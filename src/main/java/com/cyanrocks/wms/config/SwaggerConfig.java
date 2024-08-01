package com.cyanrocks.wms.config;

import com.cyanrocks.common.em.ResCodeEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author wjq
 * @Date 2024/7/23 17:04
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(ResCodeEnum.INTERNAL_ERROR.getErrCode())
                .message(ResCodeEnum.INTERNAL_ERROR.getErrMsg()).responseModel(new ModelRef(ResCodeEnum.INTERNAL_ERROR.getErrMsg())).build());
        responseMessageList.add(new ResponseMessageBuilder().code(ResCodeEnum.INVALID_CLIENT.getErrCode())
                .message(ResCodeEnum.INVALID_CLIENT.getErrMsg())
                .responseModel(new ModelRef(ResCodeEnum.INVALID_CLIENT.getErrMsg())).build());
        responseMessageList.add(new ResponseMessageBuilder().code(ResCodeEnum.SUCCESS.getErrCode())
                .message(ResCodeEnum.SUCCESS.getErrMsg()).responseModel(new ModelRef(ResCodeEnum.SUCCESS.getErrMsg())).build());

        return new Docket(DocumentationType.SWAGGER_2)
                // 去掉swagger默认的状态码
                .useDefaultResponseMessages(false).globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList).select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any()).build()
                .apiInfo(apiInfo());
    }

    /**
     * 这个方法主要是写一些文档的描述
     */
    private ApiInfo apiInfo() {
        return new ApiInfo("peidi-wms docker服务", "peidi-wms服务的API接口文档", "1.0", "",
                new Contact("王家琦", "", "1084948949@qq.com"), "", "", Collections.emptyList());
    }
}
