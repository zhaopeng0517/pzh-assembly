package p.zh.assembly.middleware.common.em.standard;

/**
 * 接口枚举类型标准
 *
 * @author zhao.peng
 * @date 2022/8/3
 */
public interface BaseEnum {

    /**
     * 获取值
     * @return
     */
    int value();

    /**
     * 获取标识
     * @return
     */
    String getText();

    /**
     * 通过整数转换为指定类型枚举
     * @param eClass
     * @param val
     * @return
     * @param <E>
     */
    static <E extends BaseEnum> E getEnum(Class<E> eClass, Integer val) {
        if (val == null) {
            return null;
        }
        E[] es = eClass.getEnumConstants();
        if (es == null) {
            throw new IllegalArgumentException(eClass.getName() + " is not Enum");
        }
        for (E e : es) {
            if (e.value() == val) {
                return e;
            }
        }
        return null;
    }
}
