package com.capstone.web.config;

import com.capstone.web.dto.ChatInterval;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;

public class ChatIntervalDeserializer extends JsonDeserializer<ChatInterval> {

    @Override
    public ChatInterval deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        System.out.println(p.getCodec().readTree(p));
        ChatInterval chatInterval = new ChatInterval();
        chatInterval.setStart(1);
        chatInterval.setEnd(2);
        return chatInterval;
    }
}
