package library;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** 
 * SmallLibrary represents a small collection of books, like a single person's home collection.
 */
public class SmallLibrary implements Library {

    // This rep is required! 
    // Do not change the types of inLibrary or checkedOut, 
    // and don't add or remove any other fields.
    // (BigLibrary is where you can create your own rep for
    // a Library implementation.)

    // rep
    private Set<BookCopy> inLibrary;
    private Set<BookCopy> checkedOut;
    
    // rep invariant:
    //    the intersection of inLibrary and checkedOut is the empty set
    //
    // abstraction function:
    //    represents the collection of books inLibrary union checkedOut,
    //      where if a book copy is in inLibrary then it is available,
    //      and if a copy is in checkedOut then it is checked out

    // TODO: safety from rep exposure argument
    
    public SmallLibrary() {
        //throw new RuntimeException("not implemented yet");
        inLibrary=new HashSet<BookCopy>();
        checkedOut=new HashSet<BookCopy>();
    }
    
    // assert the rep invariant
    private void checkRep() {
        throw new RuntimeException("not implemented yet");
    }

    @Override
    public BookCopy buy(Book book) {
       // throw new RuntimeException("not implemented yet");
        BookCopy copy=new BookCopy(book);
        inLibrary.add(copy);
        return copy;
    }
    
    @Override
    public void checkout(BookCopy copy) {
        //throw new RuntimeException("not implemented yet");
        if(inLibrary.contains(copy)) {
            checkedOut.add(copy);
            inLibrary.remove(copy);
        }
    }
    
    @Override
    public void checkin(BookCopy copy) {
        //throw new RuntimeException("not implemented yet");
        if(checkedOut.contains(copy)) {
            inLibrary.add(copy);
            checkedOut.remove(copy);
        }
    }
    
    @Override
    public boolean isAvailable(BookCopy copy) {
        //throw new RuntimeException("not implemented yet");
        return inLibrary.contains(copy);
    }
    
    @Override
    public Set<BookCopy> allCopies(Book book) {
        //throw new RuntimeException("not implemented yet");
        Set<BookCopy> ret=new HashSet<BookCopy>();
        for(BookCopy copy:inLibrary) {
            if(copy.getBook().equals(book)) {
                ret.add(copy);
            }
        }
        for(BookCopy copy:checkedOut) {
            if(copy.getBook().equals(book)) {
                ret.add(copy);
            }
        }
        return ret;
    }
    
    @Override
    public Set<BookCopy> availableCopies(Book book) {
       // throw new RuntimeException("not implemented yet");
        Set<BookCopy> ret=new HashSet<BookCopy>();
        for(BookCopy copy:inLibrary) {
            if(copy.getBook().equals(book)) {
                ret.add(copy);
            }
        }
        return ret;
    }

    @Override
    public List<Book> find(String query) {
        //throw new RuntimeException("not implemented yet");
        Set<Book> books=new HashSet<Book>();
        
        for(BookCopy copy:inLibrary) {
           Book book=copy.getBook();
            if(book.getTitle().equals(query)||book.getAuthors().contains(query)) {
                books.add(book);
            }
        }
        for(BookCopy copy:checkedOut) {
            Book book=copy.getBook();
             if(book.getTitle().equals(query)||book.getAuthors().contains(query)) {
                 books.add(book);
             }
         }
        List<Book> ret=new ArrayList<Book>(books);
        ret.sort(new Comparator<Book>() {

           

            @Override
            public int compare(Book o1, Book o2) {
                // TODO Auto-generated method stub
                return o1.getYear()-o2.getYear();
            }
            
        });
        return ret;
    }
    
    @Override
    public void lose(BookCopy copy) {
        //throw new RuntimeException("not implemented yet");
        inLibrary.remove(copy);
        checkedOut.remove(copy);
    }

    // uncomment the following methods if you need to implement equals and hashCode,
    // or delete them if you don't
    // @Override
    // public boolean equals(Object that) {
    //     throw new RuntimeException("not implemented yet");
    // }
    // 
    // @Override
    // public int hashCode() {
    //     throw new RuntimeException("not implemented yet");
    // }
    

    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */
}
