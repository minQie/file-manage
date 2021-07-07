package priv.wmc.file.config;

import java.time.format.DateTimeFormatter;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import lombok.Data;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import priv.wmc.file.config.convertor.EnumConverterFactory;
import priv.wmc.file.config.convertor.InstantConverter;
import priv.wmc.file.constant.StaticConstants;
import priv.wmc.file.constant.WinFileSystemConstants;

/**
 *
 *
 * @author Wang Mincong
 * @date 2020-01-31 08:46:21
 */
@Data
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler(WinFileSystemConstants.DOWNLOAD_URL + "/**")
            .addResourceLocations("classpath:default/noimage.png")
            .resourceChain(false);
    }

    /**
     * Get请求方式的Instant和Enum的自定义反序列化规则
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setDateFormatter(DateTimeFormatter.ofPattern(StaticConstants.DATE_PATTERN));
        registrar.setTimeFormatter(DateTimeFormatter.ofPattern(StaticConstants.TIME_PATTERN));
        registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern(StaticConstants.DATE_TIME_PATTERN));
        registrar.registerFormatters(registry);

        // Instant
        registry.addConverter(new InstantConverter(StaticConstants.DATE_TIME_PATTERN, StaticConstants.TIMEZONE));

        // Enum
        registry.addConverterFactory(new EnumConverterFactory());
    }

    /**
     * 跨域支持
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/api/**")
            .allowedOrigins("*")
            .allowCredentials(true)
            .allowedHeaders("content-type, authorization")
            .allowedMethods(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name(),
                HttpMethod.OPTIONS.name(),
                HttpMethod.PATCH.name()
            );
    }

    /**
     * JSR303的校验器<br>
     */
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
            .configure()
            .failFast(false)
            .buildValidatorFactory();
        return validatorFactory.getValidator();
    }

}

