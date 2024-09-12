
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[] mem = new int[n+3];
		mem[1] = 1;
		mem[2] = 2;
		
		
		for (int i = 3; i <= n; i++) {
			mem[i] = (mem[i-1] + mem[i-2]) % 15746;
		}
		
		System.out.println(mem[n]);
	}

}
