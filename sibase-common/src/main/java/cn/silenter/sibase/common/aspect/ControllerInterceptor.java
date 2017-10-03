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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.thymeleaf.exceptions.TemplateInputException;

/**
 * 返回数据统一拦截处理异常。
 * <p>对所有的返回数据的Controller方法进行拦截，处理异常信息，并返回统一的BaseResult</p>
 *
 * @author silenter
 * @version 1.0
 * @date 2017-09-24 12:29
 */
@ControllerAdvice
public class ControllerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String badRequestExceptionHandler(HttpMessageNotReadableException e) {
        return "error/400";
    }


    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(Exception e) {
        logger.error(e.getMessage());
        logger.error(e.toString());
        logger.error("login here");
        return "error/500";
    }

    @ExceptionHandler(TemplateInputException.class)
    public String templateInputExceptionHandler(TemplateInputException e){
        return "error/404";
    }


    @ExceptionHandler(HttpMessageNotWritableException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String serverErrorExceptionHandler(HttpMessageNotWritableException e) {
        if(logger.isInfoEnabled()){
            logger.info(e.getMessage());
        }
        return "error/500";
    }

    /**
     * 请求方式不正确。
     * <p>请求方式与服务器的不符</p>
     *
     * @param e
     * @return java.lang.String
     * @author silenter
     * @date 2017-09-24 19:24
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public String serverErrorExceptionHandler(HttpRequestMethodNotSupportedException e) {
        if(logger.isInfoEnabled()){
            logger.info(e.getMessage());
        }
        return "error/500";
    }
    /**
     * 缺少参数。
     * <p>请求的URL缺少必要的参数</p>。
     *
     * @param e
     * @return java.lang.String
     * @author silenter
     * @date 2017-09-24 19:25
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String serverErrorExceptionHandler(MissingServletRequestParameterException e) {
        if(logger.isInfoEnabled()){
            logger.info(e.getMessage());
        }
        return "error/500";
    }
}
