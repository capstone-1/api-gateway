package com.capstone.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EmotionItem {
    Integer start;
    Integer end;

    @Builder
    public EmotionItem(Integer start, Integer end){
        this.start = start;
        this.end = end;
    }
}
