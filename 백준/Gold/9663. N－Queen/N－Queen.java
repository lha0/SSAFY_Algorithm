
import java.io.*;
import java.util.*;

public class Main {
	static int N, answer;
	static int[] col;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine().split(" ")[0]);
		
		boolean[] colCheck = new boolean[N];
		boolean[] diff = new boolean[2*N];
		boolean[] sum = new boolean[2*N];
		queen(0, colCheck, diff, sum);
		
		bw.write(answer + "\n");
		bw.flush();
		bw.close();
		br.close();

	}
	
	static public void queen(int row, boolean[] colCheck, boolean[] diff, boolean[] sum) {
		if (row == N-1) {
			for (int c = 0; c < N; c++) {
				int diffNum = (row - c) + N;
				int sumNum = (row+c);
				
				//열 체크
				if (!colCheck[c] && !diff[diffNum] && !sum[sumNum]) {
					answer++;
				}
			}
		
			return;
		}
		
		//놓으려는 자리 = c = 열
		for (int c = 0; c < N; c++) {
			
			int diffNum = (row - c) + N;
			int sumNum = (row+c);
			
			//열 체크
			if (!colCheck[c] && !diff[diffNum] && !sum[sumNum]) {
				colCheck[c] = true;
				diff[diffNum] = true;
				sum[sumNum] = true;
				
				queen(row+1, colCheck, diff, sum);
				
				colCheck[c] = false;
				diff[diffNum] = false;
				sum[sumNum] = false;
			}
			
		}
		
		
	}

}
