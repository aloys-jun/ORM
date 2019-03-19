package com.aloys.orm.demo.dao.impl;

import com.aloys.orm.core.SqlSession;
import com.aloys.orm.core.impl.SqlSessionImpl;
import com.aloys.orm.demo.dao.UserDao;
import com.aloys.orm.demo.entity.UserInfo;

public class UserDaoImpl implements UserDao {

	private SqlSession session = new SqlSessionImpl();
	
	@Override
	public int insert(UserInfo userInfo) {
		
		return session.insert(userInfo);
	}

}
