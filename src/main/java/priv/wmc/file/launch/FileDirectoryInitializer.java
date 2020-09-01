package priv.wmc.file.launch;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import priv.wmc.file.constant.WinFileSystemConstants;

/**
 * 存储上传文件的目录初始化
 *
 * @author Wang Mincong
 * @date 2020-08-28 15:59:36
 */
@Component
public class FileDirectoryInitializer implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        File fileUploadDirectory = new File(WinFileSystemConstants.ABSOLUTE_PATH);
        FileUtils.forceMkdir(fileUploadDirectory);
    }
}
