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
package cn.silenter.sibase.common.bind.method;

import cn.silenter.sibase.common.bind.annotation.GridPageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * 。
 * <p></p>
 *
 * @author silenter
 * @version 1.0
 * @date 2017-09-24 19:37
 */
public class GridPageableArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if (methodParameter.hasParameterAnnotation(GridPageable.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        //当前页数
        int page = 0;
        if (!StringUtils.isEmpty(nativeWebRequest.getParameter("page"))) {
            page = Integer.valueOf(nativeWebRequest.getParameter("page")).intValue();
            page = page - 1;
        }
        //每页数量
        int size = 20;
        if (!StringUtils.isEmpty(nativeWebRequest.getParameter("rows"))) {
            size = Integer.valueOf(nativeWebRequest.getParameter("rows")).intValue();
        }
        //排序方式
        String sord = nativeWebRequest.getParameter("sord");
        if (StringUtils.isEmpty(sord)) {
            sord = "desc";
        }
        //排序字段
        String sidx = nativeWebRequest.getParameter("sidx");
        if (StringUtils.isEmpty(sidx)) {
            sidx = "auto_id";
        }
        String[] sidxs = sidx.split(",");
        String[] sords = sord.split(",");

        List<Sort.Order> orders = new ArrayList<>();
        for (int i = 0; i < sidxs.length; i++) {
            try {
                Sort.Order order;
                if ("desc".equals(sords[i].toLowerCase())) {
                    order = new Sort.Order(Sort.Direction.DESC, sidxs[i]);
                } else {
                    order = new Sort.Order(Sort.Direction.ASC, sidxs[i]);
                }
                orders.add(order);
            } catch (NullPointerException ex) {
                Sort.Order order = new Sort.Order(Sort.Direction.DESC, sidxs[i]);
                orders.add(order);
            }
        }
        Sort sort = new Sort(orders);
        Pageable pageable = new PageRequest(page, size, sort);
        return pageable;
    }
}
