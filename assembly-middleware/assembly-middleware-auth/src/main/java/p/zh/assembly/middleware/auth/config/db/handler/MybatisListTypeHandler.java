/**
 * dataofx.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package p.zh.assembly.middleware.auth.config.db.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * 数据库结果list类型转换
 * @author zhao.peng
 * @date 2022/8/3
 */
@MappedTypes({List.class})
public class MybatisListTypeHandler extends BaseTypeHandler<List<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List parameter, JdbcType jdbcType) throws SQLException {
        throw new RuntimeException();
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String v = rs.getString(columnName);
        if (StringUtils.isEmpty(v)) {
            return null;
        } else {
            String[] ele = v.split(",");
            return Arrays.asList(ele);
        }
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String v = rs.getString(columnIndex);
        if (StringUtils.isEmpty(v)) {
            return null;
        } else {
            String[] ele = v.split(",");
            return Arrays.asList(ele);
        }
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String v = cs.getString(columnIndex);
        if (StringUtils.isEmpty(v)) {
            return null;
        } else {
            String[] ele = v.split(",");
            return Arrays.asList(ele);
        }
    }
}