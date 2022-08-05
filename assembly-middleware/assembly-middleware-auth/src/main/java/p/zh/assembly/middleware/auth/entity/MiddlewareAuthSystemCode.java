package p.zh.assembly.middleware.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import p.zh.assembly.middleware.common.em.realize.ResourceStateEnum;

/**
 * <p>
 * 系统编码明细
 * </p>
 *
 * @author p.zh
 * @since 2022-08-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MiddlewareAuthSystemCode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 系统编码
     */
    private String systemCode;

    /**
     * 状态
     */
    private ResourceStateEnum systemState;


    public static final String ID = "id";

    public static final String SYSTEM_CODE = "system_code";

    public static final String SYSTEM_STATE = "system_state";

}
