package com.library.management.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 图书数据传输对象
 */
@Data
public class BookDTO {
    
    /**
     * 图书ID（新增时为空）
     */
    private Long id;
    
    /**
     * 图书名称
     */
    @NotBlank(message = "图书名称不能为空")
    private String name;
    
    /**
     * 作者
     */
    @NotBlank(message = "作者不能为空")
    private String author;
    
    /**
     * ISBN号
     */
    @NotBlank(message = "ISBN号不能为空")
    private String isbn;
    
    /**
     * 出版社
     */
    @NotBlank(message = "出版社不能为空")
    private String publisher;
    
    /**
     * 出版日期
     */
    @NotNull(message = "出版日期不能为空")
    private LocalDateTime publishDate;
    
    /**
     * 图书分类
     */
    @NotBlank(message = "图书分类不能为空")
    private String category;
    
    /**
     * 图书简介
     */
    private String description;
}
