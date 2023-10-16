
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Implement and test {Programme.addStudent } that respects the considtion given
 * the assignment specification
 * NOTE: You are expected to verify that the constraints to borrow a new book
 * from a library
 *
 * Each test criteria must be in an independent test method .
 *
 * Initialize the test object with "setting" method.
 */
public class IssueBook {
    private LibraryCard libraryCard;
    private Student student;

    @BeforeEach
    public void setUp() {
        // Instantiate Dates for library card
        Calendar calendar = Calendar.getInstance();

        calendar.set(2023, Calendar.JANUARY, 1);
        Date issueDate = calendar.getTime();

        calendar.set(2024, Calendar.OCTOBER, 1);
        Date expiryDate = calendar.getTime();

        Student student = new Student("Bob", 123);
        libraryCard = new LibraryCard(student, issueDate, expiryDate, 123);
    }

    @Test
    public void ReturnsFalse_IfBooksGreaterFour() throws IllegalBookIssueException {
        // Tests whether issuing a book whilst exceeding the max amount of four
        // borrowed books, will return false

        // Init borrowed bookslist
        ArrayList<Book> borrowed_books = new ArrayList<Book>();

        borrowed_books.add(new Book(497, "Whispers in Moonlight", 0));
        borrowed_books.add(new Book(334, "The Enchanted Atlas", 1));
        borrowed_books.add(new Book(125, "Chronicles of Clockwork Kingdom", 0));
        borrowed_books.add(new Book(193, "Echos Of Elysium", 0));
        borrowed_books.add(new Book(183, "Tales of Forgotten Stars", 0));

        libraryCard.setBooksBorrowed(borrowed_books);
        boolean expected = false;

        Book book6 = new Book(3, "art", 0);
        boolean actual = libraryCard.issueBook(book6);

        assertEquals(expected, actual);
    }

    @Test
    public void ThrowsException_IfIllegalBookException() {
        // Tests if an exception will be thrown if a book is already
        // within the the cards borrowed books

        // Init borrowed list of books and add a book to be borrowed
        ArrayList<Book> borrowed_books = new ArrayList<Book>();
        Book book1 = new Book(0, "Throw Away", 0);
        borrowed_books.add(book1);
        libraryCard.setBooksBorrowed(borrowed_books);

        // Try to issue this same book again
        assertThrows(IllegalBookIssueException.class, () -> {
            libraryCard.issueBook(book1);
        });
    }

    @Test
    public void ReturnFalse_IfInvalidCard() throws IllegalBookIssueException {
        // Tests if an invalid card, i.e. a card with an expiry date exceeding current
        // date
        // will return false if invalid card

        // Init date objects to set expiry date
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.OCTOBER, 1);
        Date oldDate = calendar.getTime();
        libraryCard.setExpiryDate(oldDate);

        // expect to return false due to expired date exceeded by current
        Book book1 = new Book(0, "Dated", 0);

        // Set expected and actual variables
        boolean expected = false;
        boolean actual = libraryCard.issueBook(book1);
        assertEquals(expected, actual);
    }

    @Test
    public void ReturnFalse_IfBookUnavailable() throws IllegalBookIssueException {
        // Tests if an issue of an unavailble book returns false

        // Init a book a set it to unavailable
        Book book1 = new Book(0, "Unattainable", 0);
        book1.setStatus(false);

        // Set expected and actual variables
        boolean expected = false;
        boolean actual = libraryCard.issueBook(book1);

        assertEquals(expected, actual);

    }

    @Test
    public void ReturnFalse_IfPendingFine() throws IllegalBookIssueException {
        // Tests if issueing a book with a pending fine returns false

        // Init update fine on library card
        libraryCard.setFine(50.00);
        Book book1 = new Book(0, "How to pay Fines", 0);

        // Set expected and actual variables
        boolean expected = false;
        boolean actual = libraryCard.issueBook(book1);

        assertEquals(expected, actual);

    }

    @Test
    public void Return3_IfDemandHigh() throws IllegalBookIssueException {
        // Tests if a books return day is within 3 days due to high demand (1)

        // Init book object with a demand of 1
        Book book1 = new Book(0, "In Demand", 1);
        libraryCard.issueBook(book1);

        // Set expected and actual variables
        int expected = 3;
        int actual = book1.getDays();

        assertEquals(expected, actual);

    }

    @Test
    public void ReturnTrue_IfPassAllConstraints() throws IllegalBookIssueException {
        // Tests if a issued book returns true if all constraints are met
        Book book1 = new Book(0, "How to pass tests", 0);

        // Set expected and actual variables
        boolean expected = true;
        boolean actual = libraryCard.issueBook(book1);

        assertEquals(expected, actual);
    }

}
