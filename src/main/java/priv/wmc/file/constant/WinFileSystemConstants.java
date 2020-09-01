package priv.wmc.file.constant;

/**
 * 文件系统的相关常量
 *
 * @author Wang Mincong
 * @date 2020-08-20 11:32:20
 */
public final class WinFileSystemConstants {

    private WinFileSystemConstants() {}

    /**
     * 保存上传文件夹的根目录，如果修改该值，需要注意：
     * 1、之前已经上传保存的文件需要转移
     * 2、之前已经上传保存到数据库的
     */
    public static final String ABSOLUTE_PATH = "D:/upload/";

    /** 存储上传图片的目录名 */
    public static final String PIC_DIR_NAME = "pic";

    /** 存储上传表格的目录名 */
    public static final String EXCEL_DIR_NAME = "excel";

    /** 存储上传其他类型文件的目录名 */
    public static final String OTHER_DIR_NAME = "other";

    /** 上传接口的url */
    public static final String UPLOAD_URL = "/file/upload";

    /** 下载接口的url */
    public static final String DOWNLOAD_URL = "/file/download";






















}
