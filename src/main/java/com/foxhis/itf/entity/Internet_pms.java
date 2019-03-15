package com.foxhis.itf.entity;

import java.util.Date;

/**
 *  username	varchar(16)	not null,
	 tag	char(1)	DEFAULT  '0'	not null,
	 changed	char(1)	DEFAULT  'T'	not null,
	 empno	char(3)	null,
	 date	datetime	null,
	 settime	datetime	DEFAULT  getdate()	null,
	 roomno	char(5)	null,
	 accnt	char(7)	null

 * @author Administrator
 *
 */
@Deprecated
public class Internet_pms {
	
	
	private String username;
	
	private String tag;
	private String changed;
	private String empno;
	private Date date;
	private Date settime;
	private String roomno;
	private String accnt;
	

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getChanged() {
		return changed;
	}
	public void setChanged(String changed) {
		this.changed = changed;
	}
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getSettime() {
		return settime;
	}
	public void setSettime(Date settime) {
		this.settime = settime;
	}
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
	@Override
	public String toString() {
		return "Internet_pms [username=" + username + ", tag=" + tag + ", changed=" + changed + ", empno=" + empno
				+ ", date=" + date + ", settime=" + settime + ", roomno=" + roomno + ", accnt=" + accnt + "]";
	}
}
