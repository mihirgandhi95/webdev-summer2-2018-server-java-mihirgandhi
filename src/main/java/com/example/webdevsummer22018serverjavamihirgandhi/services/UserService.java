package com.example.webdevsummer22018serverjavamihirgandhi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer22018serverjavamihirgandhi.models.User;
import com.example.webdevsummer22018serverjavamihirgandhi.repositories.UserRepository;

@RestController
public class UserService {
	
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user)
	{
		return userRepository.save(user);
	}

	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	
	@GetMapping("/api/user/{userId}")
	public Optional<User> findUserById(@PathVariable ("userId") int userId)
	{
		return userRepository.findById(userId);
	}
	
	
	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable ("userId") int userId, @RequestBody User user)
	{	
		user.setId(userId);
		userRepository.save(user);
		return user;
		
	}
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		userRepository.deleteById(id);
	}
	
}

