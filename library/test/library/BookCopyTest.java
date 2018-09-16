package library;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

/**
 * Test suite for BookCopy ADT.
 */
public class BookCopyTest {

    /*
     * Testing strategy
     * ==================
     * 
     * TODO: your testing strategy for this ADT should go here.
     * Make sure you have partitions.
     */
    
    // TODO: put JUnit @Test methods here that you developed from your testing strategy
    @Test
    public void testToString() {
        Book book=new Book("dog",Arrays.asList("dog"),1990);
        BookCopy copy = new BookCopy(book);
        assertEquals(book.toString()+" good",copy.toString());
        copy.setCondition(BookCopy.Condition.DAMAGED);
        assertEquals(book.toString()+" damaged",copy.toString());
    }
    
    @Test 
    public void testCondition() {
        Book book = new Book("This Test Is Just An Example", Arrays.asList("You Should", "Replace It", "With Your Own Tests"), 1990);
        BookCopy copy = new BookCopy(book);
        assertEquals(copy.getCondition(),BookCopy.Condition.GOOD);
        copy.setCondition(BookCopy.Condition.DAMAGED);
        assertEquals(copy.getCondition(),BookCopy.Condition.DAMAGED);
    }
    @Test
    public void testEquality() {
        Book book = new Book("This Test Is Just An Example", Arrays.asList("You Should", "Replace It", "With Your Own Tests"), 1990);
        BookCopy copy = new BookCopy(book);
        BookCopy copy1 = new BookCopy(book);
        assertTrue(!copy.equals(copy1));
        assertTrue(copy.hashCode()!=copy1.hashCode());
        
    }
    @Test
    public void testExampleTest() {
        Book book = new Book("This Test Is Just An Example", Arrays.asList("You Should", "Replace It", "With Your Own Tests"), 1990);
        BookCopy copy = new BookCopy(book);
        assertEquals(book, copy.getBook());
    }
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }


    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
