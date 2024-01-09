package com.example.SimplestCRUDExample.serviceImpl;

import com.example.SimplestCRUDExample.model.Book;
import com.example.SimplestCRUDExample.model.BookRequest;
import com.example.SimplestCRUDExample.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBooks(){
        List<Book> bookList = new ArrayList<>();
        return bookRepository.findAll();
    }
    public Book getBookById(Long id){
        return bookRepository.findById(id).orElse(null);

    }

    public Book save(BookRequest bookRequest){
        Book book=new Book();
        book.setAuthor(bookRequest.getAuthor());
        book.setTitle(bookRequest.getTitle());
        book.setPublicationYear(bookRequest.getPublicationYear());
        bookRepository.save(book);
        return book;
    }

    public Book updateBook(BookRequest bookRequest,Long id){
        Optional<Book> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            Book updatedBookData = bookData.get();
            updatedBookData.setTitle(bookRequest.getTitle());
            updatedBookData.setAuthor(bookRequest.getAuthor());
            updatedBookData.setPublicationYear(bookRequest.getPublicationYear());

            return bookRepository.save(updatedBookData);
        }
        return null;
    }
    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }
    public void deleteAll(){
        bookRepository.deleteAll();
    }
}
