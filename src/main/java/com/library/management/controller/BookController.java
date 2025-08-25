package com.library.management.controller;

import com.library.management.dto.BookDTO;
import com.library.management.dto.Result;
import com.library.management.entity.Book;
import com.library.management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 图书控制器
 */
@RestController
@RequestMapping("/api/books")
@Validated
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    /**
     * 新增图书
     */
    @PostMapping
    public Result<Book> addBook(@Valid @RequestBody BookDTO bookDTO) {
        try {
            Book book = bookService.addBook(bookDTO);
            return Result.success("图书添加成功", book);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 更新图书
     */
    @PutMapping("/{id}")
    public Result<Book> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) {
        try {
            Book book = bookService.updateBook(id, bookDTO);
            return Result.success("图书更新成功", book);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 删除图书
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteBook(@PathVariable Long id) {
        try {
            boolean result = bookService.deleteBook(id);
            return Result.success("图书删除成功", result);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 根据ID查询图书
     */
    @GetMapping("/{id}")
    public Result<Book> getBookById(@PathVariable Long id) {
        try {
            Book book = bookService.getBookById(id);
            if (book == null) {
                return Result.error("图书不存在");
            }
            return Result.success(book);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 根据ISBN查询图书
     */
    @GetMapping("/isbn/{isbn}")
    public Result<Book> getBookByIsbn(@PathVariable String isbn) {
        try {
            Book book = bookService.getBookByIsbn(isbn);
            if (book == null) {
                return Result.error("图书不存在");
            }
            return Result.success(book);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 查询所有图书
     */
    @GetMapping
    public Result<List<Book>> getAllBooks() {
        try {
            List<Book> books = bookService.getAllBooks();
            return Result.success(books);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 分页查询图书
     */
    @GetMapping("/page")
    public Result<List<Book>> getBooksByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<Book> books = bookService.getBooksByPage(page, size);
            return Result.success(books);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 搜索图书
     */
    @GetMapping("/search")
    public Result<List<Book>> searchBooks(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category) {
        try {
            List<Book> books = bookService.searchBooks(name, author, category);
            return Result.success(books);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 更新图书状态
     */
    @PutMapping("/{id}/status")
    public Result<Boolean> updateBookStatus(
            @PathVariable Long id,
            @RequestParam Integer status) {
        try {
            boolean result = bookService.updateBookStatus(id, status);
            return Result.success("图书状态更新成功", result);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 获取图书总数
     */
    @GetMapping("/count")
    public Result<Integer> getBookCount() {
        try {
            int count = bookService.getBookCount();
            return Result.success(count);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
