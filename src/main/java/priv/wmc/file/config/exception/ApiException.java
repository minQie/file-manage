package priv.wmc.file.config.exception;

import lombok.Getter;

/**
 * @author Wang Mincong
 * @date 2019/11/18 9:43
 */
@Getter
public class ApiException extends ApiBasicException {

    private String extraMessage;

    public ApiException(ApiError apiError) {
        super(apiError);
    }

    public ApiException(ApiError apiError, String extraMessage) {
        super(apiError);
        this.extraMessage = extraMessage;
    }

}
