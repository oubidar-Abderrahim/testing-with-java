package projectEuler;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class EvenFibunacciNumbersTest {

	@Test
	void testGetEvenFibunacciSumUpTo0() {
		
		Assert.assertEquals(0, EvenFibunacciNumbers.getEvenFibunacciSum(0));
	}
	
	@Test
	void testGetEvenFibunacciSumUpTo1() {
		
		Assert.assertEquals(0, EvenFibunacciNumbers.getEvenFibunacciSum(1));
	}
	
	@Test
	void testGetEvenFibunacciSumUpTo2() {
		
		Assert.assertEquals(0, EvenFibunacciNumbers.getEvenFibunacciSum(2));
	}
	
	@Test
	void testGetEvenFibunacciSumUpTo3() {
		
		Assert.assertEquals(2, EvenFibunacciNumbers.getEvenFibunacciSum(3));
	}
	
	@Test
	void testGetEvenFibunacciSumUpTo10() {
		
		Assert.assertEquals(44, EvenFibunacciNumbers.getEvenFibunacciSum(10));
	}
	

	@Test
	void testGetBiggestIndexSmallerThan100() {
		
		Assert.assertEquals(11, EvenFibunacciNumbers.getBiggestIndexSmallerThan(100));
	}
	
	@Test
	void testGetBiggestIndexSmallerThan1000() {
		
		Assert.assertEquals(16, EvenFibunacciNumbers.getBiggestIndexSmallerThan(1000));
	}

}
