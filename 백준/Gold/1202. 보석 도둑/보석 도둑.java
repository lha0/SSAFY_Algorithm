import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static long answer;
	static Jewerly[] jewerlyArr;
	static int[] bags;
	
	static class Jewerly implements Comparable<Jewerly> {
		int weight, value;
		
		public Jewerly(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		@Override
		public int compareTo(Jewerly o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}

	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		N = Integer.parseInt(stringTokenizer.nextToken());
		K = Integer.parseInt(stringTokenizer.nextToken());
		
		jewerlyArr = new Jewerly[N];
		
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int w = Integer.parseInt(stringTokenizer.nextToken());
			int v = Integer.parseInt(stringTokenizer.nextToken());
		
			jewerlyArr[i] = new Jewerly(w, v);
		}
		
		bags = new int[K];
		for (int i = 0; i < K; i++) {
			bags[i] = Integer.parseInt(bufferedReader.readLine());
		}
		
		Arrays.sort(jewerlyArr);
		Arrays.sort(bags);
		
		run();
		
		bufferedWriter.write(answer + "\n");
		
		bufferedReader.close();
		bufferedWriter.close();
	}

	private static void run() {
		Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		int idx = 0;
		
		for (int k = 0; k < K; k++) {
			int curBag = bags[k];
			
			//가능한 무게의 보석들을 다 pq에 넣는다
			//다음 가방은 idx부터 참조한다
			for (int i = idx; i < N; i++) {
				if (jewerlyArr[i].weight <= curBag) {
					pq.add(jewerlyArr[i].value);
					idx = i+1;
				}

				else {
					break;
				}
			}
			
			if (!pq.isEmpty())
				answer += pq.poll();
			
		}
	}

}
