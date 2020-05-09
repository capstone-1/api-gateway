package com.capstone.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TopicEditItem {
    Integer start;
    Integer end;
    String topic;

    @Builder
    public TopicEditItem(Integer start, Integer end, String topic){
        this.start = start;
        this.end = end;
        this.topic = topic;
    }
}
