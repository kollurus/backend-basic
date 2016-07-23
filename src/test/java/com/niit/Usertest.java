package com.niit;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.DAO.UserDAO;
import com.niit.model.User;

public class Usertest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = (User) context.getBean("user");
		user.setId("225");
		user.setName("minni");
		user.setPassword("my123");
		user.setMobile("9999999999");
		user.setEmail("test@test");
		user.setAddress("my home");
		userDAO.saveOrUpdate(user);
		context.close();

	}

}

