import java.io.*;
import java.util.*;

public class Main {
	static int N, maxCost, maxNode;
	static boolean[] visited;
	static List<Node>[] adjList;
	
	static class Node {
		int node, dist;
		
		Node(int node, int dist) {
			this.node = node;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine().split(" ")[0]);
		maxCost = 0;
		
		adjList = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			adjList[i] = new ArrayList<Node>();
		}
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int curNode = Integer.parseInt(st.nextToken());
			
			while(true) {
				int node = Integer.parseInt(st.nextToken());
				if (node == -1) break;
				
				int dist = Integer.parseInt(st.nextToken());
				
				adjList[node].add(new Node(curNode, dist));
				adjList[curNode].add(new Node(node, dist));
			}
		}
		
		run();
		
		bw.write(maxCost + "\n");
		
		br.close();
		bw.close();
	}

	private static void run() {
		//1번에서 가장 먼 노드 찾기
		visited = new boolean[N+1];
		visited[1] = true;
		dfs(1, 0);
		
		
		//maxNode에서 각 노드로 가는 길
		visited = new boolean[N+1];
		visited[maxNode] = true;
		dfs(maxNode, 0);
	}

	private static void dfs(int i, int cost) {
		if (maxCost < cost) {
			maxCost = cost;
			maxNode = i;
		}
		
		for (Node next : adjList[i]) {
			if (!visited[next.node]) {
				visited[next.node] = true;
				dfs(next.node, cost + next.dist);
			}
		}
	}

}
