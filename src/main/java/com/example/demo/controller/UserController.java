package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dao.UserDaoService;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;

@RestController
public class UserController {

	@Autowired
	private UserDaoService userDaoService;
	
	@GetMapping("/users")
 	public List<User> getAllUsers()
	{
		return userDaoService.findAll();
	}
//	@GetMapping("/user/{id}")
// 	public User findOneUser(@PathVariable int id) {
// 		return userDaoService.findOne(id);
// 	}
	@GetMapping("/user/{id}")
 	public User findOneUser(@PathVariable int id) {
 		User user = userDaoService.findOne(id);
 		if(user==null) {
 			throw new UserNotFoundException("id" + id);
 		}
 		return user;
 	}
	@PostMapping("/user")
 	public ResponseEntity<Object> saveUser(@RequestBody User user) {
 		 User savedUser = userDaoService.save(user);
 		 URI location = ServletUriComponentsBuilder
 		 .fromCurrentRequest()
 		 .path("/{id}")
 		 .buildAndExpand(savedUser.getId())
 		 .toUri();
 		 
 		 return ResponseEntity.created(location).build();
 	}
}
