
import java.util.*;

public class Main {
	static int n, answer;
	static int[] mem;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		mem = new int[n+3];
		
		Arrays.fill(mem, Integer.MAX_VALUE);
		
		mem[1] = 0;
		mem[2] = 1;
		mem[3] = 1;
		
		if (n == 1 || n == 2 ||  n == 3) ;
		
		else {
			for (int i = 4; i <= n; i++) {
				if (i % 2 == 0) {
					mem[i] = Math.min(mem[i], 1 + mem[i/2]);
				}
				if (i % 3 == 0) {
					mem[i] = Math.min(mem[i], 1 + mem[i/3]);
				}
				
				mem[i] = Math.min(mem[i], 1 + mem[i-1]);
				
			}
			
		}
		
		System.out.println(mem[n]);
		
		
	}
}
