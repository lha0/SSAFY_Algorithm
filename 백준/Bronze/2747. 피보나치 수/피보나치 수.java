
import java.util.*;

public class Main {
	static int[] fibo;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		fibo = new int[n+1];
		
		fibo[0] = 0;
		fibo[1] = 1;
		for (int i = 2; i < n+1; i++) {
			fibo[i] = fibo[i-1] + fibo[i-2];
		}
		
		System.out.println(fibo[n]);
		
	}

}
