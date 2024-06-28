package uz.audio_book.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.audio_book.backend.entity.Book;
import uz.audio_book.backend.repo.BookRepo;
import uz.audio_book.backend.service.BookService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;
    private final BookRepo bookRepo;
    @GetMapping
    public HttpEntity<?> getBooks() {
        return bookService.getBooksProjection();
    }

    @GetMapping("/image/{bookId}")
    public HttpEntity<?> getBookImage(@PathVariable UUID bookId) {
        return bookService.sendBookPicture(bookId);
    }

    @SneakyThrows
    @PostMapping("/upload")
    public HttpEntity<?> addBook(MultipartFile file) {
        return ResponseEntity.ok(bookRepo.save(Book.builder()
                .title("book")
                .author("author")
                .photo(file.getBytes())
                .build()));
    }

}