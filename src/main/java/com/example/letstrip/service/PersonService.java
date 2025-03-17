package com.example.letstrip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.letstrip.dao.PersonDAO;
import com.example.letstrip.dto.PersonDTO;
import com.example.letstrip.entity.Person;

@Service
public class PersonService {
	
	@Autowired
	PersonDAO dao;
	
	// 회원가입
	public Person personWrite(PersonDTO dto) {
		return dao.personWrite(dto);
	}
		
	// 존재 검사
	public boolean isExistId(String id) {
		return dao.isExistId(id);
	}
		
	// 한 명 데이터
	public Person getPerson(String id) {
		return dao.getPerson(id);
	}
		
	// 수정
	public boolean personModify(PersonDTO dto) {
		return dao.personModify(dto);
	}	
	
	// 로그인
	public Person login(String id, String pwd) {
		return dao.login(id, pwd);
	}

}
