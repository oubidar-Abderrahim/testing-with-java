package hackerRank.challenges;

import java.io.*;
import java.util.*;

interface PerformOperation {

	boolean check(int a);
}

class MyMath {

	public boolean checker(PerformOperation p, int num) {
		return p.check(num);
	}

	public PerformOperation isOdd() {
		return (a -> {
			return (a % 2 == 1);
		});
	}

	public PerformOperation isPrime() {
		return (a -> {
			
			if( a == 1 ) { 
				return false;
			}
			if( a == 2 ) { 
			   return true;
			}
			for(int i=2 ; i <= (int) Math.sqrt(a) + 1 ; i++ ) {
		      if(a % i == 0){ 
		        return false;
		      }
		    }
		    return true; 
		});
	}

	public PerformOperation isPalindrome() {
		return (a -> {
			String word = Integer.toString(a);
			return word.equalsIgnoreCase(new StringBuilder(word).reverse().toString());
		});
	}
}

public class LamdaExpression {

	public static void main(String[] args) throws IOException {
		
		MyMath ob = new MyMath();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		PerformOperation op;
		boolean ret = false;
		String ans = null;
		
		while (T-- > 0) {
			String s = br.readLine().trim();
			StringTokenizer st = new StringTokenizer(s);
			int ch = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			if (ch == 1) {
				op = ob.isOdd();
				ret = ob.checker(op, num);
				ans = (ret) ? "ODD" : "EVEN";
			} else if (ch == 2) {
				op = ob.isPrime();
				ret = ob.checker(op, num);
				ans = (ret) ? "PRIME" : "COMPOSITE";
			} else if (ch == 3) {
				op = ob.isPalindrome();
				ret = ob.checker(op, num);
				ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

			}
			System.out.println(ans);
		}
	}
}
