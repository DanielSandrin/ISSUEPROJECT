package com.chamados.chamados.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AWSService {
    @Autowired
    private AmazonS3 s3Client;

    public void putFile(UUID id, String bucketName, String fileName, File file){
        if (!s3Client.doesBucketExistV2(bucketName)){
            s3Client.createBucket(bucketName);
        }

        String key = id.toString() + '/' + fileName;

        s3Client.putObject(bucketName,key,file);
    }

    public void deleteFile(String bucketName, String fileName){
        if (s3Client.doesObjectExist(bucketName,fileName)){
            s3Client.deleteObject(bucketName,fileName);
        }else{
            throw new RuntimeException("Arquivo não encontrado!");
        }
    }

    public void deleteAllFiles(String bucketName,String key){
        ListObjectsV2Request listRequest = new ListObjectsV2Request()
                .withBucketName(bucketName)
                .withPrefix(key);

        ListObjectsV2Result result;

        do {
            result = s3Client.listObjectsV2(listRequest);
            List<DeleteObjectsRequest.KeyVersion> keys = result.getObjectSummaries().stream()
                    .map(obj -> new DeleteObjectsRequest.KeyVersion(obj.getKey()))
                    .toList();

            if(!keys.isEmpty()){
                DeleteObjectsRequest deleteRequest = new DeleteObjectsRequest(bucketName)
                        .withKeys(keys)
                        .withQuiet(true);
                s3Client.deleteObjects(deleteRequest);
            }

            listRequest.setContinuationToken(result.getNextContinuationToken());
        } while (result.isTruncated());
    }

    public String getUrlAssinada(String bucketName, String fileName){
        Date DataExpiracao = new Date(System.currentTimeMillis()+(5 * 60 * 100));
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName,fileName)
                .withMethod(HttpMethod.GET)
                .withExpiration(DataExpiracao);
        return s3Client.generatePresignedUrl(request).toString();

    }

}
