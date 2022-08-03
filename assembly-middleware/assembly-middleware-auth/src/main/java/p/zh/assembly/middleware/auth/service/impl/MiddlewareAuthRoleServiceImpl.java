package p.zh.assembly.middleware.auth.service.impl;

import p.zh.assembly.middleware.auth.entity.MiddlewareAuthRole;
import p.zh.assembly.middleware.auth.mapper.MiddlewareAuthRoleMapper;
import p.zh.assembly.middleware.auth.service.IMiddlewareAuthRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色信息 服务实现类
 * </p>
 *
 * @author p.zh
 * @since 2022-08-03
 */
@Service
public class MiddlewareAuthRoleServiceImpl extends ServiceImpl<MiddlewareAuthRoleMapper, MiddlewareAuthRole> implements IMiddlewareAuthRoleService {

}
