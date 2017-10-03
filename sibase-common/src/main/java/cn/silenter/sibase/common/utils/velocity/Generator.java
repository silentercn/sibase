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
package cn.silenter.sibase.common.utils.velocity;

/**
 * 代码生成器。
 * <p>将此文件复制到需要生成的包下，执行后会自动生成相应的MVC三层基础代码</p>
 *
 * @author silenter
 * @version 1.0
 * @date 2017-10-01 15:13
 */
public class Generator {

    //根据命名规范修改此常量值即可
    private static String packageName = "cn.silenter.sibase.common.demo";
    private static String moduleName = "Demo";
    private static String author = "silenter";
    //数据库表的名称
    private static String tableName = "sibase_demo";
    //SQL SERVER的连接驱动 和 数据库连接
    private static String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String jdbcUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=sibase";
    //MySql 的连接驱动 和 数据库连接
    //private static String jdbcDriver = "com.mysql.jdbc.Driver";
    //private static String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/sibase?characterEncoding=utf8";
    private static String jdbcUser = "root";
    private static String jdbcPass = "123";

    /**
     * 自动生成代码。
     *
     * @param args
     * @author silenter
     * @date 2017-10-01 16:19
     */
    public static void main(String[] args) {
        //创建信息Bean
        VelocityDto velocityDto = new VelocityDto(packageName, moduleName, author, tableName, jdbcDriver, jdbcUrl, jdbcUser, jdbcPass);
        //生成代码
        new VelocityUtils().createFiles(velocityDto, Generator.class);
    }
}
