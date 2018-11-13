package org.sample.webapp.enums;

/**
 * 保存一些常量字符串与其状态码
 * 凡是命名带有OP的类都是和业务强相关的
 */
public enum OPEnum {

    REGISTER_SUCCESS(1, "注册成功"),
    PROFILE_EXISTED(0, "用户名已被注册"),
    CONTINUOUS_UNDERLINE(-1, "用户名含有连续的下划线"),
    ILLEGAL_USERNAME(-2, "无效用户名"),
    NULL_USERNAME(-3, "用户名不能为空"),
    ILLEGAL_PASSWORD(-4, "无效密码"),
    NULL_PASSWORD(-5, "密码不能为空"),
    NULL_NICKNAME(-6, "昵称不能为空"),
    INNER_ERROR(-7, "系统异常！");

    private int state;

    private String stateInfo;

    OPEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static OPEnum stateOf(int index) {
        for (OPEnum x : OPEnum.values()) {
            if (x.getState() == index) {
                return x;
            }
        }
        return null;
    }
}
