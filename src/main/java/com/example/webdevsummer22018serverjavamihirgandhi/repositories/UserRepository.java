package com.example.webdevsummer22018serverjavamihirgandhi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webdevsummer22018serverjavamihirgandhi.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	
	
	
	
}
