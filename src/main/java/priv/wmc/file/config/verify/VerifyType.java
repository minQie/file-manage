package priv.wmc.file.config.verify;

import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import priv.wmc.file.config.exception.ApiErrorCodes;

/**
 * 校验类型
 *
 * @author Wang Mincong
 * @date 2020-01-15 23:04:16
 */
@Getter
@AllArgsConstructor
public enum VerifyType {

    /** 非null，非空 */
    NOT_BLANK(null, ApiErrorCodes.VERIFY_NOT_BLANK),
    /** 名称 */
    NAME(CoreRegexRegulation.NAME_PATTERN, ApiErrorCodes.VERIFY_NAME_ILLEGAL),
    /** 编号 */
    CODE(CoreRegexRegulation.CODE_PATTERN, ApiErrorCodes.VERIFY_CODE_ILLEGAL),
    /** 邮箱 */
    EMAIL(CoreRegexRegulation.EMAIL_PATTERN, ApiErrorCodes.VERIFY_EMAIL_ILLEGAL),
    /** 手机号、区号-电话号码 */
    PHONE(CoreRegexRegulation.PHONE_PATTERN, ApiErrorCodes.VERIFY_PHONE_ILLEGAL);

    /** 校验类型 */
    private Pattern pattern;

    /** 错误码 */
    private ApiErrorCodes errorCodes;

}
