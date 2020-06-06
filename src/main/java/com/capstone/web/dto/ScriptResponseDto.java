package com.capstone.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScriptResponseDto {
    private String fullScript;
    private List<TopicEditItem> topicEditList = new ArrayList<>();

    @Builder
    public ScriptResponseDto(String fullScript, List<TopicEditItem> topicEditList){
        this.fullScript = fullScript;
        this.topicEditList = topicEditList;
    }
}
