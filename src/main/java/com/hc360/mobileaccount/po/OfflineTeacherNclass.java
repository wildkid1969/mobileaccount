package com.hc360.mobileaccount.po;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class OfflineTeacherNclass {
	
	//columns START
    /**
     * teachernclassid       db_column: teachernclassid  
     * 
     * 
     * 
     */	
	private java.lang.Long teachernclassid;
    /**
     * teacherid       db_column: teacherid  
     * 
     * 
     * 
     */	
	private java.lang.Long teacherid;
    /**
     * classid       db_column: classid  
     * 
     * 
     * 
     */	
	private java.lang.Long classid;
	//columns END

	public OfflineTeacherNclass(){
	}

	public OfflineTeacherNclass(
		java.lang.Long teachernclassid
	){
		this.teachernclassid = teachernclassid;
	}

	public void setTeachernclassid(java.lang.Long value) {
		this.teachernclassid = value;
	}
	
	public java.lang.Long getTeachernclassid() {
		return this.teachernclassid;
	}
	public void setTeacherid(java.lang.Long value) {
		this.teacherid = value;
	}
	
	public java.lang.Long getTeacherid() {
		return this.teacherid;
	}
	public void setClassid(java.lang.Long value) {
		this.classid = value;
	}
	
	public java.lang.Long getClassid() {
		return this.classid;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Teachernclassid",getTeachernclassid())
			.append("Teacherid",getTeacherid())
			.append("Classid",getClassid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getTeachernclassid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof OfflineTeacherNclass == false) return false;
		if(this == obj) return true;
		OfflineTeacherNclass other = (OfflineTeacherNclass)obj;
		return new EqualsBuilder()
			.append(getTeachernclassid(),other.getTeachernclassid())
			.isEquals();
	}
}

