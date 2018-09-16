package twitter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
        //throw new RuntimeException("not implemented");
        if(tweets.isEmpty()) return null;
        
        if(tweets.size()==1) return new Timespan(tweets.get(0).getTimestamp(),tweets.get(0).getTimestamp());
        
        Instant start=null, end=null;
        for(Tweet tweet:tweets) {
            Instant tmp=tweet.getTimestamp();
            if(start==null&&end==null) {
                start=tmp;
                end=start;
            }else {
                if(start.isAfter(tmp)) {
                    start=tmp;
                }
                if(end.isBefore(tmp))
                    end=tmp;
                
            }
            
                
        }
        return new Timespan(start,end);
        
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    private static final String validChars="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_-";
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        //throw new RuntimeException("not implemented");
        
        Set<String> ret=new HashSet<String>();
        if(tweets.isEmpty()) return ret;
        
        for(Tweet tweet:tweets) {
            String text=tweet.getText();
            /*int index=-1;
            while((index=text.indexOf("@"))!=-1) {
                if(isValidAt(text,index)) {
                    String tmp=extract(text,index);
                    if(tmp!=null)
                        ret.add(tmp);
                    text=text.substring(index+1);
                }
            }
            */
            ret.addAll(getMentionedUsers(text));
           
        }
        return ret;
    }
    public static Set<String> getMentionedUsers(String tweet){
        Set<String> ret=new HashSet<String>();
        
        String text=tweet;
        int index=-1;
        while((index=text.indexOf("@"))!=-1) {
            //System.out.println(text+" "+index);
            if(isValidAt(text,index)) {
                String tmp=extract(text,index);
                System.out.println(tmp);
                if(tmp!=null)
                    ret.add(tmp);
                
            }
            text=text.substring(index+1);
        }
        return ret;
        
    }
    private static String extract(String text,int index) {
        int i=index+1;
        while(i<text.length()&&!validChar(text.charAt(i))) {
            i++;
        }
        int j=i;
        while(j<text.length()&&validChar(text.charAt(j))) {
            j++;
        }
        if(i!=j)
            return text.substring(i, j).toLowerCase();
        else return null;
    }
    private static boolean isValidAt(String text,int index) {
        if(index!=0&&validChar(text.charAt(index-1)))   return false;
        if(index!=text.length()-1&&validChar(text.charAt(index+1))) return false;        
        return true;        
    }
    private static boolean validChar(char c) {
        return validChars.indexOf(c)!=-1;
    }
    

    /* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */
}
