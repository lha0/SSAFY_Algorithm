import java.util.*;
import java.io.*;

public class Main {
	static int N, r, c;

	public static void main(String[] args) throws IOException {
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        r = Integer.parseInt(input[1]);
        c = Integer.parseInt(input[2]);
	
		/* 동작 */
		int result = run(N, r, c);
		
		/* 출력 */
		bw.write(result + "\n");
        bw.flush();
        br.close();
        bw.close();

	}
	
	public static int run (int n, int newR, int newC) {
		if (n == 1) {
			if (newR == 0 && newC == 0) {
				return 0;
			} else if (newR == 0 && newC == 1) {
				return 1;
			} else if (newR == 1 && newC == 0) {
				return 2;
			} else if (newR == 1 && newC == 1) {
				return 3;
			} 
		}
		
		int total = (int) Math.pow(2, n);
		int half = total / 2;
		int section = 1;
		
		//1사분면
		if (newR < half && newC < half) {
			section = 1;
			return (half * half * (section-1)) + run(n-1, newR, newC);
		} 
		//2사분면
		else if (newR < half && newC >= half) {
			section = 2;
			return (half * half * (section-1)) + run(n-1, newR, newC-half);
		}
		//3사분면
		else if (newR >= half && newC < half) {
			section = 3;
			return (half * half * (section-1)) + run(n-1, newR-half, newC);
		}
		//4사분면
		else if (newR >= half && newC >= half) {
			section = 4;
			return (half * half * (section-1)) + run(n-1, newR-half, newC-half);
		}
		return 0;
	}
}