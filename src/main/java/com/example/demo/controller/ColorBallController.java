package com.example.demo.controller;

import com.example.demo.pojo.Ball;
import com.example.demo.repository.ColorBallRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: yanghaojie 31648
 * @date: 2020/11/17 15:35
 */
@RequestMapping("/color")
@RestController
@Slf4j
public class ColorBallController {

    @Autowired
    private ColorBallRepository colorBallRepository;

    @RequestMapping("/create")
    public List<Ball> create(){
        List<Ball> balls = colorBallRepository.findAll();
        List<Ball> colors = new ArrayList<>();
        int count = 0;
        for(long a = 1; a < 34; a++){
            for(long b = a+1; b < 34; b++){
                for(long c = b+1; c < 34; c++){
                    for(long d = c+1; d < 34; d++){
                        for(long e = d+1; e < 34; e++){
                            for(long f = e+1; f < 34; f++){
                                for(long g = 1; g < 17; g++){
                                    Ball ball = new Ball(a,b,c,d,e,f,g,0L);
                                    for (Ball ball1 : balls) {
                                        if (ball.getRed1() != ball1.getRed1() && ball.getRed2() != ball1.getRed2() && ball.getRed3() != ball1.getRed3() && ball.getRed4() != ball1.getRed4() && ball.getRed5() != ball1.getRed5() && ball.getRed6() != ball1.getRed6() && ball.getBlue() != ball1.getBlue()){
                                            colors.add(ball);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    count += colors.size();
                    colorBallRepository.saveAll(colors);
                    colors.clear();
                }
            }
        }
        colorBallRepository.saveAll(colors);
        log.info("双色球总共数量:" + count);
        return colorBallRepository.findAll();
    }
}
