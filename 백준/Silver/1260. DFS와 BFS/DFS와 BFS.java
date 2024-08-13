import java.io.*;
import java.util.*;

public class Main {

	static int N, M, V;
	static List<Integer>[] list;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer sti = new StringTokenizer(br.readLine());
			int one = Integer.parseInt(sti.nextToken());
			int two = Integer.parseInt(sti.nextToken());
			
			list[one].add(two);
			list[two].add(one);
			
			Collections.sort(list[one]);
			Collections.sort(list[two]);
		}
		
		visited = new boolean[N+1];
		dfs(V);
		System.out.println();
		visited = new boolean[N+1];
		bfs(V);
		
		bw.write("\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void dfs(int start) {
		visited[start] = true;
		System.out.print(start + " ");
		
		for (int i : list[start]) {
			if (!visited[i]) {
				dfs(i);
			}
		}
	}
	
	public static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add(start);
		visited[start] = true;
		
		while (queue.size() != 0) {
			int cur = queue.poll();
			System.out.print(cur + " ");
			
			for (int x : list[cur]) {
				if (!visited[x]) {
					queue.add(x);
					visited[x] = true;
				}
			}
		}
		
	}

}
