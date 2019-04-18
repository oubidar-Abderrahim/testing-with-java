package projectEuler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.function.Function;

public class FibonacciWords {
	
	private static char getDigitInFibunacci(String a, String b, BigInteger n) {
		
		String previousTerm = a;
		String currentTerm = b;
		String nextTerm = a+b ;
		
		int simpleN = n.intValue();
		
		if( simpleN <= a.length() ) {
			return a.charAt(simpleN-1);
		}
		
		if( simpleN <= b.length() ) {
			return b.charAt(simpleN-1);
		}
		
		previousTerm = currentTerm;
		currentTerm = nextTerm;
		nextTerm = previousTerm + currentTerm ;
		
		
		
		
		return 'c';
	}
	
	public static void doit( Function<String, Boolean> one, String d ) {
		
		System.out.println(one.apply(d));
	}

	public static void main(String[] args) {
		
		System.out.println("يقولون أن أبا سفيان دهب بتجارة إلى الشام");
        
//        Scanner scan = new Scanner(System.in);
//        int q = scan.nextInt();
//        String[] A = new String[q];
//        String[] B = new String[q];
//        BigInteger[] N = new BigInteger[q];
//        for(int i=0; i<q ; i++) {
//        	A[i] = scan.next();
//        	B[i] = scan.next();
//        	N[i] = scan.nextBigInteger();
//        }
    }
}
