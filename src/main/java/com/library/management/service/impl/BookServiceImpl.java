package com.library.management.service.impl;

import com.library.management.dto.BookDTO;
import com.library.management.entity.Book;
import com.library.management.mapper.BookMapper;
import com.library.management.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 图书服务实现类
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {
    
    @Autowired
    private BookMapper bookMapper;
    
    @Override
    public Book addBook(BookDTO bookDTO) {
        // 检查ISBN是否已存在
        Book existingBook = bookMapper.selectByIsbn(bookDTO.getIsbn());
        if (existingBook != null) {
            throw new RuntimeException("ISBN已存在");
        }
        
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        book.setStatus(0); // 设置默认状态为可借阅
        book.setCreateTime(LocalDateTime.now());
        book.setUpdateTime(LocalDateTime.now());
        
        bookMapper.insert(book);
        return book;
    }
    
    @Override
    public Book updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookMapper.selectById(id);
        if (existingBook == null) {
            throw new RuntimeException("图书不存在");
        }
        
        // 检查ISBN是否被其他图书使用
        Book bookWithSameIsbn = bookMapper.selectByIsbn(bookDTO.getIsbn());
        if (bookWithSameIsbn != null && !bookWithSameIsbn.getId().equals(id)) {
            throw new RuntimeException("ISBN已被其他图书使用");
        }
        
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        book.setId(id);
        book.setStatus(existingBook.getStatus()); // 保持原有状态
        book.setUpdateTime(LocalDateTime.now());
        
        bookMapper.updateById(book);
        return bookMapper.selectById(id);
    }
    
    @Override
    public boolean deleteBook(Long id) {
        Book book = bookMapper.selectById(id);
        if (book == null) {
            throw new RuntimeException("图书不存在");
        }
        
        if (book.getStatus() == 1) {
            throw new RuntimeException("图书正在借阅中，无法删除");
        }
        
        return bookMapper.deleteById(id) > 0;
    }
    
    @Override
    public Book getBookById(Long id) {
        return bookMapper.selectById(id);
    }
    
    @Override
    public Book getBookByIsbn(String isbn) {
        return bookMapper.selectByIsbn(isbn);
    }
    
    @Override
    public List<Book> getAllBooks() {
        return bookMapper.selectAll();
    }
    
    @Override
    public List<Book> getBooksByPage(int page, int size) {
        int offset = (page - 1) * size;
        return bookMapper.selectByPage(offset, size);
    }
    
    @Override
    public List<Book> searchBooks(String name, String author, String category) {
        return bookMapper.selectByCondition(name, author, category);
    }
    
    @Override
    public boolean updateBookStatus(Long id, Integer status) {
        Book book = bookMapper.selectById(id);
        if (book == null) {
            throw new RuntimeException("图书不存在");
        }
        
        return bookMapper.updateStatus(id, status) > 0;
    }
    
    @Override
    public int getBookCount() {
        return bookMapper.selectCount();
    }
}
