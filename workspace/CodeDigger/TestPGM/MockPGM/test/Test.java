package MockPGM.test;

import MockPGM.BasicOperations;


public class Test {

	private int a = 5;

	public int exemple(int arg1, int arg2) {
		int val = 0;

		if (a < arg1) {
			val = BasicOperations.toSquare(arg1);
		}
		if (val < arg2) {
			val += arg2;
			if (val * val < arg1) {
				System.out.println("val est plus petit que 1...");
			}
			else{
				val+=1;
			}
			val+=2;
		}
	    else if (val == arg1) {
	    	arg1 = (arg1+arg2)/2;
			System.out.println("LOL");
			return 0+0;
		}
	    else {
			val += 1;
			if (val == 3) {
				System.out.println("poil aux doigts!");
			}
		}
		val = Math.abs(val);
		val = (int) Math.sqrt(val);
		return val;
	}
}