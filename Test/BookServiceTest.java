import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import seminars.fourth.book.Book;
import seminars.fourth.book.BookRepository;
import seminars.fourth.book.BookService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BookServiceTest {
    private static BookRepository mockRepository;
    private static BookService bookService;
    @BeforeAll
    public static void setUp() {
        mockRepository = Mockito.mock(BookRepository.class);
        bookService = new BookService(mockRepository);
    }
    @Test
    public void testFindBookById() {
        when(mockRepository.findById("1")).thenReturn(new Book("1", "Book1", "Author1"));
        Book result = bookService.findBookById("1");

        Mockito.verify(mockRepository).findById("1");
        assertEquals("Book1", result.getTitle());
        assertEquals("Author1", result.getAuthor());
    }

    @Test
    public void testFindAllBooks() {
        when(mockRepository.findAll()).thenReturn(Arrays.asList(
                new Book("1", "Book1", "Author1"),
                new Book("2", "Book2", "Author2")
        ));
        List<Book> result = bookService.findAllBooks();

        Mockito.verify(mockRepository).findAll();
        assertEquals(2, result.size());
        assertEquals("Book1", result.get(0).getTitle());
        assertEquals("Author1", result.get(0).getAuthor());
        assertEquals("Book2", result.get(1).getTitle());
        assertEquals("Author2", result.get(1).getAuthor());
    }
}
