package com.mycompany.app.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FormatValidatorTest {

    @Test
    public void validateUsername() {
        // 合法用户名
        assertTrue(FormatValidator.validateUsername("cool132332").isSuccess());
        assertTrue(FormatValidator.validateUsername("courage_1997").isSuccess());
        // 非法用户名
        assertFalse(FormatValidator.validateUsername("das__dsa").isSuccess());
        assertFalse(FormatValidator.validateUsername("王大将军").isSuccess());
        assertFalse(FormatValidator.validateUsername("cou").isSuccess());
        assertFalse(FormatValidator.validateUsername("courage_199722222222").isSuccess());
        assertFalse(FormatValidator.validateUsername("111111111111").isSuccess());
    }

    @Test
    public void validatePassword() {
        // 合法密码
        assertTrue(FormatValidator.validatePassword("123123").isSuccess());
        assertTrue(FormatValidator.validatePassword("cu23age_123").isSuccess());
        // 非法密码
        assertFalse(FormatValidator.validatePassword("12344").isSuccess());
        assertFalse(FormatValidator.validatePassword("啊好的啦开始觉得").isSuccess());
        assertFalse(FormatValidator.validatePassword("123442222222___222222").isSuccess());
    }
}
