package com.example.letstrip.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.PostConstruct;

// 파일 저장 폴더 관리 클래스
@Configuration
public class ResourceConfiguration implements WebMvcConfigurer{
	// 파일 저장 폴더의 경로 저장
	@Value("${project.upload.path}")
	private String uploadpath;
	
	// 없으면 파일 생성
    @PostConstruct
    public void init() {
        File directory = new File(uploadpath);
        if (!directory.exists()) {
            try {
                // 디렉토리 생성
                boolean isCreated = directory.mkdirs();
                if (isCreated) {
                    //System.out.println("디렉토리 생성 성공: " + uploadpath);
                } else {
                    //System.out.println("디렉토리 생성 실패: " + uploadpath);
                }
            } catch (SecurityException e) {
                e.printStackTrace();
                //System.out.println("디렉토리 생성 중 오류 발생: " + e.getMessage());
            }
        } else {
            //System.out.println("디렉토리가 이미 존재합니다: " + uploadpath);
        }
    }	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/mateImage/**")
				.addResourceLocations("file:///" + uploadpath + "/");
	}

}

