package priv.wmc.file.config.verify.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import priv.wmc.file.config.verify.Verify;
import priv.wmc.file.config.verify.VerifyType;

/**
 * "Verify" annotation core validator with custom regex
 *
 * @author Wang Mincong
 * @date 2020-01-15 23:00:39
 *
 * @see CoreRegexRegulation
 */
public class StringValidator implements ConstraintValidator<Verify, String> {

    private boolean allowBlank;
    private VerifyType verifyType;

    @Override
    public void initialize(Verify constraintAnnotation) {
        verifyType = constraintAnnotation.verifyType();
        allowBlank = constraintAnnotation.allowBlank();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 非空校验
        if (StringUtils.isBlank(value)) {
            if (allowBlank) {
                return true;
            } else {
                changeTipMessage(context, VerifyType.NOT_BLANK);
                return false;
            }
        }

        // 实际规则的校验
        changeTipMessage(context, verifyType);
        return verifyType.getPattern().matcher(value).matches();
    }

    /**
     * 将校验类型的错误提示应用到注解属性“message”
     */
    private void changeTipMessage(ConstraintValidatorContext context, VerifyType verifyType) {
        // 禁用默认提示
        context.disableDefaultConstraintViolation();
        // 修改错误提示
        context.buildConstraintViolationWithTemplate(verifyType.getErrorCodes().getMessage()).addConstraintViolation();
    }

}
