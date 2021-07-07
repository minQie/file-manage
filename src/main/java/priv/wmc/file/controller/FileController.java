package priv.wmc.file.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import priv.wmc.file.config.exception.ApiErrorCodes;
import priv.wmc.file.config.exception.ApiException;
import priv.wmc.file.constant.WinFileSystemConstants;
import priv.wmc.file.pojo.entity.UploadFile;
import priv.wmc.file.pojo.enums.UploadFileTypeEnum;
import priv.wmc.file.service.FileService;
import priv.wmc.file.util.UploadFilenameUtils;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 文件上传、下载
 *
 * @author Wang Mincong
 * @date 2019/11/14 14:51
 */
@Api(tags = "文件模块")
@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FileController {

    private final UploadFilenameUtils uploadFilenameUtils;
    private final FileService fileService;

    /**
     * 上传文件（上传文件的键“file”）
     *
     * @return 访问上传文件的url
     */
    @ApiOperation("文件上传")
    @ApiImplicitParam(name = "file", value = "要上传的文件", dataTypeClass = MultipartFile.class, required = true)
    @PostMapping(WinFileSystemConstants.UPLOAD_URL)
    public String upload(MultipartFile file) {
        fileService.check(file);

        try {
            return fileService.save(file);
        } catch (IOException e) {
            throw new ApiException(ApiErrorCodes.FILE_UPLOAD_FAIL, e.getMessage());
        }
    }

    /**
     * 下载文件
     *
     * @param fileName  文件实际存储名
     * @param extension 文件后缀名
     * @param response  Spring注入
     */
    @ApiOperation("文件下载")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "saveName", value = "文件名", dataTypeClass = String.class, required = true),
        @ApiImplicitParam(name = "extension", value = "拓展名", dataTypeClass = String.class, required = true)
    })
    @GetMapping(WinFileSystemConstants.DOWNLOAD_URL + "/{saveName}.{extension}")
    public void download(@PathVariable String saveName, @PathVariable String extension, @ApiIgnore HttpServletResponse response) throws IOException {
        // 1、文件信息查询
        UploadFile uploadFile = fileService.findBySaveNameAndExtension(saveName, extension, false);
        if (uploadFile == null) {
            fileNotFoundDeal(extension, response);
            return;
        }

        // 2、文件存在校验
        File file = new File(UploadFilenameUtils.getDiskAbsoluteUrl(uploadFile));
        if (!file.exists()) {
            fileNotFoundDeal(extension, response);
            return;
        }

        // 3、设置响应头，将文件写入输出流
        // 响应类型：.*（ 二进制流，不知道下载文件类型）
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        // 浏览器弹出下载框
        response.setHeader("Content-Disposition", "attachment;filename=" + "\"" + UploadFilenameUtils.getSaveNameWithExtension(uploadFile) + "\"");
        // 文件大小
        response.setContentLengthLong(FileUtils.sizeOf(file));
        response.getOutputStream().write(FileUtils.readFileToByteArray(file));
    }

    private void fileNotFoundDeal(String extension, HttpServletResponse response) throws IOException {
        UploadFileTypeEnum uploadFileType = uploadFilenameUtils.getTypeByExtension(extension, true);
        // 如果是图片没找到，就返回一个默认图片
        if (uploadFileType == UploadFileTypeEnum.PIC) {
            File defaultImage = ResourceUtils.getFile("classpath:default/noimage.png");
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            response.setHeader("Content-Disposition", "attachment;filename=" + "\"noimage.png\"");
            response.getOutputStream().write(FileUtils.readFileToByteArray(defaultImage));
        } else {
            throw new ApiException(ApiErrorCodes.FILE_ALREADY_DELETE);
        }
    }

}
