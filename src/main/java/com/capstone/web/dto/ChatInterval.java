package com.capstone.web.dto;

import com.capstone.web.config.ChatIntervalDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatInterval {
    private Integer start;
    private Integer end;
}
