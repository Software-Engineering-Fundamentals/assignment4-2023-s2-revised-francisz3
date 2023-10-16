
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Library Card associated with the Student
 */
public class LibraryCard {
    /**
     * Card id
     */
    private int ID;

    /**
     * Issue Date of the Card
     */
    private Date IssueDate;

    /**
     * Expiry Date of the Card
     */
    private Date ExpiryDate;

    /**
     * Number of books borrowed
     */
    private List<Book> borrowed = new ArrayList<Book>();

    /**
     * Fine asscoaited with the card
     */
    private double fine;

    /**
     * Details about the cardholder
     */
    private Student student;

    public LibraryCard(Student student, Date IssueDate, Date ExpiryDate, int ID) {
        this.student = student;
        this.IssueDate = IssueDate;
        this.ExpiryDate = ExpiryDate;
        this.ID = ID;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public Date getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(Date IssueDate) {
        this.IssueDate = IssueDate;
    }

    public Date getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(Date ExpiryDate) {
        this.ExpiryDate = ExpiryDate;
    }

    public List<Book> getBooks() {
        return borrowed;
    }

    // add books to borrowed list
    public void setBooksBorrowed(ArrayList<Book> booklist) {
        this.borrowed = booklist;
    }

    public void addBorrowbook(Book book) {
        borrowed.add(book);
    }

    /**
     * Issue a new book
     * 
     * @param Book: book to borrow
     * @return true if the book is successfully borrowed, false otherwise
     */

    public boolean issueBook(Book book) throws IllegalBookIssueException {

        // Get the number of books borrowed by the student on the library card
        int numBooksBorrowed = borrowed.size();

        // The number of books borrowed should not be greater than 4
        if (numBooksBorrowed > 4) {
            return false;
        }

        // Throws an exception if the same book is already issued on the library card

        if (borrowed.contains(book)) {
            throw new IllegalBookIssueException("Book is already issued on this library card");
        }

        // Check that the library card is still valid

        Date currDate = new Date();
        int isValid = currDate.compareTo(this.ExpiryDate);
        if (isValid > 0) {
            // isValid will be greater than 0 if the current date exceeds the expiry date
            return false;
        }

        // Check that the book is available for borrowing
        if (book.getStatus() == false) {
            return false;
        }

        // Check if library card has pending fines
        if (this.fine > 0.0) {
            return false;
        }

        // Check demand, set days based off demand
        if (book.getDemand() == 0) {
            book.setDays(15);
        } else if (book.getDemand() == 1) {
            book.setDays(3);
        }

        // if method has yet to return false

        // update relevant variables - add to borrowed, Not available
        borrowed.add(book);
        book.setStatus(false);

        return true;

    }

}
