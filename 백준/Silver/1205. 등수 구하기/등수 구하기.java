import java.util.*;
import java.io.*;

public class Main {
	static int N, newScore, P, answer;
	static Queue<Integer> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] inputline = br.readLine().split(" ");
		N = Integer.parseInt(inputline[0]);
		newScore = Integer.parseInt(inputline[1]);
		P = Integer.parseInt(inputline[2]);
		
		pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
		String[] nextLine = null;
		if (N != 0)
			nextLine = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			int next = Integer.parseInt(nextLine[i]);
			
			pq.offer(next);
		}
		
		for (int i = 0; i < P-N; i++) {
			pq.offer(-1);
		}
		
		int rank = 1;
		
		if (N == 0) {
			answer = 1;
		} else {
			answer = -1;
		}
		
		while(!pq.isEmpty()) {
			int cur = pq.poll();
			
			if (cur < newScore)  {
				answer = rank;
				break;
			}
			else if (cur == newScore) {
				continue;
			}
			
			else {
				rank++;
			}
		}
		
		bw.write(answer + "\n");
		br.close();
		bw.close();
	}

}
