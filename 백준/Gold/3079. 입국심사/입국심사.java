
import java.util.*;
import java.io.*;

public class Main {
	 static int N, M;
	 static long answer;
	 static long[] times;
	 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		times = new long[N];
		for (int i = 0; i < N; i++) {
			 st = new StringTokenizer(br.readLine());
			 int next = Integer.parseInt(st.nextToken());
			 times[i]= next; 
		}
		
		answer = Long.MAX_VALUE;
		run();
		
		bw.write(answer + "\n");
		br.close();
		bw.close();
	}

	private static void run() {
		long min = 1;
		long max = Long.MAX_VALUE;
		
		while(min <= max) {
			long mid = (min + max) / 2;
			
			long count = 0;
			for (int i = 0; i < times.length; i++) {
				if (times[i] > mid) continue;
				else {
					count += mid / times[i];
				}
				
				if (count > M) break;
			}
		
			if (count < M) { // 시간 부족,
				min = mid + 1;
			} else {
				answer = Math.min(answer, mid);
				max = mid - 1;
			}
			
//			System.out.println(count + " " + mid);
//			System.out.println(min + " " + max);
		}
	}

}
