package p.zh.assembly.middleware.auth.v1.logic;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import p.zh.assembly.middleware.auth.config.cache.AuthCacheKey;
import p.zh.assembly.middleware.auth.entity.MiddlewareAuthPower;
import p.zh.assembly.middleware.auth.service.IMiddlewareAuthPowerService;
import p.zh.assembly.middleware.auth.v1.model.SystemPowerModel;
import p.zh.assembly.middleware.common.cache.standard.MiddlewareCache;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限信息相关逻辑
 *
 * @author 赵鹏
 * @date 2022/8/5
 */
@Service
public class MiddlewareAuthPowerLogicV1 {

    @Resource
    private MiddlewareCache middlewareCache;

    @Resource
    private IMiddlewareAuthPowerService iMiddlewareAuthPowerService;
    /**
     * 获取指定系统编码的权限树(重新构建)
     * @param systemCode
     * @return
     */
    public List<SystemPowerModel> buildPowerTreeBySystemCode(String systemCode) {
        //获取当前系统编码对应的全部权限
        List<MiddlewareAuthPower> allPower = this.iMiddlewareAuthPowerService.list(
                new QueryWrapper<MiddlewareAuthPower>()
                .eq(MiddlewareAuthPower.SYSTEM_CODE , systemCode)
        );
        return allPower.stream().filter(f -> f.getAuthParentId() == null)
                .map(m -> this.buildPowerTreeByRecursion(m , allPower))
                .collect(Collectors.toList());
    }

    /**
     * 递归构建权限树
     * @param base
     * @param allPower
     * @return
     */
    protected SystemPowerModel buildPowerTreeByRecursion(MiddlewareAuthPower base , List<MiddlewareAuthPower> allPower) {
        return SystemPowerModel.builder()
                .authType(base.getAuthType())
                .authKey(base.getAuthKey())
                .authRemark(base.getAuthRemark())
                .children(
                        allPower.stream().filter(f -> f.getAuthParentId().equals(base.getId()))
                                .map(m -> buildPowerTreeByRecursion(m , allPower))
                                .collect(Collectors.toList())
                )
                .build();
    }

    /**
     * 获取指定系统编码的权限树(从缓存，如果不存在，则重新构建)
     * @param systemCode
     * @return
     */
    public List<SystemPowerModel> getPowerTreeBySystemCode(String systemCode) {
        String powerCache = this.middlewareCache.getValue(AuthCacheKey.powerTreeOfSystem(systemCode));
        if(StrUtil.isBlank(powerCache)) {
            List<SystemPowerModel> build = this.buildPowerTreeBySystemCode(systemCode);
            this.middlewareCache.setValue(AuthCacheKey.powerTreeOfSystem(systemCode) , JSON.toJSONString(build));
            return build;
        }
        return JSONArray.parseArray(powerCache , SystemPowerModel.class);
    }
}
