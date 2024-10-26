import java.util.*;
import java.io.*;

public class Main {
	static int T, N, M, W;
	static List<Node> edgeList;
	static String answer;
	
	static class Node {
		int s, e, w;
		
		Node(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
		
		@Override
		public String toString() {
			return "Start " + this.s + " End " + this.e + " Cost " + this.w;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			edgeList = new ArrayList<>();
			
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				
				edgeList.add(new Node(S, E, T));
				edgeList.add(new Node(E, S, T));
			}
			
			for (int w = 0; w < W; w++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				
				edgeList.add(new Node(S, E, -T));
			}
			
			answer = "NO";
			
			run();
			
			bw.write(answer + "\n");
		}
		
		
		br.close();
		bw.close();
	}

	private static void run() {
		//벨만포드
		//거리배열
		int [] dist = new int[N+1];
		Arrays.fill(dist, 100001);
		dist[1] = 0;
		
		//정점
		for (int i = 1; i <= N; i++) {
			//간선
			for (Node node : edgeList) {
				if (dist[node.e] > dist[node.s] + node.w) {
					dist[node.e] = dist[node.s] + node.w;
				}
			}
		}
		
		//음수 사이클
		for (int i = 1; i <= N; i++) {
			for (Node node : edgeList) {
				if (dist[node.e] > dist[node.s] + node.w) {
					answer = "YES";
					return;
				}
			}
		}
		
	}

}
