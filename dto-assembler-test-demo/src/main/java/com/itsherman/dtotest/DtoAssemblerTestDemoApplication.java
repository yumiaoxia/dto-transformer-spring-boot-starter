package com.itsherman.dtotest;

import com.itsherman.dtoassembler.annotations.DtoMapping;
import com.itsherman.dtoassembler.annotations.EnableDtoMapping;
import com.itsherman.dtoassembler.config.DtoPrepareComponentConfig;
import com.itsherman.dtoassembler.config.DtoWebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableDtoMapping(
        mappings = {@DtoMapping(basePackage = "com.itsherman.dtotest.web.dto", isRecursion = false)})
@SpringBootApplication
@Import({DtoPrepareComponentConfig.class, DtoWebConfig.class})
public class DtoAssemblerTestDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DtoAssemblerTestDemoApplication.class, args);
    }

}


