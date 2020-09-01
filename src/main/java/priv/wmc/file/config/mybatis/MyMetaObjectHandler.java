package priv.wmc.file.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author Wang Mincong
 * @date 2020-08-17 10:21:02
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");

        // 创建时间
        final String gmtCreateFileName = "gmtCreate";
        Object gmtCreate = this.getFieldValByName(gmtCreateFileName, metaObject);
        if (gmtCreate == null) {
            this.strictInsertFill(metaObject, gmtCreateFileName, LocalDateTime.class, LocalDateTime.now());
        }

        // 版本号
        final String versionFieldName = "version";
        Object version = this.getFieldValByName(versionFieldName, metaObject);
        if(null == version){
            this.setFieldValByName(versionFieldName, 0L, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "gmtModified", LocalDateTime.class, LocalDateTime.now());
    }
}

