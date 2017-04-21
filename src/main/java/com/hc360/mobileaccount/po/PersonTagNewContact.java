package com.hc360.mobileaccount.po;

public class PersonTagNewContact { 
		
		private int id;
		
		private String cmid;
		
		private String name = "";
		
		private String phone = "";
		
		private String cid="";
		
		private String createtime = "";

		public String getCmid() {
			return cmid;
		}

		public void setCmid(String cmid) {
			this.cmid = cmid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getCreatetime() {
			return createtime;
		}

		public void setCreatetime(String createtime) {
			this.createtime = createtime;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getCid() {
			return cid;
		}

		public void setCid(String cid) {
			this.cid = cid;
		}
		

	} 