package priv.wmc.file.config.exception.result;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import priv.wmc.file.config.exception.ApiErrorCodes;

/**
 * @author Wang Mincong
 * @date 2020-08-11 11:36:53
 */
public class MethodArgumentTypeMismatchExceptionResult extends ApiBasicExceptionResult {

    private final String param;
    private final String cause;

    public MethodArgumentTypeMismatchExceptionResult(MethodArgumentTypeMismatchException e) {
        super(ApiErrorCodes.PARAM_ERROR);
        param = e.getName();
        cause = e.getRootCause().getMessage();
    }

}
