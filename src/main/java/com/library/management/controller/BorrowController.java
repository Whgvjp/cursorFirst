package com.library.management.controller;

import com.library.management.dto.BorrowDTO;
import com.library.management.dto.Result;
import com.library.management.entity.BorrowRecord;
import com.library.management.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 借阅控制器
 */
@RestController
@RequestMapping("/api/borrows")
@Validated
public class BorrowController {
    
    @Autowired
    private BorrowService borrowService;
    
    /**
     * 借阅图书
     */
    @PostMapping
    public Result<BorrowRecord> borrowBook(@Valid @RequestBody BorrowDTO borrowDTO) {
        try {
            BorrowRecord borrowRecord = borrowService.borrowBook(borrowDTO);
            return Result.success("图书借阅成功", borrowRecord);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 归还图书
     */
    @PutMapping("/{id}/return")
    public Result<Boolean> returnBook(@PathVariable Long id) {
        try {
            boolean result = borrowService.returnBook(id);
            return Result.success("图书归还成功", result);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 续借图书
     */
    @PutMapping("/{id}/renew")
    public Result<Boolean> renewBook(
            @PathVariable Long id,
            @RequestParam Integer additionalDays) {
        try {
            boolean result = borrowService.renewBook(id, additionalDays);
            return Result.success("图书续借成功", result);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 根据ID查询借阅记录
     */
    @GetMapping("/{id}")
    public Result<BorrowRecord> getBorrowRecordById(@PathVariable Long id) {
        try {
            BorrowRecord borrowRecord = borrowService.getBorrowRecordById(id);
            if (borrowRecord == null) {
                return Result.error("借阅记录不存在");
            }
            return Result.success(borrowRecord);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 根据用户ID查询借阅记录
     */
    @GetMapping("/user/{userId}")
    public Result<List<BorrowRecord>> getBorrowRecordsByUserId(@PathVariable Long userId) {
        try {
            List<BorrowRecord> records = borrowService.getBorrowRecordsByUserId(userId);
            return Result.success(records);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 根据图书ID查询借阅记录
     */
    @GetMapping("/book/{bookId}")
    public Result<List<BorrowRecord>> getBorrowRecordsByBookId(@PathVariable Long bookId) {
        try {
            List<BorrowRecord> records = borrowService.getBorrowRecordsByBookId(bookId);
            return Result.success(records);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 查询所有借阅记录
     */
    @GetMapping
    public Result<List<BorrowRecord>> getAllBorrowRecords() {
        try {
            List<BorrowRecord> records = borrowService.getAllBorrowRecords();
            return Result.success(records);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 分页查询借阅记录
     */
    @GetMapping("/page")
    public Result<List<BorrowRecord>> getBorrowRecordsByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<BorrowRecord> records = borrowService.getBorrowRecordsByPage(page, size);
            return Result.success(records);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 根据状态查询借阅记录
     */
    @GetMapping("/status/{status}")
    public Result<List<BorrowRecord>> getBorrowRecordsByStatus(@PathVariable Integer status) {
        try {
            List<BorrowRecord> records = borrowService.getBorrowRecordsByStatus(status);
            return Result.success(records);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 查询逾期记录
     */
    @GetMapping("/overdue")
    public Result<List<BorrowRecord>> getOverdueRecords() {
        try {
            List<BorrowRecord> records = borrowService.getOverdueRecords();
            return Result.success(records);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 获取借阅记录总数
     */
    @GetMapping("/count")
    public Result<Integer> getBorrowRecordCount() {
        try {
            int count = borrowService.getBorrowRecordCount();
            return Result.success(count);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
