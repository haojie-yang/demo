package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.util.Date;

@SpringBootApplication
@Slf4j
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DemoApplication.class);
        Environment env = app.run(args).getEnvironment();
        log.info("启动成功!http://127.0.0.1:"+env.getProperty("server.port") + "/index/index");
        log.info("MD5加密:" + jiemi("@yanghaojie1104"));
        log.info("MD5解密:" + jiemi(jiemi("@yanghaojie1104")));
    }

    public static String jiemi(String md5){
        char[] c = md5.toCharArray();
        for (int i = 0; i < c.length; i++){
            c[i] = (char) (c[i] ^ 't');
        }
        return new String(c);
    }
}
