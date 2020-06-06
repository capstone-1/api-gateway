package com.capstone.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class EmotionResponseDto {
    List<EmotionItem> EmotionEditList = new ArrayList<>();
}
