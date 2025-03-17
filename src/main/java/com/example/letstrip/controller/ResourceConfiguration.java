package com.example.letstrip.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 파일 저장 폴더 관리 클래스
@Configuration
public class ResourceConfiguration implements WebMvcConfigurer{
	// 파일 저장 폴더의 경로 저장
	@Value("${project.upload.path}")
	private String uploadpath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/mateImage/**")
				.addResourceLocations("file:///" + uploadpath + "/");
	}

}

