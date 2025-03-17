package com.example.letstrip.dto;

import java.util.Date;

import com.example.letstrip.entity.Person;

import lombok.Data;

@Data
public class PersonDTO {
	private String name;
	private String id;
	private String pwd;
	private String gender;
	private String email1;
	private String email2;
	private String tel1;
	private String tel2;
	private String tel3;
	private Date logtime;

	public Person toEntity() {
		return new Person(name, id, pwd, gender, email1, email2, tel1, tel2, tel3, logtime);
	}

}
