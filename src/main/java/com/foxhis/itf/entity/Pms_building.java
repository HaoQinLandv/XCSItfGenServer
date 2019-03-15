package com.foxhis.itf.entity;

import java.util.Date;

import com.foxhis.itf.annotation.Table;
import com.foxhis.itf.annotation.XcsEntity;


@XcsEntity
@Table(value = "pms_building")
public class Pms_building {
	
    private String roomno;
    /**
     * sta=房控
     */
    private String type;
    /**
     * 1=ckin,0=ckot
     */
	private String tag;
	private String changed;
	private String toroomno;
	private Date settime;
	private String accnt;
	private Date wktime;
	private Date chgtime;
	public String getRoomno() {
		return roomno;
	}
	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getToroomno() {
		return toroomno;
	}
	public void setToroomno(String toroomno) {
		this.toroomno = toroomno;
	}
	public Date getSettime() {
		return settime;
	}
	public void setSettime(Date settime) {
		this.settime = settime;
	}
	public String getAccnt() {
		return accnt;
	}
	public void setAccnt(String accnt) {
		this.accnt = accnt;
	}
	public Date getWktime() {
		return wktime;
	}
	public void setWktime(Date wktime) {
		this.wktime = wktime;
	}
	public Date getChgtime() {
		return chgtime;
	}
	public void setChgtime(Date chgtime) {
		this.chgtime = chgtime;
	}
	@Override
	public String toString() {
		return "Pms_building [roomno=" + roomno + ", type=" + type + ", tag=" + tag + ", changed=" + changed
				+ ", toroomno=" + toroomno + ", settime=" + settime + ", accnt=" + accnt + ", wktime=" + wktime
				+ ", chgtime=" + chgtime + "]";
	}
	
	

}
