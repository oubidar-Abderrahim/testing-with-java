package projectEuler;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aoubidar
 *
 * Each new term in the Fibonacci sequence is generated by adding the previous two terms. 
 * By starting with 0 and 1, the first 10 terms will be: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ...
 *
 * By considering the terms in the Fibonacci sequence whose values do not exceed four million, 
 * find the sum of the even-valued terms.
 *
 */

public class EvenFibunacciNumbers {

	public static long getEvenFibunacciSum(int n) {

		long sum = 0;
		
		for(int i=3 ; i<=n ; i++) {
			
			if( Fibunacci.atIndex(i)%2 == 0 )
				sum += Fibunacci.atIndex(i) ;
			
		}
		return sum;
	}
	
	public static int getBiggestIndexSmallerThan(long l) {
		
		return Fibunacci.getBiggestIndexSmallerThan(l);
	}
	
	
	public static void main(String[] args) {
		
		System.out.println("Biggest index with value less than 4M : " + Fibunacci.getBiggestIndexSmallerThan(4000000));
		System.out.println("Biggest value less than 4M : " + Fibunacci.atIndex(33));
		System.out.println("Solution : ");
		System.out.println(getEvenFibunacciSum(33));
	}
	

	static class Fibunacci {
		
		private static Map<Integer, Long> FIBUNACCI_SUIT = new HashMap<>(); 
		
		public static long atIndex(int n) {
			
			if( FIBUNACCI_SUIT.containsKey(n) ) {
				
				return FIBUNACCI_SUIT.get(n).longValue();
			}
			
			double rootOf5 = Math.sqrt(5.0);
			
			double numerator = Math.pow((1 + rootOf5), n) + Math.pow((1 - rootOf5), n);
			
			double denominator = Math.pow(2, n) * rootOf5 ;
			
			double fraction = numerator / denominator ;
			
			long result = (long) (fraction + 0.4);
			
			FIBUNACCI_SUIT.put(n, result);
			
			return result;
		}
		
		
		public static int getBiggestIndexSmallerThan(long l) {
			
			int index = 0;
			
			while( Fibunacci.atIndex(index) < l ) {
				index++;
			}
			
			return index > 0 ? index -1 : 0 ;
		}
		
	}
}
