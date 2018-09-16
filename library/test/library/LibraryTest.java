package library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 * Test suite for Library ADT.
 */
@RunWith(Parameterized.class)
public class LibraryTest {

    /*
     * Note: all the tests you write here must be runnable against any
     * Library class that follows the spec.  JUnit will automatically
     * run these tests against both SmallLibrary and BigLibrary.
     */

    /**
     * Implementation classes for the Library ADT.
     * JUnit runs this test suite once for each class name in the returned array.
     * @return array of Java class names, including their full package prefix
     */
    @Parameters(name="{0}")
    public static Object[] allImplementationClassNames() {
        return new Object[] { 
            "library.SmallLibrary", 
            "library.BigLibrary"
        }; 
    }

    /**
     * Implementation class being tested on this run of the test suite.
     * JUnit sets this variable automatically as it iterates through the array returned
     * by allImplementationClassNames.
     */
    @Parameter
    public String implementationClassName;    

    /**
     * @return a fresh instance of a Library, constructed from the implementation class specified
     * by implementationClassName.
     */
    public Library makeLibrary() {
        try {
            Class<?> cls = Class.forName(implementationClassName);
            return (Library) cls.newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    /*
     * Testing strategy
     * ==================
     * 
     * TODO: your testing strategy for this ADT should go here.
     * Make sure you have partitions.
     */
    
    // TODO: put JUnit @Test methods here that you developed from your testing strategy
    
    @Test
    public void testFind1() {
        Library library = makeLibrary();
        Book book = new Book("dog",Arrays.asList("cat"),1990);
        BookCopy copy1=library.buy(book);
        library.checkout(copy1);
        assertEquals(library.find("dog"),Arrays.asList(book));
        
    }
    @Test
    public void testFind2() {
        Library library = makeLibrary();
        Book book = new Book("dog",Arrays.asList("cat"),1990);
        BookCopy copy1=library.buy(book);
        library.checkout(copy1);
        assertEquals(library.find("cat"),Arrays.asList(book));
        
    }
    @Test
    public void testFind3() {
        Library library = makeLibrary();
        Book book = new Book("dog",Arrays.asList("cat"),1990);
        BookCopy copy1=library.buy(book);
        Book book2 = new Book("dog",Arrays.asList("cat"),1891);
        BookCopy copy2=library.buy(book2);
        library.checkout(copy1);
        assertEquals(library.find("cat"),Arrays.asList(book2,book));
        
    }
    @Test
    public void testCheck() {
        Library library = makeLibrary();
        Book book = new Book("dog",Arrays.asList("dog"),1990);
        BookCopy copy1=library.buy(book);
        assertTrue(library.isAvailable(copy1));
        library.checkout(copy1);
        assertTrue(!library.isAvailable(copy1));
        library.checkin(copy1);
        assertTrue(library.isAvailable(copy1));
    }
    
    @Test
    public void testCopies() {
        Library library = makeLibrary();
        Book book = new Book("dog",Arrays.asList("dog"),1990);
        BookCopy copy1=library.buy(book);
        BookCopy copy2=library.buy(book);
        assertTrue(library.allCopies(book).size()==2);
        
        library.checkout(copy1);
        assertTrue(!library.isAvailable(copy1));
        assertTrue(library.availableCopies(book).contains(copy2));
        assertTrue(library.availableCopies(book).size()==1);
        
    }
    @Test
    public void testBuy() {
        Library library = makeLibrary();
        Book book = new Book("dog",Arrays.asList("dog"),1990);
        
        assertTrue("book available",library.isAvailable(library.buy(book)));
    }
    @Test
    public void testExampleTest() {
        Library library = makeLibrary();
        Book book = new Book("This Test Is Just An Example", Arrays.asList("You Should", "Replace It", "With Your Own Tests"), 1990);
        assertEquals(Collections.emptySet(), library.availableCopies(book));
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
