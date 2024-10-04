import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static long answer, max;
	static long[] budget;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		budget = new long[N];
		max = -1;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			budget[i] = Integer.parseInt(st.nextToken());
			
			max = Math.max(max, budget[i]);
		}
		
		M = Integer.parseInt(br.readLine());
		
		run();
		
		bw.write(answer + "\n");
		br.close();
		bw.close();
	}
	
	public static void run() {
		long min = 0;
		while(min <= max) {
			long mid = ( min + max ) / 2;
			
			
			long sum = 0;
			for (int i = 0; i < budget.length; i++) {
				if (budget[i] > mid) sum += mid;
				else sum += budget[i];
			}
			
			if (sum > M) max = mid-1;
			else min = mid + 1;
		}
		
		answer = max;
	}

}
