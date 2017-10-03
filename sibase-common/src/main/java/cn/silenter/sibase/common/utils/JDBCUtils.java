/**
 * Copyright (C) 2017-present silenter<silenter@126.com>
 *
 * This file is part of Sibase.
 *
 * Sibase is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * Sibase is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Sibase; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301 USA.
 *
 * An additional grant of patent rights can be found in the PATENTS file
 * in the root directory of this source tree.
 */
package cn.silenter.sibase.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * 数据库表字段转Bean属性内容。
 * <p>查询指定数据库表字段并转换为Bean的属性内容</p>
 *
 * @author silenter
 * @version 1.0
 * @date 2017-10-01 20:06
 */
public class JDBCUtils {
    private static final Logger logger = LoggerFactory.getLogger(JDBCUtils.class);

    /**
     * 获取数据模型（Do/Dto）的属性内容。
     *
     * @param JdbcDriver 数据库连接驱动
     * @param JdbcUrl 数据库连接地址
     * @param JdbcUser 数据库登录用户名
     * @param JdbcPass 数据库登录密码
     * @param tableName 数据库表名
     * @return java.lang.String[]
     * @author silenter
     * @date 2017-10-02 0:08
     */
    public String[] getAttributes(String JdbcDriver, String JdbcUrl, String JdbcUser, String JdbcPass, String tableName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String[] DoAndDto = new String[2];
        try {
            String sql = "SELECT * FROM " + tableName;
            Class.forName(JdbcDriver);
            connection = DriverManager.getConnection(JdbcUrl, JdbcUser, JdbcPass);
            preparedStatement = connection.prepareStatement(sql);
            ResultSetMetaData metaData = preparedStatement.getMetaData();
            int count = metaData.getColumnCount();
            String[] columnNames = new String[count];
            String[] columnTypeNames = new String[count];
            int[] columnSize = new int[count];
            if (logger.isInfoEnabled()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("数据库表：");
                stringBuilder.append(tableName);
                stringBuilder.append("的字段数量为：");
                stringBuilder.append(count);
                logger.info(stringBuilder.toString());
            }
            for (int i = 0; i < count; i++) {
                columnNames[i] = metaData.getColumnName(i + 1);
                columnTypeNames[i] = metaData.getColumnTypeName(i + 1);
                columnSize[i] = metaData.getColumnDisplaySize(i + 1);
            }
            DoAndDto[0] = getDoOrDto(columnNames, columnTypeNames, columnSize, count, true);
            DoAndDto[1] = getDoOrDto(columnNames, columnTypeNames, columnSize, count, false);
        } catch (Exception e) {
            e.printStackTrace();
            if (logger.isInfoEnabled()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("数据库表：");
                stringBuilder.append(tableName);
                stringBuilder.append("的查询失败，失败原因：");
                stringBuilder.append(e.getMessage());
                logger.info(stringBuilder.toString());
            }
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return DoAndDto;
    }

    /**
     * 获取Do或Dto的属性文本。
     *
     * @param columnNames
     * @param columnTypeNames
     * @param columnSize
     * @param count
     * @param isDto
     * @return java.lang.String
     * @author silenter
     * @date 2017-10-02 0:07
     */
    private String getDoOrDto(String[] columnNames, String[] columnTypeNames, int[] columnSize, int count, boolean isDto) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if ("auto_id".equals(columnNames[i].toLowerCase()) || "id".equals(columnNames[i].toLowerCase())
                    || "create_by".equals(columnNames[i].toLowerCase()) || "create_date".equals(columnNames[i].toLowerCase())
                    || "modified_by".equals(columnNames[i].toLowerCase()) || "modified_date".equals(columnNames[i].toLowerCase())) {
                continue;
            }
            if(!isDto){
                stringBuilder.append("    @Column(name = \"");
                stringBuilder.append(columnNames[i]);
                stringBuilder.append("\")\r\n");
            }
            if("Date".equals(columnTypeToJavaType(columnTypeNames[i]))){
                stringBuilder.append("    @DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")\r\n");
            }
            stringBuilder.append("    private ");
            stringBuilder.append(columnTypeToJavaType(columnTypeNames[i]));
            stringBuilder.append(" ");
            stringBuilder.append(columnNameToAttributeName(columnNames[i]));
            stringBuilder.append(";\r\n\r\n");
        }
        return stringBuilder.toString();
    }

    /**
     * 字段类型转换为Java类型。
     * <p>将数据库表的字段的类型转换为Java的数据类型</p>
     *
     * @param columnTypeName
     * @return java.lang.String
     * @author silenter
     * @date 2017-10-01 21:55
     */
    private String columnTypeToJavaType(String columnTypeName) {
        if ("nvarchar".equals(columnTypeName.toLowerCase()) || "varchar".equals(columnTypeName.toLowerCase())
                || "char".equals(columnTypeName.toLowerCase()) || "nchar".equals(columnTypeName.toLowerCase())) {
            return "String";
        }
        if ("int".equals(columnTypeName.toLowerCase()) || "number".equals(columnTypeName.toLowerCase())) {
            return "Long";
        }
        if ("date".equals(columnTypeName.toLowerCase()) || "datetime".equals(columnTypeName.toLowerCase()) || "timestamp".equals(columnTypeName.toLowerCase())) {
            return "Date";
        }
        if ("binary_double".equals(columnTypeName.toLowerCase())) {
            return "Double";
        }
        if ("binary_float".equals(columnTypeName.toLowerCase())) {
            return "Float";
        }
        if ("boolean".equals(columnTypeName.toLowerCase())) {
            return "Boolean";
        }
        if ("blob".equals(columnTypeName.toLowerCase())) {
            return "byte[]";
        }
        return "unKonw";
    }

    /**
     * 将字段名转换为实体类的属性名。
     * <p>去掉“is_”的前缀，以及将“_”后的首字母进行大写处理</p>
     *
     * @param columnName
     * @return java.lang.String
     * @author silenter
     * @date 2017-10-01 21:52
     */
    private String columnNameToAttributeName(String columnName) {
        String name = columnName;
        if (name.startsWith("is_")) {
            name = name.replace("is_", "");
        }
        while (name.indexOf("_") > -1) {
            int start = name.indexOf("_");
            String varchar = name.substring(start, start + 2);
            name = name.replace(varchar, varchar.replace("_", "").toUpperCase());
        }
        return name;
    }
}
