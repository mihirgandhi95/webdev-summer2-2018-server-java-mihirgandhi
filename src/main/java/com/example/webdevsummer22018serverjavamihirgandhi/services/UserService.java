package com.example.webdevsummer22018serverjavamihirgandhi.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer22018serverjavamihirgandhi.models.User;
import com.example.webdevsummer22018serverjavamihirgandhi.repositories.UserRepository;

@RestController
public class UserService {
	
	
	@Autowired
	UserRepository userRepository;
	
	HttpSession currentSession;
	
	@PostMapping("/api/register")
	public User register(@RequestBody User userObj, HttpSession session)
	{
		currentSession = session;
		System.out.println(userObj);
		userObj = userRepository.save(userObj);
		session.setAttribute("id", userObj);
		return userObj;
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
	
	
	@GetMapping("/api/register/{username}")
	public Map<String, String> findUserByUserName(@PathVariable("username") String username) {
		Map<String, String> res = new HashMap<String, String>();
		String bool;
		
		List<User> userList = userRepository.findUserByUserName(username);
		if(userList.size()==0) {
			bool = "false";
		}
		else
		{
			bool ="true";
		}
		res.put("bool",""+bool);
		return res;
		
	}
	
	@PostMapping("/api/login")
	public User login(@RequestBody User user,HttpSession session)
	{
		currentSession = session;
		List<User> userList = userRepository.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
		if(userList.size()== 0)
		{
			user = new User();
			
		}
		else 
		{
			user = userList.get(0);
			currentSession.setAttribute("id", user);
			session.setAttribute("id", user);
			
		}
		return user;
	}
	
	@RequestMapping(value = "/api/logout")
		public User logout (@RequestBody User user)
		{
				currentSession.invalidate();
				return new User();
		}
	
	@PutMapping("/api/profile")
	public User updateProfile(@RequestBody User user, HttpSession session){
		User newUser = (User) currentSession.getAttribute("id");
		int idUser = newUser.getId() ;
		user.setId(idUser);
		userRepository.save(user);
		return user;
	}
	
	@GetMapping("/api/getProfile")
	public User getProfile(HttpSession session) {
		User newUser;
		System.out.println("inside get profile!!");
		newUser =  (User) currentSession.getAttribute("id");
		System.out.println(newUser.getFirstName());
		return newUser;
	}
	
	
	
	
	
}

