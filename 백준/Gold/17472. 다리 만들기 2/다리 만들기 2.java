import java.io.*;
import java.util.*;

public class Main {
	static int N, M, total, sum, answer = Integer.MAX_VALUE;
	static int[][] map;
	static int[] numberOfIsland = new int[10];
	static int[][] adjMatrix;
	static List<int[]> graph = new ArrayList<int[]> ();
	static boolean connect = true;

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
		
//		print();
		total = number - 1;

		adjMatrix = new int[12][12];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				adjMatrix[i][j] = 100;
			}
		}

		//다리 만들기. 인접행렬로 가중치 표현
		makeBridge();
		
//		for (int i = 0; i < adjMatrix.length; i++) {
//			System.out.println(Arrays.toString(adjMatrix[i]));
//		}

		boolean[] visited = new boolean[12];
		
		//인접 행렬을 {from, to, weight} 형식의 리스트로 만들기
		for (int i = 0; i < adjMatrix.length; i++ ) {
			for (int j = 0; j < adjMatrix[i].length; j++) {
				if (adjMatrix[i][j] != 100) {
					graph.add(new int[] {i, j, adjMatrix[i][j]});
				}
			}
		}
		
		Collections.sort(graph, new Comparator<int[]> () {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		
//		for (int i =0 ; i < graph.size(); i++) {
//			System.out.println(Arrays.toString(graph.get(i)));
//		}
		
		//최단 경로 찾기
		findRoute();
		
		if (sum == 0 || connect == false)
			sum = -1;
		
		bw.write(sum + "\n");
		bw.flush();
		bw.close();
		br.close();

	}

	private static void findRoute() {
		int[] parent = new int[total-1];
		boolean[] v = new boolean[total-1];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < graph.size(); i++) {
//			int check = 0;
//			for (int j = 0; j < v.length; j++) {
//				if (v[j]) check++;
//			}
//			
//			if (check == v.length) break;
			
			int[] cur = graph.get(i);
			
			int from = cur[0]-2;
			int to = cur[1]-2;
			int weight = cur[2];
			
//			System.out.println("from : " + from + " to : " + to + " parent[from] : " + find(from, parent) + " parent[to] " +  find(to, parent) );
//			System.out.println(Arrays.toString(v));
			
			if (find(from, parent) == find(to, parent)) continue;
			else {
				parent = union(from, to, parent);
				sum += weight;
				v[from] = true;
				v[to] = true;
			}	
//			System.out.println(Arrays.toString(v));
//			System.out.println(Arrays.toString(parent));
//			System.out.println(sum);
		}
		
//		System.out.println(Arrays.toString(parent));
		
		// 모든 경로 연결 후 parent 배열 값이 다른게 있으면 그래프 연결이 안되었기 때문에 실패
		for (int j = 1; j < parent.length; j++) {
			if (find(parent[j-1], parent) != find(parent[j], parent)) {
				connect = false;
				break;
			}
		}
	}

	private static int[] union(int from, int to, int[] parent) {
		int x = find(from, parent);
		int y = find(to, parent);
		
		if (x < y) parent[y] = x;
		else parent[x] = y;
		
		return parent;
	}

	private static int find(int from, int[] parent) {
		if (parent[from] == from) return from;
		else {
			 parent[from] = find(parent[from], parent);
			return parent[from];
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
						else if (adjMatrix[k][curStart] != 100) {
							continue;
						}
						else {
							int min = funcForBridge(i, j, curStart, k);
							adjMatrix[curStart][k] = Math.min(min, adjMatrix[curStart][k]);	
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

//		System.out.println("curStart :  " + curStart + " target : " + target);
		// 가로, 세로 확인
		for (int i = 0; i < 4; i++) {
			int count = 0;
			for (int m = 1; m <= 10; m++) {
				int nx = x + dx[i] * m;
				int ny = y + dy[i] * m;
				
				
	
				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
//					System.out.println("nx : " + nx + " ny : " + ny + " map[nx][ny] : " + map[nx][ny] + " target : " + target + " count : " + count + " min : " + min );
					
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
//		System.out.println("x : " + x + " y : " + y + " min : " + min);
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

	public static void print() {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}

}
