package library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Book is an immutable type representing an edition of a book -- not the physical object, 
 * but the combination of words and pictures that make up a book.  Each book is uniquely
 * identified by its title, author list, and publication year.  Alphabetic case and author 
 * order are significant, so a book written by "Fred" is different than a book written by "FRED".
 */
public class Book {

    // TODO: rep
    private final String title;
    private final List<String> authors;
    private final int year;
    // TODO: rep invariant
    //
    // TODO: abstraction function
    // TODO: safety from rep exposure argument
    //title year are private and final ,string is immutable,authors is final ,list is mutable so defensive copy
    
    /**
     * Make a Book.
     * @param title Title of the book. Must contain at least one non-space character.
     * @param authors Names of the authors of the book.  Must have at least one name, and each name must contain 
     * at least one non-space character.
     * @param year Year when this edition was published in the conventional (Common Era) calendar.  Must be nonnegative. 
     */
    public Book(String t, List<String> a, int y) {
        //throw new RuntimeException("not implemented yet");
        this.title=t;
        List<String> authorscopy=new ArrayList<String>(a);
        
        this.authors=authorscopy;
        this.year=y;
    }
    
    // assert the rep invariant
    private void checkRep() {
        throw new RuntimeException("not implemented yet");
    }
    
    /**
     * @return the title of this book
     */
    public String getTitle() {
        //throw new RuntimeException("not implemented yet");
        return title;
    }
    
    /**
     * @return the authors of this book
     */
    public List<String> getAuthors() {
        //throw new RuntimeException("not implemented yet");
        List<String> authorscopy=new ArrayList<String>(authors);
        
        return authorscopy;
    }

    /**
     * @return the year that this book was published
     */
    public int getYear() {
       // throw new RuntimeException("not implemented yet");
        return year;
    }

    /**
     * @return human-readable representation of this book that includes its title,
     *    authors, and publication year
     */
    public String toString() {
        //throw new RuntimeException("not implemented yet");
        return title+authors.toString()+String.valueOf(year);
    }

    // uncomment the following methods if you need to implement equals and hashCode,
    // or delete them if you don't
     @Override
     public boolean equals(Object that) {
         //throw new RuntimeException("not implemented yet");
         
         if(!(that instanceof Book)) return false;
         Book thatbook=(Book)that;
         return title.equals(thatbook.getTitle())&&year==thatbook.getYear()&&
                authors.equals(thatbook.getAuthors());     
     }
    // 
    // @Override
    public int hashCode() {
         //throw new RuntimeException("not implemented yet");
        return title.hashCode()+authors.hashCode()+year;
     }



    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
