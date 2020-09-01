package priv.wmc.file.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import priv.wmc.file.base.enums.EnumDefine;

/**
 * @author Wang Mincong
 * @date 2020-08-23 19:54:29
 */
@Getter
@AllArgsConstructor
public enum UploadFileTypeEnum implements EnumDefine {

    PIC(1, "图片"),
    EXCEL(2, "表格"),
    OTHER(3, "其他");

    int value;
    String description;

}
