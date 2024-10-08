import java.io.*;
import java.util.*;

public class Main {
	static int N, answer;
	static ArrayList<int[]> xList, yList, zList;
	static List<Planet> edgeList;
	static int[] parent;
	
	static class Planet {
		int from, to, cost;
		
		Planet(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		
		// x, y, z 따로 관리
		//cost 계산이 x, y, z 중 최솟값이므로 따로 리스트를 만들고 각 좌표끼리 거리 계산
		xList = new ArrayList<>();
		yList = new ArrayList<>();
		zList = new ArrayList<>();	
		edgeList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			xList.add(new int[] {i, x});
			yList.add(new int[] {i, y});
			zList.add(new int[] {i, z});
		}

		//오름차순 정렬
		Collections.sort(xList, (p1, p2) -> p1[1] - p2[1]);
		Collections.sort(yList, (p1, p2) -> p1[1] - p2[1]);
		Collections.sort(zList, (p1, p2) -> p1[1] - p2[1]);
		
		//인접해있는 행성들끼리 cost 계산
		calCost(xList);
		calCost(yList);
		calCost(zList);
		
		//간선리스트 정렬
		Collections.sort(edgeList, (p1, p2) -> p1.cost - p2.cost);
		
		//크루스칼
		make();
		
		run();
		
		bw.write(answer + "\n");
		bw.close();
		br.close();

	}

	private static void run() {
		int edgeNum = 0;
		for (int i = 0; i < edgeList.size(); i++) {
			Planet cur = edgeList.get(i);
			
			if (union(cur.from, cur.to)) {
				answer += cur.cost;
				edgeNum++;
				if (edgeNum == N-1) break;
			}
		}
	}

	private static boolean union(int from, int to) {
		int parentA = find(from);
		int parentB = find(to);
		
		if (parentA == parentB) return false;
		
		if (parentA < parentB) {
			parent[parentB] = parentA;
		} else {
			parent[parentA] = parentB;
		}
		
		return true;
	}

	private static int find(int a) {
		if (a == parent[a]) return a;
		else return parent[a] = find(parent[a]); 
	}

	private static void make() {
		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
	}

	private static void calCost(ArrayList<int[]> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			int[] first = list.get(i);
			int[] second = list.get(i+1);
			
			int cost = Math.abs(first[1] - second[1]);
			
			edgeList.add(new Planet(first[0], second[0], cost));
		}
	}

}
