package com.example.letstrip.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@RestController
@RequestMapping("/api/transport")
public class TransportRestController {

    private static final String KAKAO_API_KEY = "6a9b38b02163e602fa7ea689297e5708"; // API 키

    @GetMapping
    public ResponseEntity<Map<String, Object>> getRoutes(@RequestParam("departure") String departure, @RequestParam("arrival") String arrival) {
        System.out.println("Departure: " + departure);
        System.out.println("Arrival: " + arrival);

        // 출발지 및 도착지 좌표 변환
        Map<String, String> departureCoords = getCoordinates(departure);
        Map<String, String> arrivalCoords = getCoordinates(arrival);

        if (departureCoords == null || arrivalCoords == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "좌표를 찾을 수 없습니다."));
        }

        // 자차 경로 API 호출
        Map<String, Object> carRoute = getCarRoute(departureCoords, arrivalCoords);

        // 결과 반환
        Map<String, Object> result = new HashMap<>();
        result.put("departure", departure);
        result.put("arrival", arrival);
        result.put("carRoute", carRoute);

        return ResponseEntity.ok(result);
    }

    private Map<String, String> getCoordinates(String address) {
        String apiUrl = "https://dapi.kakao.com/v2/local/search/keyword.json?query=" + address;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + KAKAO_API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, Map.class);

        if (response.getBody() != null && !response.getBody().get("documents").toString().equals("[]")) {
            List<Map<String, Object>> documents = (List<Map<String, Object>>) response.getBody().get("documents");
            Map<String, Object> firstResult = documents.get(0);

            Map<String, String> coords = new HashMap<>();
            coords.put("x", firstResult.get("x").toString());
            coords.put("y", firstResult.get("y").toString());

            return coords;
        }
        return null;
    }

    private Map<String, Object> getCarRoute(Map<String, String> departureCoords, Map<String, String> arrivalCoords) {
        String apiUrl = "https://apis-navi.kakaomobility.com/v1/directions?origin=" +
                departureCoords.get("x") + "," + departureCoords.get("y") +
                "&destination=" + arrivalCoords.get("x") + "," + arrivalCoords.get("y") +
                "&priority=RECOMMEND";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + KAKAO_API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, Map.class);

        System.out.println("📌 API 응답: " + response.getStatusCode());
        //System.out.println("📌 API 응답 본문: " + response.getBody());

        if (response.getBody() != null) {
            List<Map<String, Object>> routes = (List<Map<String, Object>>) response.getBody().get("routes");
            if (!routes.isEmpty()) {
                Map<String, Object> route = routes.get(0);
                Map<String, Object> summary = (Map<String, Object>) route.get("summary");

                Map<String, Object> carRoute = new HashMap<>();
                carRoute.put("distance", summary.get("distance"));
                carRoute.put("duration", summary.get("duration"));
                carRoute.put("fare", summary.get("fare"));

                return carRoute;
            }
        }
        return Collections.singletonMap("message", "자차 경로를 찾을 수 없습니다.");
    }
}
