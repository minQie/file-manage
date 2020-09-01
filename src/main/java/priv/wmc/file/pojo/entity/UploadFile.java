package priv.wmc.file.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import priv.wmc.file.base.entity.BaseEntity;

/**
 * 文件上传实体
 *
 * @author 王敏聪
 * @date 2019/11/18 10:16
 */
@Setter
@Getter
@TableName("upload_file")
public class UploadFile extends BaseEntity<UploadFile> {

    /**
     * 文件上传路径（例：D:/upload）
     */
    @NotNull
    String absolutePath;

    /**
     * 文件上传 初始文件名（不含拓展名）
     */
    @NotNull
    String originalName;

    /**
     * 文件上传 实际保存在磁盘上的文件名（不包含拓展名）
     */
    @NotNull
    String saveName;

    /**
     * 拓展名
     */
    String extension;

    /**
     * 文件大小（单位字节）
     */
    @NotNull
    Long size;

}
