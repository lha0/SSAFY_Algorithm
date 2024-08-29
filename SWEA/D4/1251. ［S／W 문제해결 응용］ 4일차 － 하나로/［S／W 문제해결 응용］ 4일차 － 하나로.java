import java.io.*;
import java.util.*;

public class Solution {
	static int T, N;
	static double E;
	static double cost;
	static int[] parents;
	static List<Edge> edgeList;
	
	static class Edge implements Comparable<Edge> {
		int s, e;
		double w;
		
		Edge(int s, int e, double w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Double.compare(this.w, o.w);
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine().split(" ")[0]);

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().split(" ")[0]);
			
			int[] X = new int[N];
			int[] Y = new int[N];
		
			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					if (i == 0) {
						X[j] = Integer.parseInt(st.nextToken());
					} else {
						Y[j] = Integer.parseInt(st.nextToken());
					}
				}
			}
			
			E = Double.parseDouble(br.readLine().split(" ")[0]);
			
			edgeList = new ArrayList<Edge>();
			//간선리스트 만들기
			for (int i = 0; i < N-1; i++) {
				for (int j = i+1; j < N; j++) {
					int curx = X[i], cury = Y[i];
					int nx = X[j], ny = Y[j];
					
					double dist = Math.sqrt(Math.pow( Math.abs(curx-nx) , 2 ) + Math.pow( Math.abs(cury - ny) , 2 ) ) ;
					
					edgeList.add(new Edge(i, j, dist));	
				}
				
			}
			
			cost = 0;
			
			make();
			
			Collections.sort(edgeList);
			
			run();
			
			long result = Math.round(cost);
			
			bw.write("#" + tc + " " + result + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void run() {
		int num = 0;
		for (Edge e : edgeList) {
			if (union(e.s, e.e)) {
				cost += e.w * e.w * E;
				if (++num == N-1) break;
			}
			
		}
	}

	private static int find(int a) {
		if (parents[a] == a) return a;
		else return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int s, int e) {
		int sRoot = find(s);
		int eRoot = find(e);
		
		if (sRoot == eRoot) return false;
		
		if (sRoot < eRoot) parents[eRoot] = sRoot;
		else parents[sRoot] = eRoot;
		
		return true;
	}

	private static void make() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}

}
