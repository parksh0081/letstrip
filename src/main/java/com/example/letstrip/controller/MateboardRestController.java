package com.example.letstrip.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.letstrip.dto.ChatRoomDTO;
import com.example.letstrip.entity.ChatRoom;
import com.example.letstrip.entity.Mateboard;
import com.example.letstrip.service.ChatRoomService;
import com.example.letstrip.service.MateboardService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor 
public class MateboardRestController {
   
   @Autowired
   MateboardService service;
   
   // 목록
   @GetMapping("/mateboardListJson")
   public Map<String, Object> mateboardListJson(@RequestParam(value = "pg", defaultValue = "1")int pg) {
      // 목록 처리 (한 페이지에 10개씩)
      int endNum = pg * 10;
      int startNum = endNum - 9;
      List<Mateboard> list = service.mateboardList(startNum, endNum);
      
      // 페이징 처리 (7개씩)
      int totalA = service.totalMateA();
      int totalP = (totalA + 9) / 10;
      
      int startPage = (pg - 1) / 7 * 7 + 1;
      int endPage = startPage + 6;
      if(endPage > totalP) endPage = totalP;
      
      Map<String, Object> map = new HashMap<>();
      map.put("pg", pg);
      map.put("list", list);
      map.put("startPage", startPage);
      map.put("endPage", endPage);
      map.put("totalP", totalP);
      
      return map;
   }
   
   // 검색 목록
   @GetMapping("/mateboardListSearchJson")
   public Map<String, Object> mateboardListSearchJson(@RequestParam(value = "search", defaultValue = "")String search, @RequestParam(value = "pg", defaultValue = "1")int pg) {
      // 목록 처리 (한 페이지에 10개씩)
      int endNum = pg * 10;
      int startNum = endNum - 9;
      List<Mateboard> list = service.mateboardListSearch(startNum, endNum, search);
      
      // 페이징 처리 (7개씩)
      int totalA = service.totalMateSearchA(search);
      int totalP = (totalA + 9) / 10;
      
      int startPage = (pg - 1) / 7 * 7 + 1;
      int endPage = startPage + 6;
      if(endPage > totalP) endPage = totalP;
      
      Map<String, Object> map = new HashMap<>();
      map.put("pg", pg);
      map.put("list", list);
      map.put("startPage", startPage);
      map.put("endPage", endPage);
      map.put("totalP", totalP);
      
      return map;     
   }
   
   // 파일 저장 폴더 경로
	@Value("${project.upload.path}")
	private String uploadpath;  
   
   @PostMapping(value = "/mateboardImageUpload")
   public ResponseEntity<?> mateboardImageUpload(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
	   if (file.isEmpty()) {
		   	//System.out.println("파일이 업로드되지 않았습니다.");
	   }	
	   try {
		   	// 서버에 저장할 경로
		   	File uploadDir = new File(uploadpath);
			
			// 업로드 된 파일의 이름
			String originalFileName = file.getOriginalFilename();
			// 업로드 된 파일의 확장자
			String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
			// 업로드 될 파일의 이름 재설정 (중복 방지를 위해 UUID 사용)
			String uuidFileName = UUID.randomUUID().toString() + fileExtension;
			
			// 위에서 설정한 서버 경로에 이미지 저장
			file.transferTo(new File(uploadDir, uuidFileName));
			
			// Ajax에서 업로드 된 파일의 이름을 응답 받을 수 있도록 해줍니다.
			return ResponseEntity.ok(uuidFileName);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("이미지 업로드 실패");
		}
		
	}
   
    // 이미지 삭제 
	@PostMapping(value = "/mateboardImageDelete")
	public void mateboardImageDelete(@RequestParam("file") String fileName) {
	    // 폴더 위치
		String filePath = uploadpath;
	    
	    // 해당 파일 삭제
	    deleteFile(filePath, fileName);
	}

	// 파일 하나 삭제
	private void deleteFile(String filePath, String fileName) {
		Path path = Paths.get(filePath, fileName);
			try {
				Files.delete(path);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}  
}

