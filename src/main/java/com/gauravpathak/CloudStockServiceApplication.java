package com.gauravpathak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*import org.springframework.cloud.netflix.eureka.EnableEurekaClient;*/

//@EnableEurekaClient
@SpringBootApplication
public class CloudStockServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudStockServiceApplication.class, args);
    }

}
