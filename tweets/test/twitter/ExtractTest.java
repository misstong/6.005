package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * Make sure you have partitions.
     */
    
    /*
     * testing strategy for getTimespan
     * partition the input as follows
     * 
     * tweets:empty list,one tweet,more than one
     */
    
    /*
     * testing strategy for getMentionedUsers
     * 
     * tweets:empty list,@case,lowerorUpper
     */
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it  reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest@mit talk in @ Mit 30 minutes #hype", d2);
    
    @Test 
    public void testGetTimespanEmptyList() {
        Timespan timespan=Extract.getTimespan(Arrays.asList());
        
        assertEquals(null,timespan);
        
    }
    @Test
    public void testGetTimespanOneTweet() {
        Timespan timespan=Extract.getTimespan(Arrays.asList(tweet1));
        
        assertEquals("expected start",d1,timespan.getStart());
        assertEquals("expected end",d1,timespan.getEnd());
    }
    
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
    }
    
    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
    @Test
    public void testGetMentionedUsersEmpty() {
        Set<String> mentionedUsers=Extract.getMentionedUsers(Arrays.asList());
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
    @Test
    public void testGetMentionedUsersUppercase() {
        Set<String> mentionedUsers=Extract.getMentionedUsers(Arrays.asList(tweet2));
        
        assertEquals(makeSet("mit"),mentionedUsers);
    }
    
   
    
    private Set<String> makeSet(String... names){
        Set<String> ret=new HashSet<String>();
        for(String name:names)
            ret.add(name.toLowerCase());
        return ret;
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */


    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
