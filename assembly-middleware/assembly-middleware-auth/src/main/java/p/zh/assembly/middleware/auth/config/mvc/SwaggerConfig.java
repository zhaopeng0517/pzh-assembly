package p.zh.assembly.middleware.auth.config.mvc;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * api文档
 * </p>
 *
 * @author p.zh
 * @since 2022-08-03
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
@ConditionalOnProperty(havingValue = "true", name = "swagger.enable")
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private boolean enableSwagger;

    @Value("${swagger.version}")
    private String version;

    @Bean
    public Docket createRestApi() {
        //http://ip地址:端口/项目名/swagger-ui.html#/
        ApiInfo apiInfo = new ApiInfoBuilder()
                //网站标题
                .title("AUTH API")
                //网站描述
                .description("")
                //版本
                .version(version)
                //联系人
                .contact(new Contact("p.zh", "", ""))
                //协议
                .license("The Apache License")
                //协议url
                .licenseUrl("")
                .build();

        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        ticketPar.name("T-HEAD-USER")
                .description("token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        pars.add(ticketPar.build());
        //swagger版本
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                //扫描那些controller
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo)
                // 是否开启swagger接口文档
                .enable(enableSwagger)
                .globalOperationParameters(pars);
    }

}
