package com.example.linebot.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Repository
public class SummaryRepository {
    private final RestTemplate restTemplate;

    @Autowired
    public SummaryRepository(RestTemplateBuilder templateBuilder){
        this.restTemplate = templateBuilder.build();
    }

    public static String findSummaryAPI(String region) throws IOException, InterruptedException {
        //ProcessBuilder builder = new ProcessBuilder("python", "test.py", region);
        ProcessBuilder builder = new ProcessBuilder("python", "sample3.py", region);
        Process process = builder.start();
        InputStream is = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String D = "";
        while (true) {
            int c = is.read();
            if (c == -1) {
                is.close();
                break;
            }
            char moji = (char)c;
            D += moji;
        }
        int ret = process.waitFor();
        Path file = Paths.get("log.txt");
        String text = Files.readString(file);
        return text;
    }

}
