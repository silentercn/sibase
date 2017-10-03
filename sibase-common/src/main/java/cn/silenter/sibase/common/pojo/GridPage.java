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
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * jqgrid 的表格数据模型。
 * <p>jqgrid 的数据模型</p>
 *
 * @author silenter
 * @version 1.0
 * @date 2017-09-01 23:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GridPage {
    /**
     * 数据内容
     */
    private Object rows;
    /**
     * 总页数
     */
    private int total;
    /**
     * 当前页
     */
    private int page;
    /**
     * 总记录
     */
    private long records;

    public GridPage(Page<?> pages) {
        this.page = pages.getNumber() + 1;
        this.records = pages.getTotalElements();
        this.total = pages.getTotalPages();
        this.rows = pages.getContent();
    }

    public GridPage(Page<?> pages, List<?> rows) {
        this.page = pages.getNumber() + 1;
        this.records = pages.getTotalElements();
        this.total = pages.getTotalPages();
        this.rows = rows;
    }
}
