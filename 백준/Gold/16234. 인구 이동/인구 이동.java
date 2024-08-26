import java.io.*;
import java.util.*;

/*
bfs로 4방 탐색하며 인접한 칸과의 차가 L이상 R이하인지 확인
- 맞으면 큐에 삽입
- 따로 possible list에 포함 (중복 없어야 함)

possible list에 포함된 행열 번호끼리 합, 연합 수 구해서 업데이트

answer++
 * */

public class Main {
	static int N, L, R, answer;
	static int[][] land;

	static int[] dx = new int[] { 1, -1, 0, 0 };
	static int[] dy = new int[] { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		land = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer sts = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				land[i][j] = Integer.parseInt(sts.nextToken());

			}
		}

		run();

		bw.write(answer + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static void run() {
		
		while (true) {
			
			List<int[]> possible = new ArrayList<>();
			List<List<int[]>> totalPossible = new ArrayList<>();

			boolean[][] visited = new boolean[N][N];
			for (int i =0 ; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						possible = new ArrayList<>();
						
						bfs(i, j, visited, possible);
						
						if (possible.size() != 0)	totalPossible.add(possible);
					}
					
				}
			}
			
			if (totalPossible.size() == 0) return;
			
			for (int idx = 0; idx < totalPossible.size(); idx++) {
				List<int[]> curList = totalPossible.get(idx);
				
//				System.out.println(curList.size());
				
				if (curList.size() == 0) continue;
				
				int sum = 0;
				int newPeople = 0;
				
				for (int i = 0; i < curList.size(); i++) {
					int[] cur = curList.get(i);
					
					sum += land[cur[0]][cur[1]];
					
				}
				
				newPeople = sum / curList.size();
				
				for (int i = 0; i < curList.size(); i++) {
					int[] cur = curList.get(i);

					land[cur[0]][cur[1]] = newPeople;
				}
				
				
			}
			
			answer++;
			
//			for (int i =0 ; i < N; i++) {
//				System.out.println(Arrays.toString(land[i]));
//			}
//			System.out.println();
//			
//			
//			
//			
		}

		

	}


	private static void bfs(int x, int y, boolean[][] visited, List<int[]> possible) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] { x, y });

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]
						&& Math.abs(land[cur[0]][cur[1]] - land[nx][ny]) >= L
						&& Math.abs(land[cur[0]][cur[1]] - land[nx][ny]) <= R) {
					
					
					if (!visited[x][y] && cur[0] == x && cur[1] == y) {
						visited[x][y] = true;
						possible.add(new int[] { x, y });
					}

					
					queue.offer(new int[] { nx, ny });
					visited[nx][ny] = true;
					possible.add(new int[] { nx, ny });

				}
			}
		}
		
		
	}

}
