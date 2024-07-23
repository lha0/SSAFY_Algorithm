import java.util.*;
import java.io.*;

public class Solution {
	static int T, N, min;
	static ArrayList<Node> coordinate; 
	static Node home, work;
	
	static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
		T = Integer.parseInt(input[0]);
		
		for (int t = 1; t <= T; t++) {
			String[] inputN = br.readLine().split(" ");
			N = Integer.parseInt(inputN[0]);
			
			String[] inputS = br.readLine().split(" ");
			work = new Node(Integer.parseInt(inputS[0]), Integer.parseInt(inputS[1]));
			home = new Node(Integer.parseInt(inputS[2]), Integer.parseInt(inputS[3]));
			
			coordinate = new ArrayList<>(); 
			for (int i = 4; i < (N+2) * 2; i+=2) {
				coordinate.add(new Node(Integer.parseInt(inputS[i]), Integer.parseInt(inputS[i+1])));
			}
			
			boolean[] visited = new boolean[N];
			ArrayList<Node> sel = new ArrayList<>();
			min = Integer.MAX_VALUE;
			
			run(coordinate, sel, 0, visited);
			
			bw.write("#" + t + " " + min + '\n');
		}
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static void run(ArrayList<Node> coor, ArrayList<Node> sel, int k, boolean[] visited) {
		if (k == N) {
			int sum = dist(work, sel.get(0));
			for (int i = 0; i < sel.size()-1; i++) {
				sum += dist(sel.get(i), sel.get(i+1));
			}
			sum += dist(sel.get(N-1), home);
			min = Math.min(sum,  min);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (visited[i] != true) {
				visited[i] = true;
				sel.add(k, coor.get(i));
				run(coor, sel, k+1, visited);
				sel.remove(k);
				visited[i] = false;
			}
		}
		return ;
	}
	
	public static int dist(Node a, Node b) {
		int diffX = Math.abs(a.x - b.x);
		int diffY = Math.abs(a.y - b.y);
		return diffX + diffY;
	}
	

}
