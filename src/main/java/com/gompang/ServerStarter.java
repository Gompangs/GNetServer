package com.gompang;

import com.gompang.server.NetServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by stack on 2017-09-10.
 */
@SpringBootApplication
public class ServerStarter {

    public static void main(String[] args) {
        SpringApplication.run(ServerStarter.class, args);
    }
}
