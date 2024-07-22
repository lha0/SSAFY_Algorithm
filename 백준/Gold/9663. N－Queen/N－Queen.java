import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int queen;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		
		int[] map = new int[N];
		boolean[] visited = new boolean[N];
		queen = N;
		
		run(0, map, visited, 0);
		
		bw.write(result + "\n");
		bw.flush();
		bw.close();
		br.close();

	}
	
	public static boolean check(int val, int row, int[] map, boolean[] visited) {
		//같은 열에 있는 경우
		if (visited[val]) {
			return false;
		}
		
		for (int i = 1; i < N; i++) {
			//대각선 왼쪽 위
			if (row-i >= 0 && map[row-i] == val-i) {
				return false;
			}
				
			//대각선 오른쪽 위
			if (row-i >= 0 && map[row-i] == val+i) {
				return false;
			}
		}
		return true;
	}
	
	public static void run(int row, int[] map, boolean[] visited, int count) {
	//	System.out.println(row + " " + Arrays.toString(map) + " " + Arrays.toString(visited) + " " + count);
		if (count == N) {
			result += 1;
			return;
		}
		
		for (int val = 0; val < N; val++){
			if (check(val, row, map, visited)) {
				map[row] = val;
				visited[val] = true;
				run(row+1, map, visited, count+1);
				map[row] = 0;
				visited[val] = false;
			} else {
				continue;
			}
			
		}
		
		return;
	}

}
