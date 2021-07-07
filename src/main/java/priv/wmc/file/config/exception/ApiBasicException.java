package priv.wmc.file.config.exception;

import lombok.Getter;

/**
 * 基础异常类
 *
 * @author Wang Mincong
 * @date 2019/11/18 9:17
 */
@Getter
public class ApiBasicException extends RuntimeException implements ApiError {

    private final int code;
    private final String message;

    public ApiBasicException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiBasicException(ApiError apiError) {
        this(apiError.getCode(), apiError.getMessage());
    }

}
