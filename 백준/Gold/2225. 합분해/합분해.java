import java.util.*;
import java.io.*;

public class Main {
	static int N, K, answer;
	static int[][] numbers;  
	
	public static void main(String[] args) throws IOException {
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		  
		  StringTokenizer st = new StringTokenizer(br.readLine());
		  N = Integer.parseInt(st.nextToken());
		  K = Integer.parseInt(st.nextToken());
		  
		  //numbers[N][K] = numbers[N-1][K] + numbers[N][K-1]
		  numbers = new int[N+1][K+1];
		
		  for (int i = 1; i <= N; i++) {
			  for (int j = 1; j <= K; j++) {
				  if (i == 1) {
					  numbers[i][j] = j;
				  } else {
					  numbers[i][j] = ( numbers[i-1][j] + numbers[i][j-1] ) % 1000000000;					  
				  }
			  }
		  }
		  
		  answer = numbers[N][K];
		  
		  bw.write(answer + "\n");
		  br.close();
		  bw.close();
		  
	}
	
}
	