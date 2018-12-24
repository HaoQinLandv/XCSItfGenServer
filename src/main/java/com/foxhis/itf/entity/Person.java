package com.foxhis.itf.entity;


/**
 * 客人入住同步表
 * @author Administrator
 *
 */
public class Person {

	private String fh;
	private String rzsj;
	private String zwxm;
	private String zjhm;
	private String zjlx;
	private String xb;
	private String csrq;
	private String mz;
	private String jg;
	private String address;
	private String idcardzp;
	private String camerazp;
	private String type;
	private String sta;
	private String settime;
	private String changetime;
	
	@Override
	public String toString() {
		return "Person [fh=" + fh + ", rzsj=" + rzsj + ", zwxm=" + zwxm + ", zjhm=" + zjhm + ", zjlx=" + zjlx + ", xb="
				+ xb + ", csrq=" + csrq + ", mz=" + mz + ", jg=" + jg + ", address=" + address + ", idcardzp="
				+ idcardzp + ", camerazp=" + camerazp + ", type=" + type + ", sta=" + sta + ", settime=" + settime
				+ ", changetime=" + changetime + "]";
	}
	public String getSettime() {
		return settime;
	}
	public void setSettime(String settime) {
		this.settime = settime;
	}
	public String getChangetime() {
		return changetime;
	}
	public void setChangetime(String changetime) {
		this.changetime = changetime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSta() {
		return sta;
	}
	public void setSta(String sta) {
		this.sta = sta;
	}
	public String getFh() {
		return fh;
	}
	public void setFh(String fh) {
		this.fh = fh;
	}
	public String getRzsj() {
		return rzsj;
	}
	public void setRzsj(String rzsj) {
		this.rzsj = rzsj;
	}
	public String getZwxm() {
		return zwxm;
	}
	public void setZwxm(String zwxm) {
		this.zwxm = zwxm;
	}
	public String getZjhm() {
		return zjhm;
	}
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	public String getZjlx() {
		return zjlx;
	}
	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getMz() {
		return mz;
	}
	public void setMz(String mz) {
		this.mz = mz;
	}
	public String getJg() {
		return jg;
	}
	public void setJg(String jg) {
		this.jg = jg;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIdcardzp() {
		return idcardzp;
	}
	public void setIdcardzp(String idcardzp) {
		this.idcardzp = idcardzp;
	}
	public String getCamerazp() {
		return camerazp;
	}
	public void setCamerazp(String camerazp) {
		this.camerazp = camerazp;
	}


	
}
