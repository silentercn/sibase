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
package cn.silenter.sibase.common.controller.api;

import java.util.List;

import cn.silenter.sibase.common.pojo.BaseApiResult;
import cn.silenter.sibase.common.pojo.BaseDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import cn.silenter.sibase.common.service.BaseService;

/**
 * ApiController的基类。
 * <p>其他ApiController的类可以通过继承此类，来继承抽象出的一些通用的方法</p>
 *
 * @author silenter
 * @version 1.0
 * @date 2017-09-01 23:13
 */
public class BaseApiController<T extends BaseDo> {
    @Autowired
    private BaseService<T> baseService;

    @GetMapping("/{id}")
    public BaseApiResult<T> findOne(@PathVariable("id") String id) {
        return new BaseApiResult<T>(baseService.findOne(id));
    }

    @PutMapping("/")
    public BaseApiResult<String> save(T t) {
        return new BaseApiResult<String>(baseService.save(t));
    }

    @DeleteMapping("/{id}")
    public BaseApiResult<String> delete(@PathVariable("id") String id) {
        return new BaseApiResult<String>(baseService.delete(id));
    }

    @PostMapping("/{id}")
    public BaseApiResult<String> update(@PathVariable("id") String id, T t) {
        return new BaseApiResult<String>(baseService.update(id, t));
    }

    @GetMapping("/all")
    public BaseApiResult<List<T>> findAll() {
        return new BaseApiResult<List<T>>(baseService.findAll());
    }

    @GetMapping("/page")
    public BaseApiResult<Page<T>> findAll(@PageableDefault Pageable pageable) {
        return new BaseApiResult<Page<T>>(baseService.findAll(pageable));
    }
}
