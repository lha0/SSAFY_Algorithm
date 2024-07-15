import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static StringBuilder message = new StringBuilder();

	public static void main(String[] args) throws IOException {
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
       
		/* 동작 */
		int result = run(N, 1, 2, 3);
		
		/* 출력 */
		bw.write(result + "\n");
		bw.write(message.toString());
        bw.flush();
        br.close();
        bw.close();

	}
	
	public static int run (int n, int start, int mid, int end) {
		if (n == 1) {
			message.append(start).append(" ").append(end).append("\n");
			return 1;
		} 
		
		int count = 0;
		//n-1개를 mid 기둥으로
		//start에서 출발 -> 마지막 기둥을 거쳐 -> mid로
		count += run(n-1, start, end, mid);
		
		//제일 큰 원을 마지막 기둥으로
		count += 1;
		message.append(start).append(" ").append(end).append("\n");
		
		//다시 n-1개를 마지막 기둥으로
		//mid에서 출발 -> start를 거쳐 -> end로
		count += run (n-1, mid, start, end);
		
		return count;
	}
}