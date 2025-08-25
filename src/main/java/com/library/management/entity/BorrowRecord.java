package com.library.management.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 借阅记录实体类
 */
@Data
public class BorrowRecord {
    
    /**
     * 借阅记录ID
     */
    private Long id;
    
    /**
     * 图书ID
     */
    private Long bookId;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 借阅时间
     */
    private LocalDateTime borrowTime;
    
    /**
     * 应还时间
     */
    private LocalDateTime dueTime;
    
    /**
     * 实际归还时间
     */
    private LocalDateTime returnTime;
    
    /**
     * 借阅状态：0-借阅中，1-已归还，2-已逾期
     */
    private Integer status;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
