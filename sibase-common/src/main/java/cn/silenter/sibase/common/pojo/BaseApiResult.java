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
package cn.silenter.sibase.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回消息的统一数据格式类。
 * <p></p>
 *
 * @author silenter
 * @version 1.0
 * @date 2017-09-01 23:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseApiResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int FAIL = 1;
    /**
     * 状态码：0成功，其他为失败
     */
    private int code = 0;

    /**
     * 成功为"success"，其他为失败原因
     */
    private String msg = "success";

    /**
     * 返回的数据结果集
     */
    private T data;

    /**
     * 构建默认成功的消息，状态默认0，msg默认success。。
     *
     * @param data
     * @author silenter
     * @date 2017-09-01 23:35
     */
    public BaseApiResult(T data) {
        super();
        this.data = data;
    }

    public BaseApiResult(Throwable e) {
        super();
        this.code = FAIL;
        this.msg = e.getMessage();
    }

}
