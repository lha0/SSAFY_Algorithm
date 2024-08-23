import java.io.*;
import java.util.*;

public class Main {
	static int N, M, answer, rainbow;
	static Block[][] board;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static class Block {
		int x, y, color, group;

		Block(int x, int y, int color, int group) {
			this.x = x;
			this.y = y;
			this.color = color;
			this.group = group;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new Block[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer sts = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = new Block(i, j, Integer.parseInt(sts.nextToken()), -1);
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
			// bfs로 큰 그룹 찾기
			boolean[][] visited = new boolean[N][N];

			int markGroup = 1;

			List<int[]> groups = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && board[i][j].color >= 1) {
						rainbow = 0;
						int cnt = bfs(i, j, board[i][j].color, markGroup, visited);

						if (cnt <= 1) {
							markGroup++;
							continue;
						}

						// 개수 세리기
						groups.add(new int[] { markGroup, cnt, rainbow, i, j });
//                        
//                        for (int c = 0; c < N; c++) {
//                            System.out.println(Arrays.toString(visited[c]));
//                        }
//                        System.out.println();

						markGroup++;

					}
				}
			}

			// 그룹이 없으면 return
			if (groups.size() == 0)
				break;

			// 우선순위 정렬
			// 여긴 완벽해 !!!!
			Collections.sort(groups, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if (o1[1] == o2[1]) {
						if (o1[2] == o2[2]) {
							if (o1[3] == o2[3]) {
								return o2[4] - o1[4];
							}
							return o2[3] - o1[3];
						}
						return o2[2] - o1[2];
					}

					return o2[1] - o1[1];
				}

			});

//			for (int i = 0; i < groups.size(); i++) {
//				System.out.println(Arrays.toString(groups.get(i)));
//			}

			int maxGroup = groups.get(0)[0];
			int maxCount = groups.get(0)[1];

//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(board[i][j].group + "\t");
//				}
//				System.out.println();
//			}
//			System.out.println();

			// 있으면 제거
			remove(maxGroup);
			answer += maxCount * maxCount;

//			System.out.println(maxCount + " " + maxGroup);
//			print();

			// 중력 이동
			moveDown();

//			System.out.println("move Down");
//			print();

			// 90도 반시계 회전
			turn();

//			System.out.println("turn");
//			print();

			// 중력 이동
			moveDown();

//			System.out.println("move Down 2");
//			print();
		}

	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(board[i][j].color + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void turn() {
		// (i, j) -> (N-1-j, i)
		Block[][] newBoard = new Block[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				Block temp = board[r][c];
				newBoard[N - 1 - c][r] = new Block(temp.x, temp.y, temp.color, temp.group);
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				Block temp = newBoard[r][c];
				board[r][c] = new Block(temp.x, temp.y, temp.color, temp.group);
			}
		}

	}

	private static void moveDown() {
		// 하나의 열마다 계산
		for (int c = 0; c < N; c++) {
			for (int r = 0; r < N; r++) {
				if (board[r][c].color == -1)
					continue;

				if (board[r][c].color >= 0) {
					int sr = r;
					int len = 1;
					while (r + 1 < N) {
						if (board[r + 1][c].color >= 0) {
							len++;
							r++;
						} else
							break;
					}

					int findIdx = r + 1;
					int find = 0;
					while (findIdx < N) {
						if (board[findIdx][c].color == -2) {
							findIdx++;
							find++;
							if (findIdx == N) {
								findIdx--;
								break;
							}
						} else {
							findIdx--;
							break;
						}
					}

//                    System.out.println("c" + c+ " len " + len + " find " + find + " r " + r + " findIdx " + findIdx);

					if (find > 0) {
						if (len == 1) {
							int temp = board[findIdx][c].color;
							board[findIdx][c].color = board[r][c].color;
							board[r][c].color = temp;
						} else {
							while (len > 0) {
								board[findIdx][c].color = board[r][c].color;
								board[r][c].color = -2;
								findIdx--;
								r--;
								len--;

								if (r < 0)
									r = 0;
							}
//                            board[r][c].color = -2;
						}

					}
				}
			}
		}
	}

	private static void remove(int maxGroup) {
		Queue<Block> queue = new ArrayDeque<Block>();
		boolean[][] bfsV = new boolean[N][N];
		List<int[]> removeList = new ArrayList();
		
		L: for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j].group == maxGroup && board[i][j].color >= 1) {

					queue.offer(new Block(i, j, board[i][j].color, board[i][j].group));
					removeList.add(new int[] {i, j});
					bfsV[i][j] = true;

					while (!queue.isEmpty()) {
						Block cur = queue.poll();

						for (int d = 0; d < 4; d++) {
							int nx = cur.x + dx[d];
							int ny = cur.y + dy[d];

							if (nx >= 0 && nx < N && ny >= 0 && ny < N && !bfsV[nx][ny]
									&& (board[nx][ny].color == cur.color || board[nx][ny].color == 0)) {
								bfsV[nx][ny] = true;
								queue.offer(new Block(nx, ny, cur.color, maxGroup));
								removeList.add(new int[] {nx, ny});
							}
						}

					}
				}
			}
		}
		
		for (int idx = 0; idx < removeList.size(); idx++) {
			int rx = removeList.get(idx)[0];
			int ry = removeList.get(idx)[1];
			
			board[rx][ry].x = -1;
			board[rx][ry].y = -1;
			board[rx][ry].color = -2;
			board[rx][ry].group = 0;
		}

		
	}

	private static int bfs(int r, int c, int color, int markGroup, boolean[][] visited) {
        Queue<Block> queue = new ArrayDeque<Block>();
        boolean[][] bfsV = new boolean[N][N];
        queue.offer(new Block(r, c, color, markGroup));

        board[r][c].group = markGroup;
        bfsV[r][c] = true;
        visited[r][c] = true;
        int cnt = 1;

        while (!queue.isEmpty()) {
            Block cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !bfsV[nx][ny]
                        && (board[nx][ny].color == cur.color || board[nx][ny].color == 0)) {

                    bfsV[nx][ny] = true;
                    if (board[nx][ny].color == cur.color) visited[nx][ny] = true;
                    
                    board[nx][ny].group = markGroup;
                    cnt++;
                    queue.offer(new Block(nx, ny, cur.color, markGroup));

                    if (board[nx][ny].color == 0)
                        rainbow++;
                }
            }
        }

        return cnt;
    }

}

/*
 * input 5 3 0 0 0 0 1 -1 -1 0 -1 0 -1 -1 3 -1 -1 -1 -1 0 -1 -1 0 0 2 0 0
 * 
 * output 74
 */

