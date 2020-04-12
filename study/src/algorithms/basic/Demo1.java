package algorithms.basic;

public class Demo1 {
	
	public static void main(String[] args) {
		System.out.println(gcd(27,81));
	}
	
	public static int gcd(int p, int q) {
		if(q==0) {
			return p;
		}else {
			int r = p%q;
			return gcd(q,r);
		}
	}

}
