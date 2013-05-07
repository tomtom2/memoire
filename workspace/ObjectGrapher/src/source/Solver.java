package source;
public class Solver {

	private int a;
	private int b;
	private int c;
	private float delta;

	/**
	 * Solver of equation a.X^X + b.X + c = 0
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return result
	 */
	public Solver(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public void calcDelta() {
		this.delta = b * b - 4 * a * c;
	}

	public double calcFirstSol() {
		if(delta<0){
			return 0;
		}
		return (-b - Math.sqrt(delta))/(2*a);
	}

	public double calcSecondSol() {
		if(delta<0){
			return 0;
		}
		return (-b + Math.sqrt(delta))/(2*a);
	}
	
	public void printEquationType(boolean b){
		if(delta==0){
			System.out.println("Solution unique");
		}
		else if(delta>0){
			System.out.println("Deux solutions distinctes");
		}
		else{
			System.out.println("Solutions imaginaires");
		}		
	}
	
	
	public int exemple(int arg1, int arg2){
		int val = 0;
		if(this.a<arg1){
			val = arg1;
		}
		if(val<arg2){
			val += arg2;
		}
		return val;
	}
	
	
}
