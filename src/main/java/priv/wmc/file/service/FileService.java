package priv.wmc.file.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import priv.wmc.file.config.exception.ApiErrorCodes;
import priv.wmc.file.config.exception.ApiException;
import priv.wmc.file.pojo.entity.UploadFile;
import priv.wmc.file.pojo.enums.UploadFileTypeEnum;
import priv.wmc.file.util.DateUtils;
import priv.wmc.file.util.UploadFileUploadUtils;
import priv.wmc.file.util.UploadFilenameUtils;

/**
 * @author 王敏聪
 * @date 2019/11/17 21:04
 */
@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class FileService {

    private final UploadFilenameUtils uploadFilenameUtils;

    public void check(@Nullable MultipartFile file) {
        // 文件是否为空进行校验
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("上传的文件为空");
        }

        // 文件名是否为空进行校验
        String fileOriginalFilename = file.getOriginalFilename();
        if (StringUtils.isBlank(fileOriginalFilename)) {
            throw new IllegalArgumentException("上传文件的文件名不能为空");
        }

        // 文件类型校验（这里边有个是否支持空后缀名的问题、以及不同后缀名文件的存放目录名问题）
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        uploadFilenameUtils.getTypeByExtension(extension, true);
    }

    public String save(@NonNull @NotNull MultipartFile file) throws IOException {
        String fileOriginalFilename = file.getOriginalFilename();
        assert fileOriginalFilename != null;

        String extension = UploadFilenameUtils.getExtension(fileOriginalFilename);
        UploadFileTypeEnum uploadFileType = uploadFilenameUtils.getType(file, true);

        // 准备保存文件的文件夹
        String relativeDirectoryPath = DateUtils.getCurrentDateString() + File.separator;
        File uploadDirectory = UploadFileUploadUtils.prepareUploadDirectory(relativeDirectoryPath, uploadFileType);

        // 文件名
        String saveName = UUID.randomUUID().toString();
        String fileNameWithExtension = saveName + "." + extension;

        // 1、保存文件
        UploadFileUploadUtils.upload(file, uploadDirectory, fileNameWithExtension);

        // 2、保存文件相关信息
        UploadFile uploadFile = new UploadFile();
        uploadFile.setAbsolutePath(uploadDirectory.getAbsolutePath() + File.separator);
        uploadFile.setOriginalName(UploadFilenameUtils.getFileName(fileOriginalFilename));
        uploadFile.setSaveName(saveName);
        uploadFile.setExtension(UploadFilenameUtils.getExtension(fileOriginalFilename));
        uploadFile.setSize(file.getSize());
        uploadFile.insert();

        // 3、返回访问文件的url
        return uploadFilenameUtils.getDownloadUrl(uploadFile);
    }

    public UploadFile findBySaveNameAndExtension(@NonNull String saveName, @NonNull String extension, boolean throwExceptionWhenNotFound) {
        Assert.hasText(saveName, "saveName can not be empty");
        Assert.hasText(extension, "extension can not be empty");

        // 想要使用 ar（Active Record），是需要在项目中定于和数据表实体对应的Mapper的，否则将得到 Tableinfo cache not found
        QueryWrapper<UploadFile> uploadFileQueryWrapper = new QueryWrapper<UploadFile>()
            .eq("save_name", saveName)
            .eq("extension", extension);

        UploadFile uploadFile = new UploadFile().selectOne(uploadFileQueryWrapper);

        if (uploadFile == null && throwExceptionWhenNotFound) {
            throw new ApiException(ApiErrorCodes.FILE_NOT_EXIST, "不存在名为 " + saveName + " 的文件!");
        }
        return uploadFile;
    }

}