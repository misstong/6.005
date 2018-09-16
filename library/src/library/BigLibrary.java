package library;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * BigLibrary represents a large collection of books that might be held by a city or
 * university library system -- millions of books.
 * 
 * In particular, every operation needs to run faster than linear time (as a function of the number of books
 * in the library).
 */
public class BigLibrary implements Library {

    // TODO: rep
    Map<Book,Set<BookCopy>> inLibrary;
    Map<Book,Set<BookCopy>> checkedOut;
    // TODO: rep invariant
    // TODO: abstraction function
    // TODO: safety from rep exposure argument
    
    public BigLibrary() {
        //throw new RuntimeException("not implemented yet");
        inLibrary=new HashMap<>();
        checkedOut=new HashMap<>();
        
    }
    
    // assert the rep invariant
    private void checkRep() {
        throw new RuntimeException("not implemented yet");
    }

    @Override
    public BookCopy buy(Book book) {
        //throw new RuntimeException("not implemented yet");
        BookCopy copy=new BookCopy(book);
        if(inLibrary.containsKey(book)) {
            inLibrary.get(book).add(copy);
        }else {
            Set<BookCopy> copies=new HashSet<>();
            copies.add(copy);
            inLibrary.put(book, copies);
        }
        return copy;
    }
    
    @Override
    public void checkout(BookCopy copy) {
        //throw new RuntimeException("not implemented yet");
        Book book=copy.getBook();
        if(inLibrary.containsKey(book)) {
            if(inLibrary.get(book).contains(copy)) {
                inLibrary.get(book).remove(copy);
                
                if(checkedOut.containsKey(book)) {
                    checkedOut.get(book).add(copy);
                }else {
                    Set<BookCopy> copies=new HashSet<>();
                    copies.add(copy);
                    checkedOut.put(book, copies);
                }
            }
            
        }
    }
    
    @Override
    public void checkin(BookCopy copy) {
        //throw new RuntimeException("not implemented yet");
        Book book=copy.getBook();
        if(checkedOut.containsKey(book)) {
            if(checkedOut.get(book).contains(copy)) {
                checkedOut.get(book).remove(copy);
                
                if(inLibrary.containsKey(book)) {
                    inLibrary.get(book).add(copy);
                }else {
                    Set<BookCopy> copies=new HashSet<>();
                    copies.add(copy);
                    inLibrary.put(book, copies);
                }
            }
           
        }
    }
    
    @Override
    public Set<BookCopy> allCopies(Book book) {
        //throw new RuntimeException("not implemented yet");
        Set<BookCopy> ret=new HashSet<BookCopy>();
        if(inLibrary.containsKey(book)) {
            ret.addAll(inLibrary.get(book));
        }
        if(checkedOut.containsKey(book)) {
            ret.addAll(checkedOut.get(book));
        }
        return ret;
    }

    @Override
    public Set<BookCopy> availableCopies(Book book) {
        //throw new RuntimeException("not implemented yet");
        Set<BookCopy> ret=new HashSet<BookCopy>();
        if(inLibrary.containsKey(book)) {
            ret.addAll(inLibrary.get(book));
        }
        return ret;
    }
    
    @Override
    public boolean isAvailable(BookCopy copy) {
        //throw new RuntimeException("not implemented yet");
        Book book=copy.getBook();
        if(inLibrary.containsKey(book)) {
            return inLibrary.get(book).contains(copy);
        }else
        {
            return false;
        }
    }
    
    @Override
    public List<Book> find(String query) {
        //throw new RuntimeException("not implemented yet");
        Set<Book> books=new HashSet<Book>();
        books.addAll(inLibrary.keySet());
        books.addAll(checkedOut.keySet());
        List<Book> ret=new ArrayList<Book>();
        for(Book book:books) {
            if(book.getAuthors().contains(query)||book.getTitle().equals(query)) {
                ret.add(book);
            }
        }
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
        Book book=copy.getBook();
        if(inLibrary.containsKey(book)) {
            inLibrary.get(book).remove(copy);
        }
        if(checkedOut.containsKey(book)) {
            checkedOut.get(book).remove(copy);
        }
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
