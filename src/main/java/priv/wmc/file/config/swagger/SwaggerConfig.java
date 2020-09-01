package priv.wmc.file.config.swagger;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import priv.wmc.file.config.FileSystemProperties;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger2 核心配置
 *
 * @author 王敏聪
 * @date 2019年11月20日14:21:05
 */
@Configuration
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SwaggerConfig {

    private final FileSystemProperties fileSystemProperties;

    @Bean
    public Docket apiWithGlobalToken() {
        // ui
        ApiInfo adminApiInfo = new ApiInfoBuilder()
            .title("Swagger 接口文档")
            .description("浪起来~")
            .version(fileSystemProperties.getApiVersion())
            .build();

        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("核心模块")
            .enable(fileSystemProperties.getSwaggerEnable())
            .apiInfo(adminApiInfo)
            .select()
            .apis(RequestHandlerSelectors.basePackage("priv.wmc"))
            .build();
    }

}
