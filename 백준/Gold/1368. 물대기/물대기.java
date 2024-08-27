import java.io.*;
import java.util.*;

public class Main {
	static int N, answer;
	static int[] self;
	static List<Pond>[] adjList;
	static List<Integer> minIdx;
	static Queue<Pond> pq = new PriorityQueue<Pond>();
	
	static class Pond implements Comparable<Pond> {
		int idx, cost;
		
		Pond(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Pond o) {
			return this.cost - o.cost;
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine().split(" ")[0]);
		
		//자기 자신 값
		self = new int[N];
		for (int i = 0; i < N; i++) {
			self[i] = Integer.parseInt(br.readLine().split(" ")[0]);
		}
		
		//인접리스트 초기화
		adjList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		//인풋 받기
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int val = Integer.parseInt(st.nextToken());
				if (val == 0) {
					adjList[i].add(new Pond(i, self[i]));
				} else {
					adjList[i].add(new Pond(j, val));
				}
			}
		}
		
//		minIdx = new ArrayList<>();
//		int minVal = Integer.MAX_VALUE;
//		//직접 물 파는 비용 중 최솟값 찾기
//		for (int i = 0 ; i < N; i++) {
//			if (minVal >= self[i]) {
//				minIdx.add(i);
//			}
//		}
		
		for (int  i = 0; i < self.length; i++) {
			pq.offer(new Pond(i, self[i]));
		}
		
		prim();
		
		for (int i = 0; i < self.length; i++) {
			answer += self[i];
		}

		bw.write(answer + "\n");
		bw.flush();
		bw.close();
		br.close();

	}

	private static void prim() {
		
		boolean[] visited = new boolean[N];
		
		while (!pq.isEmpty()) {
			Pond cur = pq.poll();
			
			if (visited[cur.idx]) continue;
			
			visited[cur.idx] = true;
			
			for (Pond next : adjList[cur.idx]) {
				if (!visited[next.idx] && self[next.idx] > next.cost) {
					pq.offer(new Pond(next.idx, next.cost));
					self[next.idx] = next.cost;
				}
			}
			
		}
		
//		System.out.println(Arrays.toString(self));
	}
	
}
