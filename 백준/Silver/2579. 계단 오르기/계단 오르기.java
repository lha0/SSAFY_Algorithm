import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] stairs, mem;
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		  
		  N = Integer.parseInt(br.readLine());
		  stairs = new int[N+1];
		  mem = new int[N+1];
		  for (int i = 0; i < N; i++) {
			  stairs[i] = Integer.parseInt(br.readLine());
		  }
		  
		  mem[0] = stairs[0];
		  mem[1] = stairs[0] + stairs[1];
		  
		  for (int i = 2; i < N; i++) {
			  // 현재칸 + 이전 칸(최댓값)을 밟는 경우
			  // 현재칸 + 이전칸 + 전전전칸 (최댓값)을 밟는 경우
			  if (i-3 >= 0) {
				  mem[i] = Math.max(stairs[i] + mem[i-2], stairs[i] + stairs[i-1] + mem[i-3]);				  
			  } else {
				  mem[i] = Math.max(stairs[i] + mem[i-2], stairs[i] + stairs[i-1]);				  				  
			  }
		  }
		  
		  bw.write(mem[N-1] + "\n");
		  
		  br.close();
		  bw.close();
	}
}
