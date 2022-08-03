package p.zh.assembly.middleware.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色权限关系
 * </p>
 *
 * @author p.zh
 * @since 2022-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MiddlewareAuthPowerRoleRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色编号
     */
    private Long roleId;

    /**
     * 权限编号
     */
    private Long powerId;


    public static final String ID = "id";

    public static final String ROLE_ID = "role_id";

    public static final String POWER_ID = "power_id";

}
