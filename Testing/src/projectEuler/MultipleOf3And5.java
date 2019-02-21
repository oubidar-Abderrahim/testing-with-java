package projectEuler;


/**
 * 
 * @author aoubidar
 * 
 * content : If we list all the natural numbers below 10 that are multiples of 3 or 5,
 * we get 3, 5, 6 and 9. The sum of these multiples is 23.
 * Find the sum of all the multiples of 3 or 5 below 1000.
 *
 */

public class MultipleOf3And5 {
	
	public static int sumOfMultiplesOf3And5Under(int limit) {
		
		int sum = 0 ;
		
		for (int i=1 ; i < limit ; i++) {
			
			if( (i%3 == 0) || (i%5 == 0) )
				sum += i;
		}
		
		return sum;
	}
	
	
	public static void main(String[] args) {
		
		
		System.out.println("The sum of all the multiples of 3 or 5 below 1000 is : " + sumOfMultiplesOf3And5Under(1000));
	}

}
