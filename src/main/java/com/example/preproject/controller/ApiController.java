package com.example.preproject.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    /*
        @CrossOrigin(origins = “허용주소:포트”)
        모든 origin 허용은 @CrossOrigin(origins="*") 설정
    */

    // http://localhost:8080 에서 들어오는 요청만 CORS 허용
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/")
    public String postSuccess() {
        return "REST API 호출 성공~!!";
    }
}