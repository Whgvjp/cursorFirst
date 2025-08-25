package com.library.management.service;

import com.library.management.dto.BookDTO;
import com.library.management.entity.Book;
import java.util.List;

/**
 * 图书服务接口
 */
public interface BookService {
    
    /**
     * 新增图书
     */
    Book addBook(BookDTO bookDTO);
    
    /**
     * 更新图书
     */
    Book updateBook(Long id, BookDTO bookDTO);
    
    /**
     * 删除图书
     */
    boolean deleteBook(Long id);
    
    /**
     * 根据ID查询图书
     */
    Book getBookById(Long id);
    
    /**
     * 根据ISBN查询图书
     */
    Book getBookByIsbn(String isbn);
    
    /**
     * 查询所有图书
     */
    List<Book> getAllBooks();
    
    /**
     * 分页查询图书
     */
    List<Book> getBooksByPage(int page, int size);
    
    /**
     * 根据条件查询图书
     */
    List<Book> searchBooks(String name, String author, String category);
    
    /**
     * 更新图书状态
     */
    boolean updateBookStatus(Long id, Integer status);
    
    /**
     * 查询图书总数
     */
    int getBookCount();
}
