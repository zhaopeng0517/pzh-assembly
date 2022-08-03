package p.zh.assembly.middleware.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import p.zh.assembly.middleware.common.em.realize.ResourceStateEnum;

/**
 * <p>
 * 角色信息
 * </p>
 *
 * @author p.zh
 * @since 2022-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MiddlewareAuthRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色备注
     */
    private String roleRemark;

    /**
     * 角色状态
     */
    private ResourceStateEnum roleState;

    /**
     * 系统编码
     */
    private String systemCode;


    public static final String ID = "id";

    public static final String ROLE_NAME = "role_name";

    public static final String ROLE_REMARK = "role_remark";

    public static final String ROLE_STATE = "role_state";

    public static final String SYSTEM_CODE = "system_code";

}
