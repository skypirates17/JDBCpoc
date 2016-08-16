package com.poc.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.poc.dao.UserRepository;
import com.poc.model.User;

public class TestingTool {
	
	private UserRepository repository;

	@Before
	public void init() {
		repository = new UserRepository();
	}
	
	@After
	public void destroy() {
		repository = null;
	}
	
	@Test
	public void save(){
		User user = new User();
		user.setName("JOHN RYAN");
		user.setAge("28");
		user.setAddress("CAINTA RIZAL");
		
		repository.save(user);
		

	}
	
	public void select(){
		List<User> userlist = repository.find(null);
		for(User each:userlist){
			System.out.println(each.getName());
		}
	}
}
