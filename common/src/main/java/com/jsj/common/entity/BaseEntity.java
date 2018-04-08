package com.jsj.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsj.common.utility.Tools;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;

/**
 * Created by jinshouji on 2018/4/8.
 * 抽象的实体类
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity  {
    @Id
    @Column(name = "id")
    private String id;                       //数据ID（采用32位唯一编码）

    public BaseEntity()
    {
        this.id= Tools.get32UUID();
    }

    @Column(name = "create_user")           //创建用户
    private String createUser;

    @Column(name = "modify_user")           //修改用户
    private Date modifyUser;

    @Column(updatable = false, name = "create_date")    //创建时间
    private Date createDate;

    @Column(updatable = false, name = "modify_date")    //修改时间
    private Date modifyDate;

    @PrePersist
    public void beforeAdd() {
        modifyDate = createDate = new Date();
    }

    @PreUpdate
    public void beforeModified() {
        modifyDate = new Date();
    }

    @JsonIgnore
    public boolean isNew() {
        if (this.id == null || "".equals(this.id)) {
            this.setId(null);
            return true;
        }
        return false;
    }
}
