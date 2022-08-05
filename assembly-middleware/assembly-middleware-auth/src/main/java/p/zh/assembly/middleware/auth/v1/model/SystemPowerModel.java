package p.zh.assembly.middleware.auth.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import p.zh.assembly.middleware.common.em.realize.AuthTypeEnum;

import java.util.List;

/**
 * 系统对应权限树
 *
 * @author 赵鹏
 * @date 2022/8/5
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemPowerModel {

    /**
     * 权限标识
     */
    private String authKey;
    /**
     * 权限备注/名称
     */
    private String authRemark;
    /**
     * 权限类型
     */
    private AuthTypeEnum authType;

    /**
     * 子集
     */
    private List<SystemPowerModel> children;
}
