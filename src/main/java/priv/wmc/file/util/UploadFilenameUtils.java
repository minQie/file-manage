package priv.wmc.file.util;

import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import priv.wmc.file.config.FileSystemProperties;
import priv.wmc.file.config.exception.ApiErrorCodes;
import priv.wmc.file.config.exception.ApiException;
import priv.wmc.file.constant.WinFileSystemConstants;
import priv.wmc.file.pojo.entity.UploadFile;
import priv.wmc.file.pojo.enums.UploadFileTypeEnum;

/**
 * 文件工具类
 *
 * @author 王敏聪
 * @date 2019年11月29日13:47:19
 */
@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
public final class UploadFilenameUtils {

    private final FileSystemProperties fileSystemProperties;

    /**
     * 获取文件名（不包括“.拓展名”）
     *
     * @param multipartFileOriginalName multipartFile.getOriginalName()
     * @return filename
     */
    public static String getFileName(@NonNull String multipartFileOriginalName) {
        Assert.notNull(multipartFileOriginalName, "param multipartFileOriginalName can not be null");

        int lastPointIndex = multipartFileOriginalName.lastIndexOf(".");
        return multipartFileOriginalName.substring(0, lastPointIndex);
    }

    /**
     * 获取文件拓展名（后缀名）
     *
     * @param multipartFileOriginalName multipartFile.getExtension()
     * @return ension
     */
    public static String getExtension(@NonNull String multipartFileOriginalName) {
        Assert.notNull(multipartFileOriginalName, "param multipartFileOriginalName can not be null");

        int lastPointIndex = multipartFileOriginalName.lastIndexOf(".");
        return multipartFileOriginalName.substring(lastPointIndex + 1);
    }

    /**
     * 获取访问文件的文件名（带有拓展名）
     */
    public static String getSaveNameWithExtension(UploadFile uploadFile) {
        return uploadFile.getSaveName() + "." + uploadFile.getExtension();
    }

    /**
     * 获取文件在硬盘上的绝对路径
     */
    public static String getDiskAbsoluteUrl(UploadFile uploadFile) {
        return uploadFile.getAbsolutePath() + getSaveNameWithExtension(uploadFile);
    }

    /**
     * 获取文件的访问下载url
     */
    public String getDownloadUrl(UploadFile uploadFile) {
        return fileSystemProperties.getDomain() + WinFileSystemConstants.DOWNLOAD_URL + "/" + getSaveNameWithExtension(uploadFile);
    }

    public static String getSaveDirNameByType(@NonNull UploadFileTypeEnum fileType) {
        Assert.notNull(fileType, "Param fileType can not be null");

        switch (fileType) {
            case PIC:
                return WinFileSystemConstants.PIC_DIR_NAME;
            case EXCEL:
                return WinFileSystemConstants.EXCEL_DIR_NAME;
            case OTHER:
                return WinFileSystemConstants.OTHER_DIR_NAME;
            default:
                // NEVER REACH HERE
                throw new IllegalArgumentException("错误的枚举参数值:" + fileType.name());
        }
    }

    public UploadFileTypeEnum getType(UploadFile uploadFile, boolean throwExceptionWhenNotMatch) {
        return getTypeByExtension(uploadFile.getExtension(), throwExceptionWhenNotMatch);
    }

    /**
     * 获取文件类型
     */
    public UploadFileTypeEnum getType(MultipartFile file, boolean throwExceptionWhenNotMatch) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        return getTypeByExtension(extension, throwExceptionWhenNotMatch);
    }

    /**
     * 根据拓展名获取文件类型
     */
    public UploadFileTypeEnum getTypeByExtension(String extension, boolean throwExceptionWhenNotMatch) {
        if (fileSystemProperties.getPicSupport().contains(extension)) {
            return UploadFileTypeEnum.PIC;
        }
        if (fileSystemProperties.getExcelSupport().contains(extension)) {
            return UploadFileTypeEnum.EXCEL;
        }
        if (fileSystemProperties.getOtherSupport().contains(extension)) {
            return UploadFileTypeEnum.OTHER;
        }
        if (throwExceptionWhenNotMatch) {
            throw new ApiException(ApiErrorCodes.PARAM_ERROR);
        }
        return null;
    }

}