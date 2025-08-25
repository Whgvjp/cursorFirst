package com.library.management.mapper;

import com.library.management.entity.BorrowRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 借阅记录Mapper接口
 */
@Mapper
public interface BorrowRecordMapper {
    
    /**
     * 新增借阅记录
     */
    int insert(BorrowRecord borrowRecord);
    
    /**
     * 根据ID更新借阅记录
     */
    int updateById(BorrowRecord borrowRecord);
    
    /**
     * 根据ID查询借阅记录
     */
    BorrowRecord selectById(@Param("id") Long id);
    
    /**
     * 根据用户ID查询借阅记录
     */
    List<BorrowRecord> selectByUserId(@Param("userId") Long userId);
    
    /**
     * 根据图书ID查询借阅记录
     */
    List<BorrowRecord> selectByBookId(@Param("bookId") Long bookId);
    
    /**
     * 查询所有借阅记录
     */
    List<BorrowRecord> selectAll();
    
    /**
     * 分页查询借阅记录
     */
    List<BorrowRecord> selectByPage(@Param("offset") int offset, @Param("limit") int limit);
    
    /**
     * 根据状态查询借阅记录
     */
    List<BorrowRecord> selectByStatus(@Param("status") Integer status);
    
    /**
     * 查询借阅记录总数
     */
    int selectCount();
    
    /**
     * 查询逾期记录
     */
    List<BorrowRecord> selectOverdueRecords();
    
    /**
     * 更新借阅状态
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
}
