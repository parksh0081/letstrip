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
import com.example.letstrip.LetstripApplication;
import com.example.letstrip.dto.PlaceDTO;
import com.example.letstrip.dto.ReviewDTO;
import com.example.letstrip.dto.ReviewlikeDTO;
import com.example.letstrip.dto.StoreDTO;
import com.example.letstrip.entity.Place;
import com.example.letstrip.entity.Review;
import com.example.letstrip.entity.Reviewlike;
import com.example.letstrip.entity.Store;
import com.example.letstrip.repository.ReviewlikeRepository;
import com.example.letstrip.service.MapReviewService;
import com.example.letstrip.service.PlaceService;
import com.example.letstrip.service.ReviewlikeService;
import com.example.letstrip.service.StoreService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
public class MapRestController {

    private final ReviewlikeRepository reviewlikeRepository;

    private final LetstripApplication letstripApplication;
	
	@Autowired
	PlaceService placeService;
	
	@Autowired
	MapReviewService mapReviewService;
	
	@Autowired
	ReviewlikeService reviewlikeService;
	
	@Autowired
	StoreService storeService;

    MapRestController(LetstripApplication letstripApplication, ReviewlikeRepository reviewlikeRepository) {
        this.letstripApplication = letstripApplication;
        this.reviewlikeRepository = reviewlikeRepository;
    }

	@PostMapping("/map/mapPlaceViewJson")
	public Map<String, Object> map(@RequestBody PlaceDTO dto, Model model){
		Place place=placeService.select(dto.getId());
		System.out.println(dto.toString());
		if (place==null){
			//TODO 사진 문제 해결...
			if(dto.getPlace_image()==null) {
				dto.setPlace_image("??.jpg");
			}
			if(dto.getRoad_address_name()=="") {
				dto.setRoad_address_name("정보가 없습니다.");
			}
			if(dto.getPhone()=="") {
				dto.setPhone("정보가 없습니다.");
			}
			if(dto.getCategory_name()=="") {
				dto.setCategory_name("정보가 없습니다.");
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
			
			return ResponseEntity.ok("리뷰 저장 완료!!");
		}
		
		// review like
		@GetMapping("/map/reviewLike") 
		public ResponseEntity<Map<String, Object>> reviewLike(Model model,HttpServletRequest request) {
			Map<String, Object> response=new HashMap<>();
			
			int seq=Integer.parseInt(request.getParameter("seq")); // 리뷰 글 번호 
			String id=request.getParameter("id");
			Review review=mapReviewService.selectReviewBySeq(seq); // get react
			ReviewlikeDTO reviewlikeDTO=new ReviewlikeDTO();
			reviewlikeDTO.setReview_seq(seq);
			reviewlikeDTO.setPerson_id(id);
			reviewlikeDTO.setReview(review);
			Reviewlike checkReviewlike=reviewlikeService.selectReviewlike(id, seq);
			if(checkReviewlike==null) {  	// 좋아요 안눌러있었으면 좋아요 허용 
				mapReviewService.updateReact(seq); // react update
				reviewlikeService.insertReviewlike(reviewlikeDTO);
				response.put("result", "좋아요 완료");
			}else {
				mapReviewService.updateReactMinus(seq);
				reviewlikeService.deleteReviewlike(id, seq);
				response.put("result", "좋아요 취소");
			}
			review=mapReviewService.selectReviewBySeq(seq); // get react
			
			response.put("react", review.getReact());
				

			return ResponseEntity.ok(response);
		}
		
		// review like check
		@GetMapping("/map/reviewlikeCheck")
		public ResponseEntity<Map<String, Object>>reviewlikeCheck(@RequestParam("seq") int seq, @RequestParam("id") String id){
			Map<String, Object> response = new HashMap<>();
			Reviewlike checkReviewlike=reviewlikeService.selectReviewlike(id, seq);
			if (checkReviewlike != null) {
		        response.put("liked", true);  // 사용자가 좋아요 눌렀음
		    } else {
		        response.put("liked", false); // 사용자가 좋아요 안눌렀음
		    }
			return ResponseEntity.ok(response);
		}
		
		
		// ** 내 찜 
		@PostMapping("/map/mapPlaceStore")
		public ResponseEntity<Map<String, Object>> mapPlaceStore(@RequestBody StoreDTO dto) {
			// 찜 저장 
			Store store=storeService.selectCheck(dto.getId(),dto.getPlaceid());
					
			if(store==null) { // 저장된게 없으면 저장  
				storeService.insert(dto);
			}else { // 저장된게 있으면 삭제 
				storeService.delete(dto.getId(), dto.getPlaceid());
			}
			
			// 최신 storeList 가져오기
	        List<Store> updatedStoreList = storeService.selectList(dto.getId());

	        Map<String, Object> response = new HashMap<>();
	        response.put("success", true);
	        response.put("storeList", updatedStoreList); // JSON으로 리스트 반환

	        return ResponseEntity.ok(response);
		}
		

}
