package com.hc360.mobileaccount.po;

public class PersonTagAction { 
		private int id;
		
		private String cmid;
		
		private String action = "";
		
		private String name ="";  
		private String cid="";
		private String phone;
		private String alarmdate;
		private int state;
		private String createtime = "";
		private String corpid = "";
		private String corpname="";
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getCmid() {
			return cmid;
		}

		public void setCmid(String cmid) {
			this.cmid = cmid;
		}

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}

		public String getCreatetime() {
			return createtime;
		}

		public void setCreatetime(String createtime) {
			this.createtime = createtime;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
 

		public String getCid() {
			return cid;
		}

		public void setCid(String cid) {
			this.cid = cid;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getAlarmdate() {
			return alarmdate;
		}

		public void setAlarmdate(String alarmdate) {
			this.alarmdate = alarmdate;
		}

		public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}

		public String getCorpid() {
			return corpid;
		}

		public void setCorpid(String corpid) {
			this.corpid = corpid;
		}

		public String getCorpname() {
			return corpname;
		}

		public void setCorpname(String corpname) {
			this.corpname = corpname;
		}
		
}
