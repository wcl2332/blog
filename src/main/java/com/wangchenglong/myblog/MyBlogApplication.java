package com.wangchenglong.myblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
@MapperScan("com.wangchenglong.myblog.mapper")
public class MyBlogApplication implements CommandLineRunner {
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void run(String... args) throws Exception {
        Files.createDirectories(Paths.get(uploadDir));
    }

    public static void main(String[] args) {
        SpringApplication.run(MyBlogApplication.class, args);
    }

}
