package com.capstone.web.service;

import com.capstone.web.dto.VideoResponseDto;
import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class VideoLoadService {

    @Autowired
    private final Storage storage;

    public VideoLoadService(Storage storage) {
        this.storage = storage;
    }


    //TODO:
    //1. Filtering Using ContentType (VIdeo, Audio, Text)
    //2. Make Directory Each Person
    public void getVideos(String userName) {
        Bucket bucket = storage.get(userName, Storage.BucketGetOption.fields(Storage.BucketField.values()));
        Page<Blob> blobs = bucket.list();
        for (Blob blob : blobs.iterateAll()) {
            printBlobMetaData(blob);
        }
    }

    private void printBlobMetaData(Blob blob) {
        // Print blob metadata
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
