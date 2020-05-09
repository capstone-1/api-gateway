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
    private String summary;
    private List<TopicEditItem> topicEditList = new ArrayList<>();

    @Builder
    public ScriptResponseDto(String fullScript, String summary, List<TopicEditItem> topicEditList){
        this.fullScript = fullScript;
        this.summary = summary;
        this.topicEditList = topicEditList;
    }
}
