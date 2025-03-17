package com.example.letstrip.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.letstrip.dto.PersonDTO;
import com.example.letstrip.entity.Person;
import com.example.letstrip.repository.PersonRepository;

@Repository
public class PersonDAO {
	@Autowired
	PersonRepository personRepository;
	
	// 회원가입
		public Person personWrite(PersonDTO dto) {
			dto.setLogtime(new Date());
			Person person = dto.toEntity();
			// 없을시 저장
			boolean isExist = personRepository.existsById(dto.getId());
			if(!isExist) {	
				return personRepository.save(person);
			}
			return null;
		}
		
		// 아이디 중복 검사
		public boolean isExistId(String id) {
			return personRepository.existsById(id);
		}	
		
		// 아이디 한 명
		public Person getPerson(String id) {
			return personRepository.findById(id).orElse(null);
		}
		
		// 수정
		public boolean personModify(PersonDTO dto) {
			boolean result = false;
			Person person_old = personRepository.findById(dto.getId()).orElse(null);
			if(person_old != null) {
				Person person = dto.toEntity();
				person.setLogtime(person_old.getLogtime());	// 기존 등록일로 저장
				// 저장
				Person person_result = personRepository.save(person);
				
				if(person_result != null) {
					result = true;
				}
			}
			return result;
		}

	
	// 로그인
	public Person login(String id, String pwd) {
		return personRepository.findByIdAndPwd(id, pwd);
	}
}
