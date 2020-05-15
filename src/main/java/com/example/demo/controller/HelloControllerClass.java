package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.HelloWorldBean;

@RestController
public class HelloControllerClass {

	@GetMapping(path = "/helloworld")
	public String helloWorld() {
		return "Hello World";
	}
	@GetMapping(path = "/helloworld-bean")
	public HelloWorldBean helloWorld1() {
		return new HelloWorldBean("Welcome to hello world bean");
	}
	@GetMapping(path = "/helloworld/{name}")
	public HelloWorldBean helloworldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));//"Hello World");
	}
}
