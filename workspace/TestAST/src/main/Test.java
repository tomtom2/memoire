package main;

public class Test {

	private int a = 5;
	
	
	public int exemple(int arg1, int arg2){
		int val = 0;
		
		if(a<arg1){ val = arg1; }
		
		if(val<arg2){ val += arg2; }
		else if(val==arg1){ System.out.println("LOL"); }
		else{
			val += 1;
			if(val==3){
				System.out.println("poil aux doigts!");
			}
		}
		
		val = Math.abs(val);
		
		return val;
	}
}
