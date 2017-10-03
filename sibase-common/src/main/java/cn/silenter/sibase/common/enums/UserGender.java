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
package cn.silenter.sibase.common.enums;

/**
 * 用户性别的模型。
 * <p>主要用于定义用户的性别，共定义了三种类型：men("男"),women("女"),unknow("保密");</p>
 *
 * @author silenter
 * @version 1.0
 * @date 2017-09-01 23:23
 */
public enum UserGender {
    men("男"), women("女"), unknow("保密");
    private String info;

    UserGender(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
