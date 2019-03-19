package com.aloys.orm.demo.entity;

import java.util.Date;

import com.aloys.orm.annotation.Column;
import com.aloys.orm.annotation.Table;


@Table("user_info")
public class UserInfo {

	@Column
	private Integer id;
	@Column
	private String name;
	@Column
	private Date birthday;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
}
