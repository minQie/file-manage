package priv.wmc.file.config.exception.result;

import lombok.Getter;
import priv.wmc.file.config.exception.ApiError;

/**
 * 错误信息字段：message、extraMessage
 *
 * @author Wang Mincong
 * @date 2019/11/18 9:32
 */
@Getter
public class ApiExceptionResult extends ApiBasicExceptionResult {

    private final String extraMessage;

    public ApiExceptionResult(ApiError apiError, String extraMessage) {
        super(apiError);
        this.extraMessage = extraMessage;
    }

}
