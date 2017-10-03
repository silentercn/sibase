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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 。
 * <p></p>
 *
 * @author silenter
 * @version 1.0
 * @date 2017-10-01 14:27
 */
@Data
@NoArgsConstructor
public class VelocityDto {
    //包名
    private String packageName;
    //模块的名称前缀
    private String moduleName;
    //创建作者
    private String author;
    //创建时间
    private String dateTime;
    //表名
    private String tableName;
    //数据库连接方式
    private String jdbcDriver;
    //数据库连接地址
    private String jdbcUrl;
    //数据库登录用户名
    private String jdbcUser;
    //数据库登录密码
    private String jdbcPass;
    //Do/Dto 的属性
    private String[] attributes = new String[2];
    //创建文件的路径
    private String filePath;

    public VelocityDto(String packageName, String moduleName, String author, String tableName) {
        this.packageName = packageName;
        this.moduleName = moduleName;
        this.author = author;
        this.tableName = tableName;
        this.attributes[0] = "";
        this.attributes[1] = "";
    }

    public VelocityDto(String packageName, String moduleName, String author, String tableName, String jdbcDriver, String jdbcUrl, String jdbcUser, String jdbcPass) {
        this.packageName = packageName;
        this.moduleName = moduleName;
        this.author = author;
        this.tableName = tableName;
        this.jdbcDriver = jdbcDriver;
        this.jdbcUrl = jdbcUrl;
        this.jdbcUser = jdbcUser;
        this.jdbcPass = jdbcPass;
        this.attributes[0] = "";
        this.attributes[1] = "";
    }
}
