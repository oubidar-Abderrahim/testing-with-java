package test.testing;

public class ObjectEqualityTesting extends Object {
	
	private int val ;
	
	public ObjectEqualityTesting(int val) {
		super();
		this.setVal(val);
	}

	public static void main(String[] args) {
		
		ObjectEqualityTesting obj1 = new ObjectEqualityTesting(5);
		ObjectEqualityTesting obj2 = new ObjectEqualityTesting(5);
		
		
		System.out.println("obj1 == obj2 : " + (obj1 == obj2));
		System.out.println("obj1.equals(obj2) : " + (obj1.equals(obj2)));

	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

}
