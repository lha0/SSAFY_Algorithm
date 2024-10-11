import java.util.*;
import java.io.*;

public class Main {
	static int H, W, answer;
	static int[] list;
	static Stack<Integer> stack;
	
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		  
		  StringTokenizer st = new StringTokenizer(br.readLine());
		  H = Integer.parseInt(st.nextToken());
		  W = Integer.parseInt(st.nextToken());
		  
		  list = new int[W];
		  st = new StringTokenizer(br.readLine());
		  for (int i = 0; i < W; i++) {
			list[i] = Integer.parseInt(st.nextToken());   
		  }
		  
		  run();
		  
		  bw.write(answer + "\n");
		  
		  br.close();
		  bw.close();
	}
	
	public static void run() {
		
		//처음과 마지막은 빗물 못 고임
		for (int i = 1; i < W-1; i++) {
			int leftMax = 0;
			int rightMax = 0;
			
			for (int left = 0; left < i; left++) {
				leftMax = Math.max(list[left], leftMax);
			}
			
			for (int right = i+1; right < W; right++) {
				rightMax = Math.max(list[right], rightMax);
			}
			
			if (leftMax > list[i] && rightMax > list[i]) answer += Math.min(leftMax, rightMax) - list[i];
		}
	}

}
