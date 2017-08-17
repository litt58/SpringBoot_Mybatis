package com.jzli;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * =======================================================
 *
 * @Company 金色家网络科技有限公司-开发测试云服务部
 * @Date ：2016/7/6
 * @Author ：li.jinzhao
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */

@SpringBootApplication
@MapperScan("com.jzli.mapper")
@EnableScheduling
public class Application {

    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
        SpringApplication springApplication = new SpringApplication(Application.class);
        //关闭banner显示
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);

    }
}
