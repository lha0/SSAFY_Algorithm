import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static long minNum;
	static long[] liquid, answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		liquid = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			liquid[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(liquid);
		
		answer = new long[2];
		minNum = Long.MAX_VALUE;
		
		run();
		
		Arrays.sort(answer);
		
		for (int i = 0; i < 2; i++) {
			bw.write(answer[i] + " ");
		}
		
		bw.write("\n");
		br.close();
		bw.close();
	}
	
	public static void run() {
		int start = 0;
        int end = N - 1;
        
        while (start < end) {
            long sum =  liquid[start] + liquid[end];
            long diff = Math.abs(sum);
            
            if (diff < minNum) {
                minNum = diff;
                answer[0] = liquid[start];
                answer[1] = liquid[end];
            }
            
            // 합계가 0보다 작으면 start 포인터를 올리고, 크면 end 포인터를 내림
            if (sum < 0) {
                start++;
            } else {
                end--;
            }
        }
		
	}

}
