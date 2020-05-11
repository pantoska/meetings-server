package com.ztp.mettings.auth.exception;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

@Configuration
class ProblemConfig {

    @Bean
    ObjectMapper objectMapper() {
        var mapper = new ObjectMapper();
        mapper.registerModules(new ProblemModule(), new ConstraintViolationProblemModule());
        return mapper;
    }
}
