package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ChapterResourse {
	
	//columns START
    /**
     * id       db_column: id  
     * 
     * 
     * 
     */	
	private java.lang.Long id;
    /**
     * chapterid       db_column: chapterid  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Long chapterid;
    /**
     * resourceid       db_column: resourceid  
     * 
     * 
     * @NotNull 
     */	
	private java.lang.Long resourceid;
    /**
     * 类型 1视频 2录音       db_column: type  
     * 
     * 
     * @NotNull @Max(127)
     */	
	private Integer type;
	//columns END

	public ChapterResourse(){
	}

	public ChapterResourse(
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
	public void setChapterid(java.lang.Long value) {
		this.chapterid = value;
	}
	
	public java.lang.Long getChapterid() {
		return this.chapterid;
	}
	public void setResourceid(java.lang.Long value) {
		this.resourceid = value;
	}
	
	public java.lang.Long getResourceid() {
		return this.resourceid;
	}
	public void setType(Integer value) {
		this.type = value;
	}
	
	public Integer getType() {
		return this.type;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Chapterid",getChapterid())
			.append("Resourceid",getResourceid())
			.append("Type",getType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ChapterResourse == false) return false;
		if(this == obj) return true;
		ChapterResourse other = (ChapterResourse)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

