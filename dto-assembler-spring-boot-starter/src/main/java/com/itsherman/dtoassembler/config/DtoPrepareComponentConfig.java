package com.itsherman.dtoassembler.config;

import com.itsherman.dtoassembler.factory.ClassDtoPrepareFactory;
import com.itsherman.dtoassembler.factory.InterfaceDtoPrepareFactory;
import com.itsherman.dtoassembler.handler.DtoModelDispatchHandler;
import com.itsherman.dtoassembler.handler.clazz.*;
import com.itsherman.dtoassembler.handler.inter.*;
import com.itsherman.dtoassembler.pool.DtoViewDefinitionPool;
import com.itsherman.dtoassembler.utils.SpringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DtoPrepareComponentConfig {

    @Bean
    public ClassDtoModelRegister classDtoModelRegister() {
        return new ClassDtoModelRegister();
    }

    @Bean
    public InterDtoModelRegister interDtoModelRegister() {
        return new InterDtoModelRegister();
    }

    @Bean
    public DtoClassModelPropertyDtoViewHandler dtoClassModelPropertyDtoViewHandler() {
        return new DtoClassModelPropertyDtoViewHandler();
    }

    @Bean
    public DtoInterModelPropertyDtoViewHandler dtoInterModelPropertyDtoViewHandler() {
        return new DtoInterModelPropertyDtoViewHandler();
    }

    @Bean
    public DtoClassPropertyParserClassHandler dtoClassPropertyParserClassHandler() {
        return new DtoClassPropertyParserClassHandler();
    }

    @Bean
    public DtoInterPropertyParserClassHandler dtoInterPropertyParserClassHandler() {
        return new DtoInterPropertyParserClassHandler();
    }

    @Bean
    public DtoClassPropertySourceClassHandler dtoClassPropertySourceClassHandler() {
        return new DtoClassPropertySourceClassHandler();
    }

    @Bean
    public DtoInterfacePropertySourceClassHandler dtoInterfacePropertySourceClassHandler() {
        return new DtoInterfacePropertySourceClassHandler();
    }

    @Bean
    public DtoClassPropertyValueHandler dtoClassPropertyValueHandler() {
        return new DtoClassPropertyValueHandler();
    }

    @Bean
    public DtoInterfacePropertyValueHandler dtoInterfacePropertyValueHandler() {
        return new DtoInterfacePropertyValueHandler();
    }

    @Bean
    public DtoClassViewRegister dtoClassViewRegister() {
        return new DtoClassViewRegister();
    }

    @Bean
    public DtoInterViewRegister dtoInterViewRegister() {
        return new DtoInterViewRegister();
    }

    @Bean
    public DtoViewDefinitionPool dtoViewDefinitionPool() {
        return new DtoViewDefinitionPool();
    }

    @Bean
    public ClassDtoPrepareFactory classDtoPrepareFactory() {
        return new ClassDtoPrepareFactory();
    }

    @Bean
    public InterfaceDtoPrepareFactory interfaceDtoPrepareFactory() {
        return new InterfaceDtoPrepareFactory();
    }

    @Bean(initMethod = "handle")
    public DtoModelDispatchHandler dtoModelDispatchHandler() {
        DtoModelDispatchHandler dtoModelDispatchHandler = new DtoModelDispatchHandler();
        return dtoModelDispatchHandler;
    }

    @Bean("dtoSpringUtils")
    public SpringUtils springUtils() {
        return new SpringUtils();
    }


}
