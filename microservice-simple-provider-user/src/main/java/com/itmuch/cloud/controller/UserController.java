package com.itmuch.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmuch.cloud.model.User;

@RestController
@RequestMapping("/user")
public class UserController    {


	
	@GetMapping(value="/{id}", produces="application/json;charset=utf-8")
	public User getUser(@PathVariable("id")Integer id) {
		User user = new User();
		user.setId(id);
		user.setAge(18);
		user.setName("tom"+id);
		return user;
	}
}
