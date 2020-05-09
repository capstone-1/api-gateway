package com.capstone.web.service;

import com.capstone.web.config.GCStorage;
import net.minidev.json.parser.JSONParser;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GCSReaderServiceTest {
    @Autowired
    private GCSReaderService readerService;

    private WebTestClient webTestClient;
    private String chatApiUrl = "http://localhost:5000/chat-api?fileName=test-video";

    @Test
    public void chatApiTest() {
        //given
        String expectedJson = "{\n" +
                "\"chat_edit_list\": \"[{\\\"start\\\":360,\\\"end\\\":480},{\\\"start\\\":480,\\\"end\\\":600},{\\\"start\\\":1920,\\\"end\\\":2040},{\\\"start\\\":3960,\\\"end\\\":4080},{\\\"start\\\":4080,\\\"end\\\":4200}]\",\n" +
                "\"chat_frequency_url\": \"test-video/result/chat-frequency.png\"\n" +
                "}";
        //when
        System.out.println(Objects.requireNonNull(readerService.getChatResult("test-video")));
        //then
//        assertEquals(expectedJson, );
    }
}