package com.jsj.entity;

import com.jsj.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by jinshouji on 2018/4/8.
 * Demo：学生类参考
 */
@Data
@Entity
@Table(name = "student")
public class Student extends BaseEntity
{
    @Column(name = "name")           //学生姓名
    private String name;

    @Column(name = "sno")           //学号
    private String sno;
}
