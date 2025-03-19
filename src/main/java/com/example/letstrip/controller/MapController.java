package com.example.letstrip.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.letstrip.dto.ReviewDTO;
import com.example.letstrip.entity.Place;
import com.example.letstrip.entity.Review;
import com.example.letstrip.entity.Reviewimage;
import com.example.letstrip.service.MapReviewService;
import com.example.letstrip.service.PlaceService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MapController {

	@Autowired
	PlaceService placeService;
	
	@Autowired
	MapReviewService mapReviewService;
	
	@GetMapping("/map/mapMain")
	public String mapMain() {
		return "/map/mapMain";
	}

	
	@GetMapping("/map/map")
	public String map() {
		return "/map/map";
	}

	@GetMapping("/map/mapPlaceView")
	public String mapPlaceViewHtml(@RequestParam("place_id") String place_id, Model model) {
		Place place=placeService.select(place_id);
		model.addAttribute("place", place);
		model.addAttribute("place_id", place_id);
		return "map/mapPlaceView";
	}
	
	@GetMapping("/map/mapReviewWriteForm")
	public String mapReviewWriteForm(HttpServletRequest request, Model model) {
		String id=request.getParameter("place_id");
		Place place=placeService.select(id);
		model.addAttribute("place",place);
		return "/map/mapReviewWriteForm";
	}
	
	// ** Review **
	
	// 파일 저장 폴더의 경로 저장 
	@Value("${map.upload.path}")
	private String uploadpath;

	
	@PostMapping("/map/mapReviewWrite") // 리뷰 하나당 
	public String mapReviewWrite(ReviewDTO reviewDTO, HttpServletRequest request, Model model, @RequestParam("img") List<MultipartFile> list) {
		// 데이터 가져오기
//		ReviewDTO reviewDTO=new ReviewDTO();
//		int star=Integer.parseInt(request.getParameter("rating"));
//		String content=request.getParameter("content");
//		reviewDTO.setStar(star);
//		reviewDTO.setContent(content);
		reviewDTO.setReact(0);
		reviewDTO.setImages(null); 	// 디폴트 null값 
		reviewDTO.setLogtime(new Date());
		System.out.println("ReviewDTO: "+reviewDTO);
		
		List<Reviewimage>reviewImages=new ArrayList<>();
		//Reviewimage[] reviewImages=new Reviewimage[list.size()];
		
		
		List<String>fileNames=new ArrayList<String>();
		for(int i=0; i<list.size(); i++) {
			fileNames.add(list.get(i).getOriginalFilename()); // aaa.jpg 형식으로 저장됨 
			 // ReviewImage 객체 생성 및 값 설정
		    Reviewimage reviewImage = new Reviewimage();
		    reviewImage.setPlace_image(fileNames.get(i));

		    // 리스트에 추가
		    reviewImages.add(reviewImage);
		}
		reviewDTO.setImages(reviewImages);
		
		// 파일 저장 폴더 만들기 
		File folder=new File(uploadpath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		// 파일 저장하기 
		for(int i=0; i<list.size(); i++) {
			String fileName=list.get(i).getOriginalFilename();
			
			if(!fileName.equals("")) {
				File file=new File(uploadpath, fileName);
				try {
					list.get(i).transferTo(file);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		Review review=mapReviewService.insert(reviewDTO);
		if(review != null) {
			model.addAttribute("review",review);
		}
		
//		model.addAttribute("fileNames",fileNames);
		
		return "/map/mapReviewWrite";
	}
}

