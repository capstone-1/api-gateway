package com.capstone.web.controller;

import com.capstone.web.dto.ChatResponseDto;
import com.capstone.web.dto.VideoResponseDto;
import com.capstone.web.service.GCSReaderService;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<VideoResponseDto> getAllVideos() {
        return gcsReaderService.getAllVideos();
    }

    //GCS에서 다운 => 자르고 스크립트에 하나씩 생성
    @GetMapping("/get-script-result")
    public Mono<String> getExtractionResult(@RequestParam(name = "videoName") String name) {
        return gcsReaderService.getExtractionResult(name);
        //토픽 모델링으로 비디오 정보 전송
    }

    @GetMapping("/get-chat-result")
    public Mono<ChatResponseDto> getChatResult(@RequestParam(name = "videoName") String name) {
        return gcsReaderService.getChatResult(name);
        //토픽 모델링으로 비디오 정보 전송
    }

//    @GetMapping("/get-decibel-result")
//    public Mono<String> getDecibelResult(@RequestParam(name = "videoName") String name) {
//        return gcsReaderService.getDecibelResult(name);
//        //토픽 모델링으로 비디오 정보 전송
//    }
}


//name: test123.wav => name에 directory/file 형식으로 나옴
//Time Created : 이후 스크립트 작성에 필요할듯
//content Type : video, audio, text/plain