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

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity实体模型的基类。
 * <p>其他的Entity实体模型可以通过继承此类来继承通用的属性及方法</p>
 *
 * @author silenter
 * @version 1.0
 * @date 2017-09-01 23:15
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseDo {
    /**
     * 自增列
     */
    @Column(name = "auto_id", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer autoId;
    /**
     * 主键ID，使用的GUID
     */
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "guid")
    private String id;

    /**
     * 创建者
     */
    @Column(name = "create_by")
    @CreatedBy
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    @CreatedDate
    private Date createDate;

    /**
     * 修改者
     */
    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;

    /**
     * 修改时间
     */
    @Column(name = "modified_date")
    @LastModifiedDate
    private Date modifiedDate;

}
