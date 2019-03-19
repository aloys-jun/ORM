package com.aloys.orm;

import java.util.Date;

import org.junit.Test;

import com.aloys.orm.demo.dao.UserDao;
import com.aloys.orm.demo.dao.impl.UserDaoImpl;
import com.aloys.orm.demo.entity.UserInfo;

import junit.framework.Assert;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	@Test
	public void testAddUser() {
		UserDao userDao = new UserDaoImpl();
		
		UserInfo user = new UserInfo();
		user.setId(1);
		user.setName("Tony");
		user.setBirthday(new Date());
		
		int i = userDao.insert(user);
		//断言
		Assert.assertEquals(1, i);
		
	}
    
}
