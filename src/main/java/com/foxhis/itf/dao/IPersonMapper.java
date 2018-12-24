package com.foxhis.itf.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.foxhis.itf.entity.Person;

@MapperScan
public interface IPersonMapper {

    public List<Person> getPersonBySta(String sta); 
	
	public int updatePersonChanged(Person person);
}
