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
package cn.silenter.sibase.common.service.impl;

import cn.silenter.sibase.common.pojo.BaseDo;
import cn.silenter.sibase.common.repository.BaseRepository;
import cn.silenter.sibase.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service 层的实现基类。
 * <p>其他的Service层的实现类可以通过继承此类来继承通用的方法</p>
 *
 * @author silenter
 * @version 1.0
 * @date 2017-09-01 23:20
 */
public class BaseServiceImpl<T extends BaseDo> implements BaseService<T> {
    @Autowired
    private BaseRepository<T> baseRepository;

    @Override
    public Boolean exists(String id) {
        return baseRepository.exists(id);
    }

    @Override
    public String save(T entity) {
        baseRepository.save(entity);
        return "";
    }

    @Override
    public String save(List<T> entitys) {
        baseRepository.save(entitys);
        return "";
    }

    @Override
    public String delete(String id) {
        baseRepository.delete(id);
        return "";
    }

    @Override
    public String delete(T entity) {
        baseRepository.delete(entity);
        return "";
    }

    @Override
    public String delete(List<T> entitys) {
        baseRepository.delete(entitys);
        return "";
    }

    @Override
    public String update(String id, T entity) {
        baseRepository.save(entity);
        return "";
    }

    @Override
    public Long count() {
        return baseRepository.count();
    }

    @Override
    public T findOne(String id) {
        return baseRepository.findOne(id);
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }

}
