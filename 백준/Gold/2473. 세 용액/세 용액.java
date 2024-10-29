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
		
		answer = new long[3];
		minNum = Long.MAX_VALUE;
		
		for (int i = 0; i < N-2; i++) {
			//용액 하나 지정
			run(i);
		}
		
		Arrays.sort(answer);
		
		for (int i = 0; i < 3; i++) {
			bw.write(answer[i] + " ");
		}
		
		bw.write("\n");
		br.close();
		bw.close();
	}
	
	public static void run(int stand) {
		int start = stand + 1;
        int end = N - 1;
        
        while (start < end) {
            long sum = liquid[stand] + liquid[start] + liquid[end];
            long diff = Math.abs(sum);
            
            if (diff < minNum) {
                minNum = diff;
                answer[0] = liquid[stand];
                answer[1] = liquid[start];
                answer[2] = liquid[end];
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
