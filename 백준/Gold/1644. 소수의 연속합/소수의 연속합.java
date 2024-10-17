
import java.io.*;
import java.util.*;

public class Main {
	static int N, answer;
	static List<Integer> prime;
	static boolean[] notPrime;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		//소수가 아닌 것 true, 맞으면 false
		findPrime();
		
		//자기 자신이 소수면 answer + 1
		if (!notPrime[N]) answer++;
	
		if (N < 5) {
			bw.write(answer + "\n");
		} else {
			prime = new ArrayList<>();
			//소수 판별
			for (int num = 2; num <= N; num++) {
				if (!notPrime[num]) prime.add(num);
			}
			
			//소수 리스트로 합 구하기
			run();
			
			bw.write(answer + "\n");
		}
		
		br.close();
		bw.close();

	}
	


	private static void findPrime() {
		notPrime = new boolean[N+1];
		notPrime[1] = true;
		
		for (int i = 2; i <= Math.sqrt(N); i++) {
			if (!notPrime[i]) {
				for (int j = i*i; j <= N; j+=i) {
					notPrime[j] = true;
				}
			}
		}
	}


	private static void run() {
		int start = 0;
		int end = start+1;
		int sum = prime.get(start) + prime.get(end);
		
		while(start < end) {
			if (sum == N) {
				answer++;
				end++;
				sum += prime.get(end);
			}
			
			else if (sum < N) { //아직 합이 더 작으면 늘려가기
				end++;
				sum += prime.get(end);
				
			}
			
			else { //합이 더 크면 줄이기
				sum -= prime.get(start);
				start++;
				
			}
			
		}
	}

}
