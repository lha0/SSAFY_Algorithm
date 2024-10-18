import java.io.*;

public class Main {
	static int N, K;
	static long answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		run();
		
		bw.write(answer + "\n");
		br.close();
		bw.close();
	}
	private static void run() {
		//lower bound
		//매개변수 (mid) : B[k] 값
		long start = 1;
		long end = K;
		
		while(start < end) {
			long mid = (start + end) / 2;
			
//			System.out.println(start + " " + end);
//			System.out.println("mid " + mid);
			
			long total = 0; // B[k], 즉 mid 값보다 작거나 같은 숫자들의 합
			for (int row = 1; row <= N; row++) {
				// 각 row마다, mid 보다 작은 값은
				// N개 이거나, mid/row 개 (= col 번호) (row * col = 특정값) 
				total += Math.min(N, mid / row);
			}
			
			// mid보다 작은 값의 개수가 K보다 많으면 줄여나가기
			if (K <= total) {
				end = mid;
			} 
			// mid보다 작은 값의 개수가 적으면 start 늘리기
			else {
				start = mid+1;
			}
		}
		
		answer = start;
	}
}
