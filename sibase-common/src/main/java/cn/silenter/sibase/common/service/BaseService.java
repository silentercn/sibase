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
package cn.silenter.sibase.common.service;

import java.util.List;

import cn.silenter.sibase.common.pojo.BaseDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service 层的接口基类。
 * <p>其他的Service层接口类可以通过继承此类，来继承通用的方法</p>
 *
 * @author silenter
 * @version 1.0
 * @date 2017-09-01 23:18
 */
public interface BaseService<T extends BaseDo> {
    public Boolean exists(String id);

    public String save(T entity);

    public String save(List<T> entitys);

    public String delete(String id);

    public String delete(T entity);

    public String delete(List<T> entitys);

    public String update(String id, T entity);

    public Long count();

    public T findOne(String id);

    public List<T> findAll();

    public Page<T> findAll(Pageable pageable);


}
