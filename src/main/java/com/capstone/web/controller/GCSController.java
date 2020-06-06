package com.capstone.web.controller;

import com.capstone.web.dto.EmotionResponseDto;
import com.capstone.web.dto.ScriptResponseDto;
import com.capstone.web.dto.VideoResponseDto;
import com.capstone.web.service.GCSReaderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class GCSController {
    private final GCSReaderService gcsReaderService;

    public GCSController(GCSReaderService GCSReaderService) {
        this.gcsReaderService = GCSReaderService;
    }


    @GetMapping("/get-all-videos")
    public List<VideoResponseDto> getAllVideos(@RequestParam(name = "storageName") String name) {
        return gcsReaderService.getAllVideos(name);
    }

    //GCS에서 다운 => 자르고 스크립트에 하나씩 생성
    @GetMapping("/get-script-result")
    public Mono<ScriptResponseDto> getScriptResult(@RequestParam(name = "videoName") String name) {
        return gcsReaderService.getScriptResult(name);
    }

    @GetMapping("/get-emotion")
    public Mono<EmotionResponseDto> getEmotionResult(@RequestParam(name = "videoName") String name) {
        return gcsReaderService.getEmotionResult(name);
    }

    @GetMapping("/get-chat-result")
    public Mono<String> getChatResult(@RequestParam(name = "videoName") String name) {
        return gcsReaderService.getChatResult(name);
    }

    @GetMapping("/get-decibel-result")
    public Mono<String> getDecibelResult(@RequestParam(name = "videoName") String name) {
        return gcsReaderService.getDecibelResult(name);
    }
}


//name: test123.wav => name에 directory/file 형식으로 나옴
//Time Created : 이후 스크립트 작성에 필요할듯
//content Type : video, audio, text/plain