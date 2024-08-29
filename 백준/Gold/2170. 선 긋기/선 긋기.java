import java.io.*;
import java.util.*;

public class Main {
	static int N, dist;
	static Node[] nodeList;
	
	static class Node {
		int x, y;
		
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine().split(" ")[0]);
		
		nodeList = new Node[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			nodeList[i] = new Node(x, y);
		}
		
		Arrays.sort(nodeList, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.x - o2.x == 0 ? o1.y - o2.y : o1.x - o2.x;
			}
		});
		
		run();
		
		bw.write(dist + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static void run() {
		int minX = nodeList[0].x;
		int maxY = nodeList[0].y;
		
		int idx = 1;
		while (idx < N) {
			Node cur = nodeList[idx];
			
			// 앞이랑 겹치면
			if (cur.x <= maxY) {
				idx++;
				if (maxY <= cur.y) maxY = cur.y;
			} 
			
			else {
				//이전까지 거리 합
				dist += maxY - minX;
				minX = cur.x;
				maxY = cur.y;
			}
		}
		dist += maxY - minX;

	}

}
