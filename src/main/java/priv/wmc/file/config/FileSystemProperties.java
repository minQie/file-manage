package priv.wmc.file.config;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Wang Mincong
 * @date 2020-08-23 08:19:07
 */
@Getter
@Setter
@ConfigurationProperties("upload")
public class FileSystemProperties {

    /**
     * 项目实际部署运行的服务器域名
     */
    @Value("${domain:http://127.0.0.1:8080}")
    private String domain;

    /**
     * 是否开启swagger
     */
    // 1、可以直接注值
    // @Value("true")

    // 2、报错找不到对应的配置值：java.lang.IllegalArgumentException: Could not resolve placeholder 'swaggerEnable' in value "${swaggerEnable}"
    // 即：必须完全匹配
    // @Value("${swaggerEnable}")

    // 3、不报错，通过:符号表示设置默认值，因为读不到设置了一个默认值null
    // @Value("${swaggerEnable:}")

    // Boolean类型也不行设置null值？：java.lang.IllegalArgumentException: Invalid boolean value [null]
    // @Value("${swaggerEnable:null}")

    // 成功
    // @Value("${swaggerEnable:}")

    // 报错：Caused by: java.lang.IllegalArgumentException: Invalid boolean value []
    // @Value("${swaggerEnable:}")
    // private boolean swaggerEnable;

    @Value("${swagger-enable:true}")
    private Boolean swaggerEnable;

    /**
     * api version
     */
    @Value("${api-version:v1.0}")
    private String apiVersion;

    /** 文件上传支持的图片格式  */
    private List<String> picSupport;

    /** 文件上传支持的表格格式 */
    private List<String> excelSupport;

    /** 文件上传支持的其他文件格式 */
    private List<String> otherSupport;

}
