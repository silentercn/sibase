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
package cn.silenter.sibase.common.aspect;

import cn.silenter.sibase.common.exception.BaseException;
import cn.silenter.sibase.common.pojo.BaseApiResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Api返回数据统一拦截处理异常。
 * <p>对所有的返回数据的ApiController方法进行拦截，处理异常信息，并返回统一的BaseApiResult</p>
 *
 * @author silenter
 * @version 1.0
 * @date 2017-09-10 11:18
 */
@Aspect
@Component
public class ApiControllerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ApiControllerInterceptor.class);

    /**
     * 定义拦截规则：拦截cn.silenter.*.controller包下面的所有类中，有@RequestMapping注解的方法。
     * && @annotation(org.springframework.web.bind.annotation.RequestMapping)
     *
     * @author silenter
     * @date 2017-09-10 11:23
     */
    @Pointcut("execution(public * cn.silenter.*.controller.api.*(..))")
    public void controllerMethodPointcut() {
    }

    /**
     * 拦截器具体实现。
     *
     * @param pjp
     * @return BaseApiResult
     * @author silenter
     * @date 2017-09-10 11:27
     */
    @Around("controllerMethodPointcut()") //指定拦截器规则；也可以直接把“execution(* cn.silenter.........)”写进这里
    public BaseApiResult Interceptor(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();
        //MethodSignature signature = (MethodSignature) pjp.getSignature();
        //获取被拦截的方法
        // Method method = signature.getMethod();
        //获取被拦截的方法名
        //String methodName = method.getName();
        //保存所有请求参数，用于输出到日志中
        //Set<Object> allParams = new LinkedHashSet<>();
        BaseApiResult<?> result;
        logger.info("xxxxxxxxxxxxx");
        try {
            result = (BaseApiResult<?>) pjp.proceed();
            logger.info(pjp.getSignature() + " use time:" + (System.currentTimeMillis() - startTime));

        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }
        return result;
    }

    private BaseApiResult<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        BaseApiResult<?> result = new BaseApiResult();
        // 已知异常
        if (e instanceof BaseException) {
            result.setMsg(e.getLocalizedMessage());
            result.setCode(BaseApiResult.FAIL);
        } else {
            logger.error(pjp.getSignature() + " error ", e);
            result.setMsg(e.toString());
            result.setCode(BaseApiResult.FAIL);
            // 未知异常是应该重点关注的，这里可以做其他操作，如通知邮件，单独写到某个文件等等。
        }
        return result;
    }
}
