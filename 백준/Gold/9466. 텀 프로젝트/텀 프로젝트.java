import java.util.*;
import java.io.*;

public class Main {
	static int T, n, count;
	static int[] students;
	static boolean[] visited, done;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());

			students = new int[n+1];
			st = new StringTokenizer(br.readLine());
			
			count = 0;
			visited = new boolean[n+1];
			done = new boolean[n+1];
			
			for (int i = 1; i <= n; i++) {
				students[i] = Integer.parseInt(st.nextToken());
				//자기자신
				if (i == students[i]) {
					visited[i] = true;
					done[i] = true;
					count++;
				}
			}
			
			for (int i = 1; i <= n; i++) {
				//아직 거쳐가지 않았다면
				if (!done[i]) {
					dfs(i);
				}
			}
			
			bw.write((n - count) + "\n");
			
		}
		
		br.close();
		bw.close();
		
	}
	
	public static void dfs(int n) {
		if (visited[n]) return;
		
		visited[n] = true;
		
		int next = students[n];
		
		if (!visited[next]) {
			dfs(next);
		}
		
		else {
			if (!done[next]) {
				count++;
				
				while(next != n) {
					count++;
					next = students[next];
				}
			}
		}
		
		done[n] = true;
		
	}
		

}
