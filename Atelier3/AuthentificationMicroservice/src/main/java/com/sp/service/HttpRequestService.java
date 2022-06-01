package com.sp.service;

import com.sp.dto.UsersDTO;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpRequestService {

    private final RestTemplate restTemplate;

    public HttpRequestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public UsersDTO getUsersByName(String name) {
        String url = "http://localhost:8081/users?userName="+name;
        return this.restTemplate.getForObject(url, UsersDTO.class);
    }
}