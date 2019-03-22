package com.foxhis.itf.entity;

import java.util.Date;

import com.foxhis.itf.annotation.Table;
import com.foxhis.itf.annotation.XcsEntity;

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

@XcsEntity
@Table("master_temp")
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
	
    private String mobile;
    private String lastName;
    private String firstName;
    private String ref1;
    private String ref2;
    private String ref3;
    private String ref4;
    private String ref5;
    private String ref6;
	
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
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getRef1() {
		return ref1;
	}
	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}
	public String getRef2() {
		return ref2;
	}
	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}
	public String getRef3() {
		return ref3;
	}
	public void setRef3(String ref3) {
		this.ref3 = ref3;
	}
	public String getRef4() {
		return ref4;
	}
	public void setRef4(String ref4) {
		this.ref4 = ref4;
	}
	public String getRef5() {
		return ref5;
	}
	public void setRef5(String ref5) {
		this.ref5 = ref5;
	}
	public String getRef6() {
		return ref6;
	}
	public void setRef6(String ref6) {
		this.ref6 = ref6;
	}
	
	
	@Override
	public String toString() {
		return "Master_temp [roomno=" + roomno + ", accnt=" + accnt + ", hno=" + hno + ", checkInTime=" + checkInTime
				+ ", checkOutTime=" + checkOutTime + ", groupId=" + groupId + ", groupName=" + groupName
				+ ", guestName=" + guestName + ", guestTitle=" + guestTitle + ", idCardTypeCode=" + idCardTypeCode
				+ ", idCardTypeName=" + idCardTypeName + ", guestId=" + guestId + ", genderCode=" + genderCode
				+ ", genderName=" + genderName + ", birth=" + birth + ", nationalityCode=" + nationalityCode
				+ ", nationalityName=" + nationalityName + ", mobile=" + mobile + ", lastName=" + lastName
				+ ", firstName=" + firstName + ", ref1=" + ref1 + ", ref2=" + ref2 + ", ref3=" + ref3 + ", ref4=" + ref4
				+ ", ref5=" + ref5 + ", ref6=" + ref6 + "]";
	}
	
	
	
}
