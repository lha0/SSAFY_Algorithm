
import java.io.*;
import java.util.*;

public class Main {
	static int N, M, total, sum, answer = Integer.MAX_VALUE;
	static int[][] map;
	static int[] numberOfIsland = new int[100];
	static ArrayList<Vertex>[] graph;
	
	static class Vertex implements Comparable<Vertex> {
		int e, w;
		
		Vertex(int e, int w) {
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.w - o.w;
		}
		
		
	}

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			StringTokenizer sts = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(sts.nextToken());
			}
		}

		// 섬을 하나 찾고(bfs), 그 섬에서 다른 섬 (1)까지의 거리를 bfs로 측정
		// 각 섬을 고유번호로 표기
		int number = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					map[i][j] = number;
					bfs(i, j, number);
					number++;
				}
			}
		}
		
		total = number - 1;
		
		 graph = new ArrayList[50];
		 for (int i = 0; i < 50; i++) {
			 graph[i] = new ArrayList<>();
		 }
		 
		//다리 만들기. 인접행렬로 가중치 표현
		makeBridge();

		prim();
		
		bw.write(sum + "\n");
		bw.flush();
		bw.close();
		br.close();

	}

	

	private static void prim() {
		boolean[] visited = new boolean[total-1];
		Queue<Vertex> queue = new PriorityQueue();
		queue.offer(new Vertex(0, 0));
		
		while (!queue.isEmpty()) {
			Vertex cur = queue.poll();
			
			if (visited[cur.e]) continue;
			
			visited[cur.e] = true;
			sum += cur.w;
			
			for (Vertex next : graph[cur.e]) {
				if (!visited[next.e]) {
					queue.offer(new Vertex(next.e, next.w));
				}
			}
		}
		
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				sum = -1;
				break;
			}
		}
	}


	private static void makeBridge() {
		// 섬 내 하나의 좌표에서 오른쪽, 왼쪽 / 아래, 위로만 움직였을 때 그 거리가 2이상이고 새로운 1을 만나면 저장
		// 1에서 2, 3, 4로 연결한 다리 길이의 최솟값을 저장
		// 2에서 1, 3, 4도 저장 => 2차원 인접행렬
		// 섬 표시를 2부터 시작했기 때문에 +2

		int curStart = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == curStart) {

					// 각 점에서 목표 섬으로
					for (int k = 2; k <= total; k++) {
						// 자기 자신은 제외
						if (curStart == k)
							continue;
						else {
							int min = funcForBridge(i, j, curStart, k);
							
							if (min != Integer.MAX_VALUE) graph[curStart-2].add(new Vertex(k-2, min));
						}
					}

					numberOfIsland[curStart]--;

					// 본인 섬을 모두 다 체크했다면 다음 섬으로
					if (numberOfIsland[curStart] == 0) {
						curStart++;
						i = 0;
						j = 0;
					}
				}
			}
		}

	}

	private static int funcForBridge(int x, int y, int curStart, int target) {
		int min = Integer.MAX_VALUE;

		// 가로, 세로 확인
		for (int i = 0; i < 4; i++) {
			int count = 0;
			for (int m = 1; m <= 10; m++) {
				int nx = x + dx[i] * m;
				int ny = y + dy[i] * m;
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					// 내 땅이 바로 옆에 있으면 방향 바꿔서;
					if (map[nx][ny] == curStart)
						break;
					
					// 다음 위치가 k (목표땅)이고, count가 2보다 크면 최소값 저장
					if (map[nx][ny] == target && count >= 2) {
						min = Math.min(min, count);
						break;
					}
					
					// 다음 위치가 목표땅이고, count가 2보다 작으면 break;
					if (map[nx][ny] == target && count < 2) {
						break;
					}

					// target이 아닌 다른 섬이면 break;
					if (map[nx][ny] != 0 && map[nx][ny] != target)
						break;
					
					// 0 (바다)이면 계속 감
					if (map[nx][ny] == 0) {
						count++;
					}
				}
			}

		}
		return min;

	}

	private static void bfs(int x, int y, int number) {
		Queue<int[]> needVisit = new ArrayDeque<int[]>();
		needVisit.offer(new int[] { x, y });
		int count = 1;

		while (!needVisit.isEmpty()) {
			int[] cur = needVisit.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];

				if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 1) {
					map[nx][ny] = number;
					needVisit.offer(new int[] { nx, ny });
					count++;
				}
			}

		}
		numberOfIsland[number] = count;
	}
}
