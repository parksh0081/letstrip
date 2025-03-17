package com.example.letstrip.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.letstrip.service.PersonService;

@Controller
public class PersonRestController {
    
    @Autowired
    PersonService service;

    // @RequestBody로 JSON 형태로 전달된 데이터 받기
    @PostMapping("/person/checkIdJson")
    public ResponseEntity<Map<String, Object>> checkIdJson(@RequestBody Map<String, String> requestBody) {
        String id = requestBody.get("id");  // JSON에서 id 추출

        boolean result = service.isExistId(id);  // DAO에서 아이디 중복 여부 확인
        
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("result", result);  // 아이디 사용 가능 여부 반환

        return new ResponseEntity<>(responseMap, HttpStatus.OK);  // 성공 응답
    }
    
}
