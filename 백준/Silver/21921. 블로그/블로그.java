import java.io.*;
import java.util.*;

public class Main {
	static int N, X;
	static long maxNum, range;
	static int[] visitor;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		visitor = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int next = Integer.parseInt(st.nextToken());
			
			visitor[i] = next;
		}
		
		run();
		
		if (maxNum == 0) bw.write("SAD\n");
		else bw.write(maxNum + "\n" + range + "\n");
		
		br.close();
		bw.close();
	}

	private static void run() {
		int start = 0;
		int end = 0;
		long sum = 0;
		
		while(end <= N) {
			if (end - start != X) {
				sum += visitor[end];
				end++;
				continue;
			}
			
			if (sum > maxNum) {
				maxNum = sum;
				range = 1;
			} else if (sum == maxNum) {
				range++;
			} 
			
			if (end == N) break;
			
			sum -= visitor[start++];
			sum += visitor[end++];
			
		}
	}
}
