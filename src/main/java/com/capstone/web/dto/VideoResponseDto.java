package com.capstone.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class VideoResponseDto {
    private String name;
    //mp4, mov ....
    private String extension;
    private Date createdTime;

    @Builder
    public VideoResponseDto(String name, String extension, Long createdTime) {
        this.name = name;
        this.extension = extension;
        this.createdTime = new Date(createdTime);
    }
}
