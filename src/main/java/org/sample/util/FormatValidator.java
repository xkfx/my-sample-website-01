package org.sample.util;

import org.sample.dto.Validation;
import org.sample.enums.OnesProfileEnum;

import java.util.regex.Pattern;

/**
 * 封装数据格式方面的约束条件
 */
public class FormatValidator {

    private static final Pattern USERNAME = Pattern.compile("^[a-zA-Z]\\w{2,13}[a-zA-z0-9]$");
    private static final Pattern PASSWORD = Pattern.compile("^\\w{6,14}$");
    private static final Pattern CONTINUOUS_UNDERLINE = Pattern.compile("^\\w+__\\w+$");

    private static boolean containContinuousUnderline(final String x) {
        return CONTINUOUS_UNDERLINE.matcher(x).matches();
    }

    public static boolean checkNotNull(final String x) {
        return x != null && !x.equals("");
    }

    /**
     * 返回一个DTO对象封装验证结果，成功返回true，失败返回
     * 具体的失败信息。详细规则如下：用户名必须以字母开头，
     * 由4~15个英文字母、数字、下划线组成，不能含有连续下
     * 划线、空格，且不能以下划线结尾。
     */
    public static Validation validateUsername(final String username) {
        if (checkNotNull(username)) {
            if (!USERNAME.matcher(username).matches()) {
                return new Validation(false, OnesProfileEnum.ILLEGAL_USERNAME);
            }
            if (containContinuousUnderline(username)) {
                return new Validation(false, OnesProfileEnum.CONTINUOUS_UNDERLINE);
            }
            return new Validation(true);
        } else {
            return new Validation(false, OnesProfileEnum.NULL_USERNAME);
        }
    }

    /**
     * 返回一个DTO对象封装验证结果，成功返回true，失败返回
     * 具体的失败信息。详细规则如下：
     * 密码由6~14位任意大小写字母、数字、下划线组成
     */
    public static Validation validatePassword(final String password) {
        if (checkNotNull(password)) {
            if (PASSWORD.matcher(password).matches()) {
                return new Validation(true);
            }
            return new Validation(false, OnesProfileEnum.ILLEGAL_PASSWORD);
        } else {
            return new Validation(false, OnesProfileEnum.NULL_PASSWORD);
        }
    }

    public static Validation validateNickname(final String nickname) {
        if (checkNotNull(nickname)) {
            return new Validation(true);
        } else {
            return new Validation(false, OnesProfileEnum.NULL_NICKNAME);
        }
    }

    public static Validation validateRegistration(final String username, final String password, final String nickname) {
        Validation result = validateUsername(username);
        if (!result.isSuccess()) return result;
        result = validatePassword(password);
        if (!result.isSuccess()) return result;
        result = validateNickname(nickname);
        if (!result.isSuccess()) return result;
        return new Validation(true);
    }

}
