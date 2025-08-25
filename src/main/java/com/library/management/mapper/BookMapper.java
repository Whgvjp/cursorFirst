package com.library.management.mapper;

import com.library.management.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 图书Mapper接口
 */
@Mapper
public interface BookMapper {
    
    /**
     * 新增图书
     */
    int insert(Book book);
    
    /**
     * 根据ID更新图书
     */
    int updateById(Book book);
    
    /**
     * 根据ID删除图书
     */
    int deleteById(@Param("id") Long id);
    
    /**
     * 根据ID查询图书
     */
    Book selectById(@Param("id") Long id);
    
    /**
     * 根据ISBN查询图书
     */
    Book selectByIsbn(@Param("isbn") String isbn);
    
    /**
     * 查询所有图书
     */
    List<Book> selectAll();
    
    /**
     * 分页查询图书
     */
    List<Book> selectByPage(@Param("offset") int offset, @Param("limit") int limit);
    
    /**
     * 根据条件查询图书
     */
    List<Book> selectByCondition(@Param("name") String name, 
                                @Param("author") String author, 
                                @Param("category") String category);
    
    /**
     * 查询图书总数
     */
    int selectCount();
    
    /**
     * 更新图书状态
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
}
