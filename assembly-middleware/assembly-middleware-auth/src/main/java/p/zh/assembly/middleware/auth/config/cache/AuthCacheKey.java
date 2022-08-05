package p.zh.assembly.middleware.auth.config.cache;

/**
 * 缓存键
 *
 * @author 赵鹏
 * @date 2022/8/5
 */
public class AuthCacheKey {

    /**
     * 系统编码对应权限树
     * @param systemCode
     * @return
     */
    public static String powerTreeOfSystem(String systemCode) {
        return String.format("assembly:middleware:auth:power:tree:%s" , systemCode);
    }
}
