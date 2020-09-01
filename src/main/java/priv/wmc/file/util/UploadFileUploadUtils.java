package priv.wmc.file.util;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import priv.wmc.file.constant.WinFileSystemConstants;
import priv.wmc.file.pojo.enums.UploadFileTypeEnum;

/**
 * @author Wang Mincong
 * @date 2019-11-15 11:34:00
 */
public final class UploadFileUploadUtils {

    private UploadFileUploadUtils() {}

    /**
     * 序列化文件到指定目录，并返回文件名
     */
    public static void upload(@NonNull MultipartFile file, File uploadDirectory, String fileNameWithExtension) throws IOException {
        Assert.notNull(file, "upload file can not be null");

        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(uploadDirectory, fileNameWithExtension));
    }

    /**
     * 返回文件的存储目录
     */
    public static File prepareUploadDirectory(String relativeDirectoryPath, UploadFileTypeEnum uploadFileType) throws IOException {
        String directoryName = UploadFilenameUtils.getSaveDirNameByType(uploadFileType);

        File fileUploadDirectory = new File(WinFileSystemConstants.ABSOLUTE_PATH + directoryName + File.separator + relativeDirectoryPath);

        /*
         * 前提知识：windows下不允许同一目录下存在同名文件（如果存在“abc.txt”，你是创建不了名为“abc”的文件夹的）
         * 源码逻辑如下：
         * 如果同名文件存在：是文件夹 → true
         * 如果同名文件不存在：(创建目录成功) | (创建目录失败 → 目标文件存在且是一个目录) -> true
         */
        FileUtils.forceMkdir(fileUploadDirectory);
        return fileUploadDirectory;
    }

}
