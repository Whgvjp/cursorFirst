package com.library.management.service.impl;

import com.library.management.dto.BorrowDTO;
import com.library.management.entity.Book;
import com.library.management.entity.BorrowRecord;
import com.library.management.mapper.BookMapper;
import com.library.management.mapper.BorrowRecordMapper;
import com.library.management.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 借阅服务实现类
 */
@Service
@Transactional
public class BorrowServiceImpl implements BorrowService {
    
    @Autowired
    private BorrowRecordMapper borrowRecordMapper;
    
    @Autowired
    private BookMapper bookMapper;
    
    @Override
    public BorrowRecord borrowBook(BorrowDTO borrowDTO) {
        // 检查图书是否存在
        Book book = bookMapper.selectById(borrowDTO.getBookId());
        if (book == null) {
            throw new RuntimeException("图书不存在");
        }
        
        // 检查图书是否可借阅
        if (book.getStatus() != 0) {
            throw new RuntimeException("图书不可借阅");
        }
        
        // 创建借阅记录
        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setBookId(borrowDTO.getBookId());
        borrowRecord.setUserId(borrowDTO.getUserId());
        borrowRecord.setBorrowTime(LocalDateTime.now());
        borrowRecord.setDueTime(LocalDateTime.now().plusDays(borrowDTO.getBorrowDays()));
        borrowRecord.setStatus(0); // 借阅中
        borrowRecord.setRemark(borrowDTO.getRemark());
        borrowRecord.setCreateTime(LocalDateTime.now());
        borrowRecord.setUpdateTime(LocalDateTime.now());
        
        borrowRecordMapper.insert(borrowRecord);
        
        // 更新图书状态为已借出
        bookMapper.updateStatus(borrowDTO.getBookId(), 1);
        
        return borrowRecord;
    }
    
    @Override
    public boolean returnBook(Long borrowRecordId) {
        BorrowRecord borrowRecord = borrowRecordMapper.selectById(borrowRecordId);
        if (borrowRecord == null) {
            throw new RuntimeException("借阅记录不存在");
        }
        
        if (borrowRecord.getStatus() != 0) {
            throw new RuntimeException("该记录不是借阅中状态");
        }
        
        // 更新借阅记录
        borrowRecord.setReturnTime(LocalDateTime.now());
        borrowRecord.setStatus(1); // 已归还
        borrowRecord.setUpdateTime(LocalDateTime.now());
        
        borrowRecordMapper.updateById(borrowRecord);
        
        // 更新图书状态为可借阅
        bookMapper.updateStatus(borrowRecord.getBookId(), 0);
        
        return true;
    }
    
    @Override
    public boolean renewBook(Long borrowRecordId, Integer additionalDays) {
        BorrowRecord borrowRecord = borrowRecordMapper.selectById(borrowRecordId);
        if (borrowRecord == null) {
            throw new RuntimeException("借阅记录不存在");
        }
        
        if (borrowRecord.getStatus() != 0) {
            throw new RuntimeException("该记录不是借阅中状态");
        }
        
        // 更新应还时间
        borrowRecord.setDueTime(borrowRecord.getDueTime().plusDays(additionalDays));
        borrowRecord.setUpdateTime(LocalDateTime.now());
        
        return borrowRecordMapper.updateById(borrowRecord) > 0;
    }
    
    @Override
    public BorrowRecord getBorrowRecordById(Long id) {
        return borrowRecordMapper.selectById(id);
    }
    
    @Override
    public List<BorrowRecord> getBorrowRecordsByUserId(Long userId) {
        return borrowRecordMapper.selectByUserId(userId);
    }
    
    @Override
    public List<BorrowRecord> getBorrowRecordsByBookId(Long bookId) {
        return borrowRecordMapper.selectByBookId(bookId);
    }
    
    @Override
    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowRecordMapper.selectAll();
    }
    
    @Override
    public List<BorrowRecord> getBorrowRecordsByPage(int page, int size) {
        int offset = (page - 1) * size;
        return borrowRecordMapper.selectByPage(offset, size);
    }
    
    @Override
    public List<BorrowRecord> getBorrowRecordsByStatus(Integer status) {
        return borrowRecordMapper.selectByStatus(status);
    }
    
    @Override
    public List<BorrowRecord> getOverdueRecords() {
        return borrowRecordMapper.selectOverdueRecords();
    }
    
    @Override
    public int getBorrowRecordCount() {
        return borrowRecordMapper.selectCount();
    }
}
