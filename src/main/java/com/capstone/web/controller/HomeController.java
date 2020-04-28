package com.capstone.web.controller;

import com.capstone.web.dto.VideoResponseDto;
import com.capstone.web.service.VideoLoadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {
    private final VideoLoadService videoLoadService;

    public HomeController(VideoLoadService videoLoadService) {
        this.videoLoadService = videoLoadService;
    }


    @GetMapping("/videos")
    public void getVideos(@RequestParam("name") String name) {
        videoLoadService.getVideos(name);
    }

}
