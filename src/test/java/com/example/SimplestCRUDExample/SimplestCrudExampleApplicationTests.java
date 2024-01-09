package com.example.SimplestCRUDExample;

import com.example.SimplestCRUDExample.controller.BookController;
import com.example.SimplestCRUDExample.model.Book;
import com.example.SimplestCRUDExample.model.BookRequest;
import com.example.SimplestCRUDExample.serviceImpl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SimplestCrudExampleApplicationTests {

	@InjectMocks
	private BookController bookController;

	@Mock
	private BookServiceImpl bookService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllBooks() {
		List<Book> books = new ArrayList<>();
		books.add(new Book(1L, "Book Title", "Author", 2022));
		when(bookService.getAllBooks()).thenReturn(books);

		ResponseEntity<List<Book>> response = bookController.getAllBooks();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
	}

	@Test
	void testGetBookById() {
		long bookId = 1L;
		Book book = new Book(bookId, "Book Title", "Author", 2022);
		when(bookService.getBookById(bookId)).thenReturn(book);

		ResponseEntity<Book> response = bookController.getBookById(bookId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(bookId, response.getBody().getId());
	}

	// Add necessary import statements for other test cases as required

	// Additional test cases should go here based on specific functionalities

}
