package com.example.letstrip.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
public class TrainRestController {

    @GetMapping("/trainSchedule")  // 경로 변경
    public String getTrainSchedule(
                                   @RequestParam("departureDate") String departureDate,
                                   @RequestParam("departureCode") String departureCode,
                                   @RequestParam("arrivalCode") String arrivalCode
                                   ) throws Exception {
        
        String apiKey = "tlkOqKpio2H5dPSq3SuOrEHcD7PHT%2Fhb4lVbsChKjDrhLni30AszV8AHZzfmoyzS51OtdYVwt%2F4qZzROZoIY4w%3D%3D";
       
        String url = "https://apis.data.go.kr/B551457/run/v2/travelerTrainRunPlan2?serviceKey=" + apiKey +
                "&pageNo=1&numOfRows=10&returnType=JSON" +
                "&cond%5Brun_ymd%3A%3AGTE%5D=" + departureDate.trim() + // 20240101
                "&cond%5Bdptre_stn_cd%3A%3AEQ%5D=" + departureCode.trim() + // 3900023 (출발지역코드)
                "&cond%5Barvl_stn_cd%3A%3AEQ%5D=" + arrivalCode.trim(); // 3900114 (도착지역코드)
        
       
        System.out.println("Request URL: " + url);  // 디버깅용으로 URL 출력


        URI uri = new URI(url);

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(uri, String.class);

        return response;
    }
}
