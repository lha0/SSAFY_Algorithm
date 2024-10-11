import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] tops, answer;
	static Stack<Integer> stack;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		 st = new StringTokenizer(br.readLine());
		 tops = new int[N];
		 answer = new int[N];
		 for (int i = 0; i < N; i++) {
			 tops[i] = Integer.parseInt(st.nextToken());
		 }
		 
		 answer[0] = 0;
		 stack = new Stack<>();
		 run();
		 
		 for (int i = 0; i < answer.length; i++) {
			 bw.write(answer[i]+ " ");
		 }
		 
		br.close();
		bw.close();
	}

	private static void run() {
		
		answer[0]  = 0;
		stack.push(0);
		int maxidx = 0;
		
		for (int i = 1; i < tops.length; i++) {
			int topidx = stack.peek();
			int topVal = tops[topidx];
			int newVal = tops[i];
			
			if (topVal < newVal) {
				while(topVal < newVal) {
					stack.pop();
					
					if (stack.size() != 0) {
						topidx = stack.peek();
						topVal = tops[topidx];
					} else {
						break;
					}
					
				}
				
				if (stack.size() == 0) answer[i] = 0;
				else answer[i]= topidx + 1;
				
				stack.push(i);
				maxidx = i;
				
			} else {
				answer[i] = topidx + 1; 
				stack.push(i);
			}
		}
	}

}
