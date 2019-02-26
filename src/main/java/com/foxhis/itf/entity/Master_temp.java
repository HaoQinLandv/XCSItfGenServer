package com.foxhis.itf.entity;

import java.util.Date;

/**
 * 
 roomno	char(5)		 null,
 accnt		char(10)		not null,	
 hno      char(10)		  null,
 checkInTime		datetime		  null,
 checkOutTime		datetime		  null,
 groupId	char(7)		null,
 groupName	char(17)		null,
 guestName char(17)		null,
 guestTitle  char(17)		null,
 idCardTypeCode  char(17)		null,
 idCardTypeName  char(17)		null ,
 guestId  char(17)		null,
 genderCode  char(1)		null,
 genderName  char(2)		null,
 birth  datetime null,
 nationalityCode  char(2)		null,
 nationalityName   char(17)		null,

 * @author Administrator
 * POJO
 *
 */
public class Master_temp {
	
	private String roomno;
	private String accnt;
	private String hno;
	private Date checkInTime;
	private Date checkOutTime;
	private String groupId;
	private String groupName;
	private String guestName;
	private String guestTitle;
	private String idCardTypeCode;
	private String idCardTypeName;
	private String guestId;
	private String genderCode;
	private String genderName;
	private Date birth;
	private String nationalityCode;
	private String nationalityName;
	public String getRoomno() {
		return roomno;
	}
	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}
	public String getAccnt() {
		return accnt;
	}
	public void setAccnt(String accnt) {
		this.accnt = accnt;
	}
	
	public String getHno() {
		return hno;
	}
	public void setHno(String hno) {
		this.hno = hno;
	}
	public Date getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}
	public Date getCheckOutTime() {
		return checkOutTime;
	}
	public void setCheckOutTime(Date checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getGuestTitle() {
		return guestTitle;
	}
	public void setGuestTitle(String guestTitle) {
		this.guestTitle = guestTitle;
	}
	public String getIdCardTypeCode() {
		return idCardTypeCode;
	}
	public void setIdCardTypeCode(String idCardTypeCode) {
		this.idCardTypeCode = idCardTypeCode;
	}
	public String getIdCardTypeName() {
		return idCardTypeName;
	}
	public void setIdCardTypeName(String idCardTypeName) {
		this.idCardTypeName = idCardTypeName;
	}
	public String getGuestId() {
		return guestId;
	}
	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}
	public String getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	public String getGenderName() {
		return genderName;
	}
	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getNationalityCode() {
		return nationalityCode;
	}
	public void setNationalityCode(String nationalityCode) {
		this.nationalityCode = nationalityCode;
	}
	public String getNationalityName() {
		return nationalityName;
	}
	public void setNationalityName(String nationalityName) {
		this.nationalityName = nationalityName;
	}
	@Override
	public String toString() {
		return "Master_temp [roomno=" + roomno + ", accnt=" + accnt + ", hno=" + hno + ", checkInTime=" + checkInTime
				+ ", checkOutTime=" + checkOutTime + ", groupId=" + groupId + ", groupName=" + groupName
				+ ", guestName=" + guestName + ", guestTitle=" + guestTitle + ", idCardTypeCode=" + idCardTypeCode
				+ ", idCardTypeName=" + idCardTypeName + ", guestId=" + guestId + ", genderCode=" + genderCode
				+ ", genderName=" + genderName + ", birth=" + birth + ", nationalityCode=" + nationalityCode
				+ ", nationalityName=" + nationalityName + "]";
	}
	
	
	
}
