import java.util.*;
import java.io.*;

public class Main {
	static long A, B, C;

	public static void main(String[] args) throws IOException {
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");

        A = Integer.parseInt(input[0]);
        B = Integer.parseInt(input[1]);
        C = Integer.parseInt(input[2]);
	
		/* 동작 */
		long result = run(A, B, C);
		
		/* 출력 */
		bw.write(result + "\n");
        bw.flush();
        br.close();
        bw.close();

	}
	
	public static long run (long A, long B, long C) {
		if (B == 1) {
			return A % C;
		} 
		
		long mult = run (A, B/2, C);
		
		//짝수
		if (B % 2 == 0) {
			return (mult * mult) % C;
		} 
		
		//홀수
		else {
			return (mult * mult % C) * A % C;
		}
		
	}
}