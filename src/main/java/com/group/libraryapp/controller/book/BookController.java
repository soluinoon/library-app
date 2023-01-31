package com.group.libraryapp.controller.book;

import com.group.libraryapp.service.book.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;

    // 생성자 만들어서 스프링 빈에 주입당한다.
    // 서비스도 스프링 빈이고, 컨트롤러도 스프링 빈이기 때문에.
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public void saveBook() {
        bookService.saveBook();
    }
}
