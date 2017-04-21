package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class AppVersion {
	
	//columns START
    /**
     * versionid       db_column: versionid  
     * 
     * 
     * 
     */	
	private java.lang.Long versionid;
    /**
     * 版本号       db_column: version_code  
     * 
     * 
     * @NotBlank @Length(max=50)
     */	
	private int versionCode;
    /**
     * 版本名称       db_column: version_name  
     * 
     * 
     * @Length(max=100)
     */	
	private java.lang.String versionName;
    /**
     * 描述       db_column: description  
     * 
     * 
     * @Length(max=65535)
     */	
	private java.lang.String description;
    /**
     * 文件大小       db_column: file_size  
     * 
     * 
     * 
     */	
	private java.lang.Integer fileSize;
    /**
     * 文件加密串       db_column: file_encrypt  
     * 
     * 
     * @Length(max=150)
     */	
	private java.lang.String fileEncrypt;
    /**
     * 是否强制更新 0否 1是       db_column: force  
     * 
     * 
     * @Max(127)
     */	
	private Integer force;
    /**
     * 下载链接       db_column: down_url  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String downUrl;
    /**
     * 创建时间       db_column: createtime  
     * 
     * 
     * 
     */	
	private java.util.Date createtime;
    /**
     * 状态 0禁用 1启用       db_column: state  
     * 
     * 
     * @Max(127)
     */	
	private Integer state;
	//columns END

	public AppVersion(){
	}

	public AppVersion(
		java.lang.Long versionid
	){
		this.versionid = versionid;
	}

	public void setVersionid(java.lang.Long value) {
		this.versionid = value;
	}
	
	public java.lang.Long getVersionid() {
		return this.versionid;
	}
	public void setVersionCode(int value) {
		this.versionCode = value;
	}
	
	public int getVersionCode() {
		return this.versionCode;
	}
	public void setVersionName(java.lang.String value) {
		this.versionName = value;
	}
	
	public java.lang.String getVersionName() {
		return this.versionName;
	}
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setFileSize(java.lang.Integer value) {
		this.fileSize = value;
	}
	
	public java.lang.Integer getFileSize() {
		return this.fileSize;
	}
	public void setFileEncrypt(java.lang.String value) {
		this.fileEncrypt = value;
	}
	
	public java.lang.String getFileEncrypt() {
		return this.fileEncrypt;
	}
	public void setForce(Integer value) {
		this.force = value;
	}
	
	public Integer getForce() {
		return this.force;
	}
	public void setDownUrl(java.lang.String value) {
		this.downUrl = value;
	}
	
	public java.lang.String getDownUrl() {
		return this.downUrl;
	}
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setState(Integer value) {
		this.state = value;
	}
	
	public Integer getState() {
		return this.state;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Versionid",getVersionid())
			.append("VersionCode",getVersionCode())
			.append("VersionName",getVersionName())
			.append("Description",getDescription())
			.append("FileSize",getFileSize())
			.append("FileEncrypt",getFileEncrypt())
			.append("Force",getForce())
			.append("DownUrl",getDownUrl())
			.append("Createtime",getCreatetime())
			.append("State",getState())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getVersionid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof AppVersion == false) return false;
		if(this == obj) return true;
		AppVersion other = (AppVersion)obj;
		return new EqualsBuilder()
			.append(getVersionid(),other.getVersionid())
			.isEquals();
	}
}

