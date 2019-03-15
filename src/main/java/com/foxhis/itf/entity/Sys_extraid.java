package com.foxhis.itf.entity;

import com.foxhis.itf.annotation.Table;
import com.foxhis.itf.annotation.XcsEntity;

@XcsEntity
@Table(value = "sys_extraid")
public class Sys_extraid {
	private String cat;
	private String descript;
	private int id;
	public String getCat() {
		return cat;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
