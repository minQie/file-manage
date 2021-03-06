package priv.wmc.file.config.exception;

/**
 * 错误相关的枚举都要实现的接口
 *
 * @author Wang Mincong
 * @date 2020-01-15 23:24:22
 */
public interface ApiError {

    /**
     * 返回错误码
     *
     * @return 错误码
     */
    int getCode();

    /**
     * 返回错误信息
     *
     * @return 错误信息
     */
    String getMessage();

}
