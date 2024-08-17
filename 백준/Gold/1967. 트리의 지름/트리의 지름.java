import java.io.*;
import java.util.*;

/*
 * 인접리스트
 * 시작점(1 ~ N) -> 끝점 (본인 제외 나머지)
 * dfs
 * */

public class Main {
	static int N;
	static List<Node>[] adjList;
	static boolean[] visited;
	static int max = 0;
	
	static class Node {
		int num, weight;
		
		Node(int num, int weight) {
			this.num = num;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine().split(" ")[0]);

		adjList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}


		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			
			adjList[s].add(new Node(e, w));
			adjList[e].add(new Node(s, w));
		}

		
		for (int i = 0; i < N; i++) {
			//각 노드에서 각 노드로 가는 가장 긴 길을 찾는다
			visited = new boolean[N];
			dfs(i, 0);
		}
		
		
		bw.write(max + "\n");
		bw.flush();
		bw.close();
		br.close();

	}
	
	
	public static void dfs(int start, int cost) {
		if (cost > max) {
			max = cost;
		}
		
		visited[start] = true;
		
		
		for (int i = 0; i < adjList[start].size(); i++) {
			Node next = adjList[start].get(i);
			if (!visited[next.num]) {
				visited[next.num] = true;
				dfs(next.num, cost + next.weight);
			}
		}
		
	}


}
