package p.zh.assembly.middleware.auth.config.db.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import p.zh.assembly.middleware.common.em.standard.BaseEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库实体枚举映射
 *
 * @author zhao.peng
 * @date 2022/8/3
 */
@MappedTypes({BaseEnum.class})
public class MybatisBaseEnumsTypeHandler<E extends BaseEnum> extends BaseTypeHandler<E> {

    private Class<E> eClass;

    public MybatisBaseEnumsTypeHandler(Class<E> eClass) {
        if (eClass == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.eClass = eClass;

    }

    public MybatisBaseEnumsTypeHandler() {
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.value());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int i = rs.getInt(columnName);
        if (i == 0 && rs.wasNull()) {
            return null;
        }
        try {
            return (E) BaseEnum.getEnum(eClass, i);
        } catch (Exception e) {
            throw new IllegalArgumentException("cannot convert " + i + " to " + e.getClass().getSimpleName(), e);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int i = rs.getInt(columnIndex);
        if (i == 0 && rs.wasNull()) {
            return null;
        }
        try {
            return (E) BaseEnum.getEnum(eClass, i);
        } catch (Exception e) {
            throw new IllegalArgumentException("cannot convert " + i + " to " + e.getClass().getSimpleName(), e);
        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int i = cs.getInt(columnIndex);
        if (i == 0 && cs.wasNull()) {
            return null;
        }
        try {
            return (E) BaseEnum.getEnum(eClass, i);
        } catch (Exception e) {
            throw new IllegalArgumentException("cannot convert " + i + " to " + e.getClass().getSimpleName(), e);
        }
    }
}
