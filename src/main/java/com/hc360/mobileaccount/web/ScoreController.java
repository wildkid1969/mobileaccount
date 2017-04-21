package com.hc360.mobileaccount.web;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc360.mobileaccount.po.Account;
import com.hc360.mobileaccount.po.CommonPoUtil;
import com.hc360.mobileaccount.po.LuckySocre;
import com.hc360.mobileaccount.po.PReturn;
import com.hc360.mobileaccount.po.ReturnMsg;
import com.hc360.mobileaccount.po.Score;
import com.hc360.mobileaccount.service.AccountService;
import com.hc360.mobileaccount.service.ScoreService;
import com.hc360.mobileaccount.utils.MobileAccountUtils;
import com.hc360.mobileaccount.utils.RC4;

@Controller
@RequestMapping("/score")
public class ScoreController {

	@Autowired
	private ScoreService scoreService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private CommonPoUtil map;

	@RequestMapping(value = "test", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String cc(String phone, String length ){
		accountService.updateYzxbalance(phone, length);
		return "OK";
	}

	@RequestMapping(value = "action", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String accountlogin(Score score, HttpServletResponse response, String callback) throws Exception {
		ReturnMsg retMsg = new ReturnMsg();

		if (score == null || score.getPhone() == null || score.getType() == null) {
			retMsg.setCode(101);
			retMsg.setMessage("数据填写错误，请重试");
			return MobileAccountUtils.getGson().toJson(retMsg);
		} else {
			score.setMarktime(MobileAccountUtils.getDateNow());
			try {
				String pp = RC4.decry_RC4(score.getPhone());
				score.setPhone(pp);
			} catch (Exception e) {
			}
			if (accountService.isExitsPhone(score.getPhone(),score.getAppType())) {
				String num = map.getScoreTypeMap().get(score.getType());	
				if (num == null) {
					retMsg.setCode(103);
					retMsg.setMessage("积分类型不存在");
					if(callback != null){
						return callback + "(" + MobileAccountUtils.getGson().toJson(retMsg) + ")";
					}
					return MobileAccountUtils.getGson().toJson(retMsg);
				}

				int count = scoreService.countScoreLog(score);
				if (count > 0) {
					retMsg.setCode(105);
					retMsg.setMessage("今天已经获取该积分了");
					if(callback != null){
						return callback + "(" + MobileAccountUtils.getGson().toJson(retMsg) + ")";
					}
					return MobileAccountUtils.getGson().toJson(retMsg);
				}
				score.setScorenum(num);
				if (Integer.valueOf(num) > 0) {
					score.setAction(1);
				} else {
					score.setAction(2);
				}
				score.setScorenum("+"+score.getScorenum());
				if(score.getType().equals("qd")){
					score.setName("签到");
				}else if(score.getType().equals("fx")){
					score.setName("分享");
				}else if(score.getType().equals("sz")){
					score.setName("免费电话设置");
				}else if(score.getType().equals("wsxx")){
					score.setName("完善信息30分钟");
				}
				int ret = scoreService.updateScore(score);
				if (ret > 0) {
					retMsg.setCode(200);
					retMsg.setMessage("领取积分成功");
					if(callback != null){
						return callback + "(" + MobileAccountUtils.getGson().toJson(retMsg) + ")";
					}
					if(score.getType().equals("wsxx")){
							// 给云之讯账号充值
							String result = accountService.buyGoodsForYZX(score.getPhone(), "1.65");
							System.out.println(result);
							PReturn yzxReturnMsg = MobileAccountUtils.getGson().fromJson(result, PReturn.class);
							if(yzxReturnMsg != null && yzxReturnMsg.getResp().getRespCode().equals("000000")){
								accountService.updateYzxbalance(score.getPhone(), "1800");
							}
					}
					return MobileAccountUtils.getGson().toJson(retMsg);
				} else {
					retMsg.setCode(102);
					retMsg.setMessage("领取积分失败");
					if(callback != null){
						return callback + "(" + MobileAccountUtils.getGson().toJson(retMsg) + ")";
					}
					return MobileAccountUtils.getGson().toJson(retMsg);
				}
			} else {
				retMsg.setCode(104);
				retMsg.setMessage("账号不存在");
				if(callback != null){
					return callback + "(" + MobileAccountUtils.getGson().toJson(retMsg) + ")";
				}
				return MobileAccountUtils.getGson().toJson(retMsg);
			}
		}
	}

	@RequestMapping(value = "getscore", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getUserScore(String phone,String callback) {
		ReturnMsg retMsg = new ReturnMsg();
		try {
			String pp = RC4.decry_RC4(phone);
			phone = pp;
		} catch (Exception e) {
		}
		Score s = scoreService.selectScore(phone);
		if (s != null) {
			retMsg.setCode(200);
			retMsg.setScore(s.getScorenum());
		} else {
			retMsg.setCode(106);
			retMsg.setScore("0");
		}
		Account account = accountService.getAccountInfo(phone);
		if(account != null){
			retMsg.setIsmodify(String.valueOf(account.getIsmodify()));
		}
		
		if(callback !=null ){
			return callback + "(" +MobileAccountUtils.getGson().toJson(retMsg) + ")";
		}
		
		return MobileAccountUtils.getGson().toJson(retMsg);
	}

	@RequestMapping(value = "getlist", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getLuckyList(String callback) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		List<LuckySocre> list = new ArrayList<LuckySocre>();
		for (Map.Entry<String, LuckySocre> entry : map.getLuckyMap().entrySet()) {
			if((entry.getValue().getStartTime() != "" && now.getTime() < sdf.parse(entry.getValue().getStartTime()).getTime())
					|| (entry.getValue().getEndTime() != "" && now.getTime() >= sdf.parse(entry.getValue().getEndTime()).getTime())){
				continue;
			}
			list.add(entry.getValue());
		}
		if(callback != null){
			return callback + "(" + MobileAccountUtils.getGson().toJson(list) + ")";
		}
		return MobileAccountUtils.getGson().toJson(list);
	}

	@RequestMapping(value = "getscoredraw", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getScoreDraw(String phone, String callback) {
		try {
			String pp = RC4.decry_RC4(phone);
			phone = pp;
		} catch (Exception e) {
		}
		if(callback != null){
			return callback + "(" + scoreService.getScoreDraw(phone) + ")";
		}
		return scoreService.getScoreDraw(phone);
	}
	

	
	@RequestMapping(value = "getscoreinfo", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getScoreInfo(String phone , String callback) throws Exception{
		String p = RC4.decry_RC4(phone);
		List<Score> lscore = scoreService.getScoreInfo(p);
		List<Score> llscore = new ArrayList<Score>(); 
		for(Score l : lscore){
			if(l.getName() != null){
				l.setName(URLEncoder.encode(l.getName(), "UTF-8"));
			}
			llscore.add(l);
		}
		if(callback != null){
			return callback +"(" +MobileAccountUtils.getGson().toJson(llscore) +")";
		}
		return MobileAccountUtils.getGson().toJson(llscore);
	}

	@RequestMapping(value = "luckyscore", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String luckyScore(String id, String phone, String callback) throws ParseException{
		ReturnMsg retMsg = new ReturnMsg();
		phone = RC4.decry_RC4(phone);
		if (phone == null || phone.equals("")) {
			retMsg.setCode(401);
			retMsg.setMessage("手机号码不能为空!");
			if(callback != null){
				return callback + "(" + MobileAccountUtils.getGson().toJson(retMsg) + ")";
			}
			return MobileAccountUtils.getGson().toJson(retMsg);
		}else if(id == null || id.equals("")){
			retMsg.setCode(402);
			retMsg.setMessage("选择要抽取的奖项!");
			if(callback != null){
				return callback + "(" + MobileAccountUtils.getGson().toJson(retMsg) + ")";
			}
			return MobileAccountUtils.getGson().toJson(retMsg);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		LuckySocre luckySocre = null;
		for (Map.Entry<String, LuckySocre> entry : map.getLuckyMap().entrySet()) {
			if((entry.getValue().getStartTime() != "" && now.getTime() < sdf.parse(entry.getValue().getStartTime()).getTime())
					|| (entry.getValue().getEndTime() != "" && now.getTime() >= sdf.parse(entry.getValue().getEndTime()).getTime())
					|| entry.getValue().getDisable().equals("1")){
				continue;
			}
			if(entry.getValue().getId().equals(id)){
				luckySocre = entry.getValue();
				break;
			}
		}
		if(luckySocre == null){
			retMsg.setCode(403);
			retMsg.setMessage("该奖项不存在!");
			if(callback != null){
				return callback + "(" + MobileAccountUtils.getGson().toJson(retMsg) + ")";
			}
			return MobileAccountUtils.getGson().toJson(retMsg);
		}

		Score score = scoreService.selectScore(phone);
		if (score == null || Integer.valueOf(score.getScorenum()) - Integer.valueOf(luckySocre.getProd()) < 0) {
			retMsg.setCode(404);
			retMsg.setMessage("积分不足!");	// 账户不存在默认积分为0	签到后会登入积分数据
			if(callback != null){
				return callback + "(" + MobileAccountUtils.getGson().toJson(retMsg) + ")";
			}
			return MobileAccountUtils.getGson().toJson(retMsg);
		}

		score.setName(luckySocre.getName());
		score.setScorefrom(score.getScorenum());
		score.setScorenum(luckySocre.getProd());
		score.setType("cj");
		score.setMarktime(String.valueOf(new Date().getTime()));
		int n = scoreService.updateScoredel(score);
		if(n <= 0){
			retMsg.setCode(300);
			retMsg.setMessage("很遗憾，请下次再来(+__+)");
			if(callback != null){
				return callback + "(" + MobileAccountUtils.getGson().toJson(retMsg) + ")";
			}
			return MobileAccountUtils.getGson().toJson(retMsg);
		}

		if(scoreService.luckySelect(luckySocre, phone) == 1){
			retMsg.setCode(200);
			if(luckySocre.getType().equals("0")){
				retMsg.setMessage("恭喜你中奖了！");
			}else{
				retMsg.setMessage("兑换成功！");
			}

			if(luckySocre.getLen() != "" && Integer.parseInt(luckySocre.getLen()) > 0){	// 只有该奖项对应分钟数不为空时，做充值操作
//				YzxAccount yzxAccount = MobileAccountUtils.getYzxAccountHc(); 	// 获取云之讯账号余额
//				if (yzxAccount == null || yzxAccount.getBalance() < 200){
//					retMsg.setCode(300);
//					retMsg.setMessage("很遗憾，请下次再来(-__-)");
//				}else{
					String result = accountService.buyGoodsForYZX(phone, ""+Double.parseDouble(luckySocre.getLen())*0.055);
					PReturn pReturn = MobileAccountUtils.getGson().fromJson(result, PReturn.class);
					if(pReturn != null && pReturn.getResp() != null && pReturn.getResp().getRespCode().equals("000000")){
						accountService.updateYzxbalance(phone, ""+Integer.parseInt(luckySocre.getLen())*60);
					}
//				}
			}
		}else{
			retMsg.setCode(300);
			retMsg.setMessage("很遗憾，请下次再来！");
		}

		if(callback != null){
			return callback + "(" + MobileAccountUtils.getGson().toJson(retMsg) + ")";
		}
		return MobileAccountUtils.getGson().toJson(retMsg);
	}
}