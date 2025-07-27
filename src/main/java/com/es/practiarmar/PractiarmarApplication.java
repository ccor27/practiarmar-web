package com.es.practiarmar;

import com.cmeza.sdgenerator.annotation.SDGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SDGenerator(
        entityPackage = "com.es.practiarmar.model",
        repositoryPackage = "com.es.practiarmar.repository",
        managerPackage = "",
        repositoryPostfix = "Repository",
        managerPostfix = "Manager",
        onlyAnnotations = true,
        debug = false,
        overwrite = false,
        additionalExtends = {},
        lombokAnnotations = false,
        withComments = true,
        withJpaSpecificationExecutor = false
)
@EnableAsync
@SpringBootApplication
public class PractiarmarApplication {

    public static void main(String[] args) {
        SpringApplication.run(PractiarmarApplication.class, args);
    }

}
