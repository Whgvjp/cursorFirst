package com.library.management.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 图书实体类
 */
@Data
public class Book {
    
    /**
     * 图书ID
     */
    private Long id;
    
    /**
     * 图书名称
     */
    private String name;
    
    /**
     * 作者
     */
    private String author;
    
    /**
     * ISBN号
     */
    private String isbn;
    
    /**
     * 出版社
     */
    private String publisher;
    
    /**
     * 出版日期
     */
    private LocalDateTime publishDate;
    
    /**
     * 图书分类
     */
    private String category;
    
    /**
     * 图书状态：0-可借阅，1-已借出，2-已下架
     */
    private Integer status;
    
    /**
     * 图书简介
     */
    private String description;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
