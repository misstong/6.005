/* Copyright (c) 2007-2017 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Optional;

import org.junit.Test;

/**
 * TODO: Description
 */
public class BoardTest {
    
    // TODO: Testing strategy
    
    //private static final int Optional = 0;

    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO: Tests
    @Test
    public void test() {
        Optional<File> file=Optional.of(new File("D:\\ThunderDownload\\6.005\\2\\1.txt"));
        Board b=new Board(file,2,2);
        System.out.println(b.toString());
        assertEquals(true,b.hasBomb(1,1));
    }
}
