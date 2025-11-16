package com.kjandgo.webfluxdemo.router;

import com.kjandgo.webfluxdemo.handler.TestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TestRouter {

        @Bean
        public RouterFunction<ServerResponse> userRoutes(TestHandler handler) {
            return RouterFunctions.route()
                    .GET("/fluxtest", handler::fluxTest)
                    .GET("/fluxtest2", handler::fluxTest2)
                    .build();
        }

}
