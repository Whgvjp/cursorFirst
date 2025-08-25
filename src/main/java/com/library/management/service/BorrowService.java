package com.library.management.service;

import com.library.management.dto.BorrowDTO;
import com.library.management.entity.BorrowRecord;
import java.util.List;

/**
 * 借阅服务接口
 */
public interface BorrowService {
    
    /**
     * 借阅图书
     */
    BorrowRecord borrowBook(BorrowDTO borrowDTO);
    
    /**
     * 归还图书
     */
    boolean returnBook(Long borrowRecordId);
    
    /**
     * 续借图书
     */
    boolean renewBook(Long borrowRecordId, Integer additionalDays);
    
    /**
     * 根据ID查询借阅记录
     */
    BorrowRecord getBorrowRecordById(Long id);
    
    /**
     * 根据用户ID查询借阅记录
     */
    List<BorrowRecord> getBorrowRecordsByUserId(Long userId);
    
    /**
     * 根据图书ID查询借阅记录
     */
    List<BorrowRecord> getBorrowRecordsByBookId(Long bookId);
    
    /**
     * 查询所有借阅记录
     */
    List<BorrowRecord> getAllBorrowRecords();
    
    /**
     * 分页查询借阅记录
     */
    List<BorrowRecord> getBorrowRecordsByPage(int page, int size);
    
    /**
     * 根据状态查询借阅记录
     */
    List<BorrowRecord> getBorrowRecordsByStatus(Integer status);
    
    /**
     * 查询逾期记录
     */
    List<BorrowRecord> getOverdueRecords();
    
    /**
     * 查询借阅记录总数
     */
    int getBorrowRecordCount();
}
