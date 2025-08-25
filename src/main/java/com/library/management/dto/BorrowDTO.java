package com.library.management.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * 借阅数据传输对象
 */
@Data
public class BorrowDTO {
    
    /**
     * 图书ID
     */
    @NotNull(message = "图书ID不能为空")
    private Long bookId;
    
    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    
    /**
     * 借阅天数
     */
    @NotNull(message = "借阅天数不能为空")
    private Integer borrowDays;
    
    /**
     * 备注
     */
    private String remark;
}
