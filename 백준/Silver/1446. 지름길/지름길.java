import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node> {
		int e, d;
		
		Node(int e, int d) {
			this.e = e;
			this.d = d;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.d - o.d;
		}
	}
	
	static int N, D;
	static List<Node>[] list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[D+1];
		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			if (end > D) continue;
			
			list[start].add(new Node(end, d));
		}
		
		int[] mem = new int[D+1];
		Arrays.fill(mem, Integer.MAX_VALUE);
		mem[0] = 0;
		
		for (int i = 0; i <= D; i++) {
			if (i != 0) mem[i] =  Math.min(mem[i], mem[i-1] + 1);
			
			
			for (Node next : list[i]) {
				if (mem[next.e] > next.d + mem[i]) {
					mem[next.e] = next.d + mem[i];
				}
			}
		}
		
		bw.write(mem[D] + "\n");
		br.close();
		bw.close();
		
	}

}
