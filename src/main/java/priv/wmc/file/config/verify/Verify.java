package priv.wmc.file.config.verify;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import priv.wmc.file.config.verify.validator.StringValidator;

/**
 * 默认含有非空校验
 *
 * 封装校验类型、可指定请求方式进行校验
 *
 * @author Wang Mincong
 * @Date 2019/11/6 11:43
 */
@Constraint(validatedBy = StringValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
public @interface Verify {

    String message() default "change this property's value is useless";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    /** 校验类型 */
    VerifyType verifyType();

    /** 是否允许为空（null、空串） */
    boolean allowBlank() default false;

}
