import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] liquid, answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		liquid = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			liquid[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(liquid);
		
		answer = new int[2];
		
		run();
		
		for (int i = 0; i < 2; i++) {
			bw.write(answer[i] + " ");
		}
		bw.write("\n");
		br.close();
		bw.close();
	}
	
	public static void run() {
		int start = 0;
		int end = N-1;
		int sum = liquid[start] + liquid[end];
		int diff = Math.abs(0 - Math.abs(sum));
		
		int minDiff = diff;
		
		answer = new int[] {liquid[start], liquid[end]};
		
		while (start < end-1) {
			
			if (Math.abs(liquid[start+1] + liquid[end]) < Math.abs(liquid[start] + liquid[end-1])) {
				sum -= liquid[start];
				start++;
				sum += liquid[start];
				diff = Math.abs(0 - Math.abs(sum));
			} else {
				sum -= liquid[end];
				end--;
				sum += liquid[end];
				diff = Math.abs(0 - Math.abs(sum));
			}
			
			
			
//			System.out.println("start " + start + " end " + end);
//			System.out.println("mindiff " + minDiff + " diff " + diff);
			
			if (diff < minDiff) {
				answer = new int[] {liquid[start], liquid[end]};
				minDiff = diff;
			}
		}
		
	}

}
