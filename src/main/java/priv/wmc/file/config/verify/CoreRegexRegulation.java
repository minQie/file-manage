package priv.wmc.file.config.verify;

import java.util.regex.Pattern;

/**
 * 校验规则定义
 *
 * @author Wang Mincong
 * @date 2019/11/5 17:27
 */
public final class CoreRegexRegulation {

    private CoreRegexRegulation() {}

    /** 名称：由中文、英文字母、数字、*·_ /-.,中文括号,英文括号,&,空格组成 */
    private static final String NAME_REGEX = "^[\u4e00-\u9fa5aa-zA-Z0-9\\s\\-,*._·&()（）]+$";
    /** 编号：由英文字母、数字、*·_ /-.,中文括号,英文括号,&,空格组成 */
    private static final String CODE_REGEX = "^[a-zA-Z0-9\\s\\-,*._·&()（）]+$";
    /** 邮箱： */
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    /** 号码：手机号、区号-电话号码 */
    private static final String PHONE_REGEX = "^(0\\d{2,3}-\\d{7,8})|(1[3456789]\\d{9})$";

    static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);
    static final Pattern CODE_PATTERN = Pattern.compile(CODE_REGEX);
    static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

}
