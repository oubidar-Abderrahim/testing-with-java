package test.testing;

public class Palindrome {
    public static boolean isPalindrome(String word) {

    	return word.equalsIgnoreCase(new StringBuilder(word).reverse().toString());
    }
    
    public static void main(String[] args) {
        System.out.println(Palindrome.isPalindrome("Deleveled"));
    }
}