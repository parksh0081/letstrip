package com.example.letstrip.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.letstrip.dto.PlaceDTO;
import com.example.letstrip.dto.ReviewDTO;
import com.example.letstrip.entity.Place;
import com.example.letstrip.entity.Review;
import com.example.letstrip.service.MapReviewService;
import com.example.letstrip.service.PlaceService;


@RestController
public class MapRestController {
	
	@Autowired
	PlaceService placeService;
	
	@Autowired
	MapReviewService mapReviewService;
	

	@PostMapping("/map/mapPlaceViewJson")
	public Map<String, Object> map(@RequestBody PlaceDTO dto, Model model){
		System.out.println(dto.toString());
		Place place=placeService.select(dto.getId());
		
		if (place==null){
			//TODO 사진 문제 해결...
			if(dto.getPlace_image()==null) {
				dto.setPlace_image("원조할아버지손두부.jpg");
			}
			place=placeService.insert(dto);
		}


		Map<String, Object> map = new HashMap<>();
		map.put("id", place.getId());
		map.put("place_name", place.getPlace_name());
		map.put("phone", place.getPhone());
		map.put("category_group_name", place.getCategory_group_name());
		map.put("road_address_name", place.getRoad_address_name());
		map.put("place_image", place.getPlace_image());
	
		
		return map;
	}
	
	
	// ** Review **
	
		// 파일 저장 폴더의 경로 저장 
		@Value("${map.upload.path}")
		private String uploadpath;

		
		@PostMapping("/map/mapReviewWrite") // 리뷰 하나당 
		public ResponseEntity<String> mapReviewWrite(ReviewDTO reviewDTO, Model model, @RequestParam("img") List<MultipartFile> list) {
			// 데이터 가져오기
			reviewDTO.setReact(0);
			reviewDTO.setImages(null); 	// 디폴트 null값 
			reviewDTO.setLogtime(new Date());
			System.out.println("ReviewDTO: "+reviewDTO);
			
			
			List<String>fileNames=new ArrayList<String>();
			for(int i=0; i<list.size(); i++) {
				fileNames.add(list.get(i).getOriginalFilename()); // aaa.jpg 형식으로 저장됨 
			}
			reviewDTO.setImages(fileNames);
			
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
			
//			model.addAttribute("fileNames",fileNames);
			
			return ResponseEntity.ok("리뷰 저장 완료!!");
		}
		
//		@GetMapping("/map/updateSideTab")
//		public String updateSideTab(Model model, @RequestParam("place_id") String place_id) {
//		System.out.println("placeid: "+place_id);
//			// 리뷰 목록 
//			List<Review>reviewList=mapReviewService.selectList(place_id);
//			
//			model.addAttribute("reviewList",reviewList);
//			//return "map::mapReviewListFragment"; // 프래그먼트 반환 
//			//return "mapPlaceView::reviewList"; // 프래그먼트 반환 
//			return "/map/mapReviewListFragment";
//		}
}
