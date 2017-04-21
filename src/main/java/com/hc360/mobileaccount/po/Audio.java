package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Audio {
	
	//columns START
    /**
     * id       db_column: id  
     * 
     * 
     * 
     */	
	private java.lang.Long id;
    /**
     * url       db_column: url  
     * 
     * 
     * @NotBlank @Length(max=200)
     */	
	private java.lang.String url;
    /**
     * description       db_column: description  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String description;
    /**
     * timeLength       db_column: time_length  
     * 
     * 
     * 
     */	
	private java.lang.Integer timeLength;
    /**
     * createtime       db_column: createtime  
     * 
     * 
     * 
     */	
	private java.util.Date createtime;
    /**
     * 标签id       db_column: labelids  
     * 
     * 
     * @Length(max=50)
     */	
	private java.lang.String labelids;
    /**
     * imgUrl       db_column: img_url  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String imgUrl;
    /**
     * 兴趣名称集合 逗号隔开       db_column: labelnames  
     * 
     * 
     * @Length(max=200)
     */	
	private java.lang.String labelnames;
	
	private int playCnt;
	//columns END

	public Audio(){
	}

	public Audio(
		java.lang.Long id
	){
		this.id = id;
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setTimeLength(java.lang.Integer value) {
		this.timeLength = value;
	}
	
	public java.lang.Integer getTimeLength() {
		return this.timeLength;
	}
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setLabelids(java.lang.String value) {
		this.labelids = value;
	}
	
	public java.lang.String getLabelids() {
		return this.labelids;
	}
	public void setImgUrl(java.lang.String value) {
		this.imgUrl = value;
	}
	
	public java.lang.String getImgUrl() {
		return this.imgUrl;
	}
	public void setLabelnames(java.lang.String value) {
		this.labelnames = value;
	}
	
	public java.lang.String getLabelnames() {
		return this.labelnames;
	}

	public int getPlayCnt() {
		return playCnt;
	}

	public void setPlayCnt(int playCnt) {
		this.playCnt = playCnt;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Url",getUrl())
			.append("Description",getDescription())
			.append("TimeLength",getTimeLength())
			.append("Createtime",getCreatetime())
			.append("Labelids",getLabelids())
			.append("ImgUrl",getImgUrl())
			.append("Labelnames",getLabelnames())
			.append("PlayCnt",getPlayCnt())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Audio == false) return false;
		if(this == obj) return true;
		Audio other = (Audio)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

