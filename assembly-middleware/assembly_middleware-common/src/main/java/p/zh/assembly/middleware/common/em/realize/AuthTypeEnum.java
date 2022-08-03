package p.zh.assembly.middleware.common.em.realize;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import p.zh.assembly.middleware.common.em.standard.BaseEnum;
/**
 * 权限类型
 *
 * @author zhao.peng
 * @date 2022/8/3
 */
public enum AuthTypeEnum implements BaseEnum {

    /**
     * 菜单展示类型
     */
    MENU(1 , "菜单类型") ,
    /**
     * 功能调用类型
     */
    BUTTON(2 , "功能按钮类型") ,
    ;

    private int value;

    private String text;

    AuthTypeEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    /**
     * mybatis Mapper中的方法参数如果为枚举的话, 会调用toString()方法获取枚举值, 用于ognl表达式判断
     *
     * @return
     */
    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    @JsonValue
    public int value() {
        return this.value;
    }

    public String getText() {
        return text;
    }

    @JsonCreator
    public static AuthTypeEnum getItem(Integer code) {
        return BaseEnum.getEnum(AuthTypeEnum.class, code);
    }
}
