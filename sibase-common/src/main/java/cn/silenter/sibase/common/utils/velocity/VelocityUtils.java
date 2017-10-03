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

import cn.silenter.sibase.common.utils.JDBCUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自动代码生成工具。
 * <p>自动生成MVC多层需要的基础代码</p>
 *
 * @author silenter
 * @version 1.0
 * @date 2017-10-01 11:56
 */
public class VelocityUtils {
    private static final Logger logger = LoggerFactory.getLogger(VelocityUtils.class);
    private String basePath = new String();

    /**
     * 生成代码的对外调用的方法。
     * <p>调用此方法后，请将日志打印级别调至info级别，可以查看到代码生成输出日志</p>
     *
     * @param velocityDto 关于生成的代码需要的一些信息的Bean
     * @param clazz       要生成代码的那个类文件的Class
     * @author silenter
     * @date 2017-10-01 19:37
     */
    public void createFiles(VelocityDto velocityDto, Class clazz) {
        //读取公共资源的路径
        this.basePath = this.getClass().getResource("/templates/Service.vm").getPath().replaceFirst("/", "").replace("Service.vm", "");
        if (logger.isInfoEnabled()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("获取模板资源路径：");
            stringBuilder.append(basePath);
            logger.info(stringBuilder.toString());
        }
        //创建数据模型
        VelocityDto dto = init(velocityDto, clazz);
        //分别创建对应的java文件
        createFile(dto, "Service", "service/");
        createFile(dto, "ServiceImpl", "service/impl/");
        createFile(dto, "Controller", "controller/");
        createFile(dto, "ApiController", "controller/api/");
        createFile(dto, "Mapper", "mapper/");
        createFile(dto, "Repository", "repository/");
        createFile(dto, "Do", "pojo/");
        createFile(dto, "Dto", "pojo/");
    }

    /**
     * 初始化VelocityDto中的创建时间、文件生成的路径。
     *
     * @param velocityDto
     * @param clazz
     * @return VelocityDto
     * @author silenter
     * @date 2017-10-01 19:35
     */
    private VelocityDto init(VelocityDto velocityDto, Class clazz) {
        //赋值创建时间
        String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        velocityDto.setDateTime(dateTime);

        //通过包名获取相对路径
        String path = velocityDto.getPackageName().replace(".", "/") + "/";
        //获取文件生成后存放的路径
        String classPath = clazz.getResource("/").getPath().replaceFirst("/", "")
                .replace("target/classes/", "src/main/java/" + path);
        velocityDto.setFilePath(classPath);

        //判断是否需要读取数据库
        if (!StringUtils.isEmpty(velocityDto.getTableName()) && !StringUtils.isEmpty(velocityDto.getJdbcDriver()) &&
                !StringUtils.isEmpty(velocityDto.getJdbcUrl()) && !StringUtils.isEmpty(velocityDto.getJdbcUser()) && !StringUtils.isEmpty(velocityDto.getJdbcPass())) {
            JDBCUtils jdbcUtils = new JDBCUtils();
            String[] attributes = jdbcUtils.getAttributes(velocityDto.getJdbcDriver(), velocityDto.getJdbcUrl(), velocityDto.getJdbcUser(), velocityDto.getJdbcPass(), velocityDto.getTableName());
            velocityDto.setAttributes(attributes);
        }
        return velocityDto;
    }

    /**
     * 生成每个文件的调用方法。
     *
     * @param velocityDto
     * @param fileName
     * @param filePath
     * @author silenter
     * @date 2017-10-01 19:41
     */
    private void createFile(VelocityDto velocityDto, String fileName, String filePath) {
        if (logger.isInfoEnabled()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("开始生成：");
            stringBuilder.append(velocityDto.getModuleName());
            stringBuilder.append(fileName);
            stringBuilder.append(".java");
            logger.info(stringBuilder.toString());
        }
        //创建并初始化Velocity
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, this.basePath);
        ve.setProperty(VelocityEngine.INPUT_ENCODING, "UTF-8");
        ve.setProperty(VelocityEngine.OUTPUT_ENCODING, "UTF-8");
        ve.init();
        //获取内容
        Template template = ve.getTemplate(fileName + ".vm");
        //填入需要替换的内容
        VelocityContext ctx = new VelocityContext();
        ctx.put("packageName", velocityDto.getPackageName());
        ctx.put("module", velocityDto.getModuleName().toLowerCase().substring(0, 1) + velocityDto.getModuleName().substring(1));
        ctx.put("moduleName", velocityDto.getModuleName());
        ctx.put("dateTime", velocityDto.getDateTime());
        ctx.put("author", velocityDto.getAuthor());
        ctx.put("tableName", velocityDto.getTableName());

        if ("Do".equals(fileName) || "Dto".equals(fileName)) {
            ctx.put("doAttributes", velocityDto.getAttributes()[1]);
            ctx.put("dtoAttributes", velocityDto.getAttributes()[0]);
        }

        //调用代码生成及写出的方法
        merge(template, ctx, velocityDto.getFilePath() + filePath + velocityDto.getModuleName() + fileName + ".java");
        if (logger.isInfoEnabled()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("生成结束：");
            stringBuilder.append(velocityDto.getModuleName());
            stringBuilder.append(fileName);
            stringBuilder.append(".java  生成路径：");
            stringBuilder.append(velocityDto.getFilePath());
            stringBuilder.append(filePath);
            logger.info(stringBuilder.toString());
        }
    }

    /**
     * 模板替换及代码内容写出到文件的方法。
     *
     * @param template
     * @param ctx
     * @param path
     * @author silenter
     * @date 2017-10-01 19:42
     */
    private static void merge(Template template, VelocityContext ctx, String path) {
        File file = new File(path);
        File dir = file.getParentFile();
        //判断目录是否存在，不存在则创建多级目录
        if (!dir.exists()) {
            dir.mkdirs();
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(path);
            template.merge(ctx, writer);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            if (logger.isInfoEnabled()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("代码文件写出失败，文件名：");
                stringBuilder.append(path);
                logger.info(stringBuilder.toString());
            }
        } finally {
            writer.close();
        }
    }

}
