package com.capstone.web.service;

import com.capstone.web.dto.EmotionResponseDto;
import com.capstone.web.dto.ScriptResponseDto;
import com.capstone.web.dto.VideoResponseDto;
import com.google.api.client.util.Lists;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GCSReaderService {

    @Autowired
    private final Storage storage;

    @Autowired
    WebClient.Builder builder;

    public GCSReaderService(Storage storage) {
        this.storage = storage;
    }

    public List<VideoResponseDto> getAllVideos(String userName) {
        Bucket bucket = storage.get(userName, Storage.BucketGetOption.fields(Storage.BucketField.values()));
        return Lists.newArrayList(bucket.list().iterateAll())
                .stream()
                .filter(this::isVideo)
                .map(this::blobToVideoResponseDto)
                .collect(Collectors.toList());
    }

    public Mono<ScriptResponseDto> getScriptResult(String name) {
        WebClient webClient = builder.baseUrl("http://localhost:5000").build();
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/script-api")
                        .queryParam("fileName", name)
                        .build())
                .retrieve()
                .bodyToMono(ScriptResponseDto.class);
    }

    public Mono<EmotionResponseDto> getEmotionResult(String name) {
        WebClient webClient = builder.baseUrl("http://localhost:5000").build();
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/emotion-api")
                        .queryParam("fileName", name)
                        .build())
                .retrieve()
                .bodyToMono(EmotionResponseDto.class);
    }

    public Mono<String> getChatResult(String name) {
        WebClient webCilent = builder.baseUrl("http://chathost:5000").build();
        return webCilent.get()
                .uri(uriBuilder -> uriBuilder.path("/chat-api")
                .queryParam("fileName", name)
                .build())
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> getDecibelResult(String name) {
        WebClient webCilent = builder.build();
        return webCilent.get()
                .uri(uriBuilder -> uriBuilder.path("http://localhost:5000/decibel-api")
                        .queryParam("fileName", name)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

    private VideoResponseDto blobToVideoResponseDto(Blob blob) {
        return VideoResponseDto.builder()
                .name(getVideoName(blob.getName()))
                .createdTime(blob.getCreateTime())
                .extension(getVideoExtension(blob.getContentType()))
                .build();
    }

    private String getVideoName(String name) {
        return name.split("/")[0];
    }

    private boolean isVideo(Blob blob) {
        return blob.getContentType().contains("video");
    }

    private String getVideoExtension(String contentType) {
        return contentType.split("/")[1];
    }

    private void printBlobAllMetaData(Blob blob) {
        // Print blob metadata
        System.out.println("======================================\n");
        System.out.println("Bucket: " + blob.getBucket());
        System.out.println("CacheControl: " + blob.getCacheControl());
        System.out.println("ComponentCount: " + blob.getComponentCount());
        System.out.println("ContentDisposition: " + blob.getContentDisposition());
        System.out.println("ContentEncoding: " + blob.getContentEncoding());
        System.out.println("ContentLanguage: " + blob.getContentLanguage());
        System.out.println("ContentType: " + blob.getContentType());
        System.out.println("Crc32c: " + blob.getCrc32c());
        System.out.println("Crc32cHexString: " + blob.getCrc32cToHexString());
        System.out.println("ETag: " + blob.getEtag());
        System.out.println("Generation: " + blob.getGeneration());
        System.out.println("Id: " + blob.getBlobId());
        System.out.println("KmsKeyName: " + blob.getKmsKeyName());
        System.out.println("Md5Hash: " + blob.getMd5());
        System.out.println("Md5HexString: " + blob.getMd5ToHexString());
        System.out.println("MediaLink: " + blob.getMediaLink());
        System.out.println("Metageneration: " + blob.getMetageneration());
        System.out.println("Name: " + blob.getName());
        System.out.println("Size: " + blob.getSize());
        System.out.println("StorageClass: " + blob.getStorageClass());
        System.out.println("TimeCreated: " + new Date(blob.getCreateTime()));
        System.out.println("Last Metadata Update: " + new Date(blob.getUpdateTime()));
        Boolean temporaryHoldIsEnabled = (blob.getTemporaryHold() != null && blob.getTemporaryHold());
        System.out.println("temporaryHold: " + (temporaryHoldIsEnabled ? "enabled" : "disabled"));
        Boolean eventBasedHoldIsEnabled =
                (blob.getEventBasedHold() != null && blob.getEventBasedHold());
        System.out.println("eventBasedHold: " + (eventBasedHoldIsEnabled ? "enabled" : "disabled"));
        if (blob.getRetentionExpirationTime() != null) {
            System.out.println("retentionExpirationTime: " + new Date(blob.getRetentionExpirationTime()));
        }
        if (blob.getMetadata() != null) {
            System.out.println("\n\n\nUser metadata:");
            for (Map.Entry<String, String> userMetadata : blob.getMetadata().entrySet()) {
                System.out.println(userMetadata.getKey() + "=" + userMetadata.getValue());
            }
        }
    }
}
