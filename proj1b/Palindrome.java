package proj1b;
import org.junit.Test;
import proj1b.Deque;
import proj1a.ArrayDeque;

public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> deque = new ArrayDeque<Character>();
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            deque.addLast(ch);
        }
        return deque;
    }
    public boolean isPalindrome(String s) {
        for(int i = 0, j = s.length() - 1; i < j;i++,j--){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
        }
        return true;
    }
    public boolean isPalindrome(String s,CharacterComparator c) {
        for(int i = 0, j = s.length() - 1; i < j;i++,j--){
            if(!c.equalChars(s.charAt(i),s.charAt(j))){
                return false;
            }
        }
        return true;
    }
}
