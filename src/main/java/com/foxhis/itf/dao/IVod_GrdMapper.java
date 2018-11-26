package com.foxhis.itf.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.foxhis.itf.entity.Vod_Grd;


@MapperScan
public interface IVod_GrdMapper {
	
	public List<Vod_Grd> getVodByChanged(); 
	
	public int updateVodChanged(Vod_Grd vod);

}
