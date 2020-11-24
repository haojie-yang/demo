package com.example.demo.repository;


import com.example.demo.pojo.Ball;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: yanghaojie 31648
 * @date: 2020/11/17 15:41
 */
@Repository
public interface ColorBallRepository extends JpaRepository<Ball, String> {
}
