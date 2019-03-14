package hackerRank.challenges;

import java.io.*;
import java.util.*;
import java.lang.reflect.*;
////////////////////////////////////////////////////////////////////////////////////////////////////////////
import static java.lang.System.in;

class Prime {

	private boolean isPrime(int a) {
		if( a == 1 ) { 
			return false;
		}
		if (a == 2) {
			return true;
		}
		for (int i = 2; i <= (int) Math.sqrt(a) + 1; i++) {
			if (a % i == 0) {
				return false;
			}
		}
		return true;
	}

	public void checkPrime(int... values) {

		for (int i = 0; i < values.length; i++) {

			if (isPrime(values[i]))
				System.out.print(values[i] + " ");
		}
		System.out.println();
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class PrimeChecker {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			int n1 = Integer.parseInt(br.readLine());
			int n2 = Integer.parseInt(br.readLine());
			int n3 = Integer.parseInt(br.readLine());
			int n4 = Integer.parseInt(br.readLine());
			int n5 = Integer.parseInt(br.readLine());
			Prime ob = new Prime();
			ob.checkPrime(n1);
			ob.checkPrime(n1, n2);
			ob.checkPrime(n1, n2, n3);
			ob.checkPrime(n1, n2, n3, n4, n5);
			Method[] methods = Prime.class.getDeclaredMethods();
			Set<String> set = new HashSet<>();
			boolean overload = false;
			for (int i = 0; i < methods.length; i++) {
				if (set.contains(methods[i].getName())) {
					overload = true;
					break;
				}
				set.add(methods[i].getName());

			}
			if (overload) {
				throw new Exception("Overloading not allowed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
