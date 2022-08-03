package p.zh.assembly.middleware.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import p.zh.assembly.middleware.common.em.realize.AuthTypeEnum;

/**
 * <p>
 * 权限明细
 * </p>
 *
 * @author p.zh
 * @since 2022-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MiddlewareAuthPower implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 权限标识
     */
    private String authKey;

    /**
     * 权限备注
     */
    private String authRemark;

    /**
     * 父级 ， 根节点为 Null
     */
    private Long authParentId;

    /**
     * 权限类型
     */
    private AuthTypeEnum authType;

    /**
     * 接入系统编码
     */
    private String systemCode;


    public static final String ID = "id";

    public static final String AUTH_KEY = "auth_key";

    public static final String AUTH_REMARK = "auth_remark";

    public static final String AUTH_PARENT_ID = "auth_parent_id";

    public static final String AUTH_TYPE = "auth_type";

    public static final String SYSTEM_CODE = "system_code";

}
