package com.example.letstrip.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BusRestController {

    @GetMapping("/suburbsBusSchedule")
    public String getSuburbsBusSchedule(
            @RequestParam("departure") String departure,
            @RequestParam("arrival") String arrival,
            @RequestParam("departDate") String departDate) {

        String serviceKey = "tlkOqKpio2H5dPSq3SuOrEHcD7PHT/hb4lVbsChKjDrhLni30AszV8AHZzfmoyzS51OtdYVwt/4qZzROZoIY4w==";  // 서비스 키 넣기
        String apiUrl = "http://apis.data.go.kr/1613000/SuburbsBusInfoService/getStrtpntAlocFndSuberbsBusInfo" +
                "?serviceKey=" + serviceKey +
                "&pageNo=1&numOfRows=10&_type=json" + // JSON으로 응답 받기
                "&depTerminalId=" + departure + //NAI0671801
                "&arrTerminalId=" + arrival + //NAI3214401
                "&depPlandTime=" + departDate; //20230401
        
        System.out.println("Request URL: " + apiUrl);  // 디버깅용으로 URL 출력
        
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(apiUrl, String.class);
        System.out.println("API Response: " + response); // 응답 확인
        return response;
    }
}
