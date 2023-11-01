package com.javayh.content.hub.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author haiji
 */
public class UniversalLongTypeHandler extends BaseTypeHandler<Object> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        if (parameter instanceof List) {
            // 处理 List<Long> 类型的数据
            List<Long> list = (List<Long>) parameter;
            // 将 list 转换为数据库需要的格式并设置到 PreparedStatement 中
            String stringValue = list.toString();
            ps.setString(i, stringValue);
        } else if (parameter instanceof Long) {
            // 处理 Long 类型的数据
            Long value = (Long) parameter;
            ps.setLong(i, value);
        } else {
            // 处理其他类型的数据
            throw new SQLException("Unsupported data type for UniversalLongTypeHandler.");
        }
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // 获取数据，并根据需要处理
        String value = rs.getString(columnName);
        return parseValue(value);
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // 获取数据，并根据需要处理
        String value = rs.getString(columnIndex);
        return parseValue(value);
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        // 获取数据，并根据需要处理
        String value = cs.getString(columnIndex);
        return parseValue(value);
    }

    private Object parseValue(String value) {
        if (value != null) {
            if (value.startsWith("[")) {
                // 数据是 List<Long> 类型的字符串
                String[] elements = value.substring(1, value.length() - 1).split(",");
                List<Long> result = new ArrayList<>();
                for (String element : elements) {
                    result.add(Long.parseLong(element.trim()));
                }
                return result;
            } else {
                // 数据是 Long 类型的字符串
                return Long.parseLong(value);
            }
        }
        return null;
    }
}
