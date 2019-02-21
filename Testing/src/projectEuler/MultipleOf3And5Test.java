package projectEuler;

import org.junit.Assert;
import org.junit.jupiter.api.Test;



class MultipleOf3And5Test {

	@Test
	void testSumOfMultiplesOf3And5Under10() {
		
		Assert.assertEquals(23, MultipleOf3And5.sumOfMultiplesOf3And5Under(10));
	}
	
	@Test
	void testSumOfMultiplesOf3And5Under20() {
		
		Assert.assertEquals(78, MultipleOf3And5.sumOfMultiplesOf3And5Under(20));
	}

}
