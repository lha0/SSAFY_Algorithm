import java.io.*;
import java.util.*; 

public class Main {
	static int N, M, time;
	static int[][] maps;
	static List<int[]> cheese;
	
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		
		time = 0;
		cheese = new ArrayList<>();
		maps = new int[N][M];
		
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int next = Integer.parseInt(st.nextToken());
				
				maps[i][j] = next;
			}
		}
		
		run();
		
		bw.write(time + "\n");
		
		br.close();
		bw.close();

	}

	private static void run() {
		while(true) {
			
			//외부 영역 찾아서 표기
			L : for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (maps[i][j] == 0) {
						maps[i][j] = 2;
						bfs(0, 0);
						break L;
					}
				}
			}
			
//			System.out.println("제거 전");
//			print();
			
			cheese = new ArrayList<>();
			
			//외부 공기랑 맞닿은 부분이 2이상이면 제거항목에 추가
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (maps[i][j] == 1 && check(i, j)) {
						cheese.add(new int[] {i, j});
					}
				}
			}
			
			if (cheese.size() == 0) return;
			
			remove();
			
			//다시 0으로 원복
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (maps[i][j] == 2) maps[i][j] = 0;
					
				}
			}
			
//			System.out.println("제거 후");
//			print();
			
			time++;
			
		}
		
	}

	

	private static void remove() {
		for (int i = 0; i < cheese.size(); i++) {
			int[] cur = cheese.get(i);
			
			maps[cur[0]][cur[1]] = 0;
		}
	}

	private static boolean check(int i, int j) {
		int cnt = 0;
		for (int idx = 0; idx < 4; idx++) {
			int nx = i + dx[idx];
			int ny = j + dy[idx];
			
			if (nx >= 0 && nx < N && ny >= 0 && ny < M && maps[nx][ny] == 2) {
				cnt++;
			}
		}
		
		if (cnt >= 2) return true;
		return false;
	}

	private static void bfs(int i, int j) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		boolean[][] visited = new boolean[N][M];
		
		queue.offer(new int[] {i, j});
		visited[i][j] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for (int idx = 0; idx < 4; idx++) {
				int nx = cur[0] + dx[idx];
				int ny = cur[1] + dy[idx];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && maps[nx][ny] == 0) {
					maps[nx][ny] = 2;
					visited[nx][ny] = true;
					queue.offer(new int[] {nx, ny});
				}
 			}
		}
	}
	
	private static void print() {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(maps[i]));
		}
		System.out.println();
	}

	

}
