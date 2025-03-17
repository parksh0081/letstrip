package com.example.letstrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.letstrip.entity.Person;

public interface PersonRepository extends JpaRepository<Person, String>{
	@Query(value="select * from person where id = :id and pwd=:pwd", nativeQuery = true)
	Person findByIdAndPwd(@Param("id") String id, @Param("pwd") String pwd);
	
}
