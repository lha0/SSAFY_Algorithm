import java.util.*;

public class Main {
	static int[][] map;
	static int n;
	static int m;
	static boolean[][] check;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int result = 0;
	static boolean[][] fCheck;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];
		check = new boolean[n][m];
		fCheck = new boolean[n][m];
		// check 값으로 전체 크기를 검사한다/
		for (int i = 0; i < n; i++) {
			String input = sc.next();
			for (int j = 0; j < input.length(); j++) {
				map[i][j] = input.charAt(j) - '0';

			}
		}

		for (int i = 1; i < n - 1; i++) {
			for (int j = 1; j < m - 1; j++) {
				if (!check[i][j]) {
					if (bfs(i, j, map[i][j])) {
						check[i][j] = true;

					}
				}
			}
		}
		
		for(int i = 1 ;  i < n-1 ; i++) {
			for ( int j = 1 ; j < m-1 ; j++) {
				if(check[i][j] && !fCheck[i][j]) {
					findResult(i , j);
				}
			}
		}
		
		System.out.println(result);
		
		
		
	}

	private static void findResult(int r, int c) {
		//bfs를 돌리고 돌린 값들 중에서 벽의 최소값을 찾는다.
		// 최소 값을 통해서 list 들의 값을 더한다.
		int min = Integer.MAX_VALUE;
		Queue<int[]> q = new ArrayDeque();
		fCheck[r][c] = true;
		ArrayList<int[]> list = new ArrayList();
		list.add(new int[] {r,c});
		q.offer(new int[] {r,c});
		
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			
			for( int i = 0 ;i < 4 ; i++) {
				int nr = arr[0] + dr[i];
				int nc = arr[1] + dc[i];
				
				
				if(check[nr][nc]) {
					if(!fCheck[nr][nc]) {
						q.offer(new int[] {nr,nc});
						fCheck[nr][nc]=true;
						list.add(new int[] {nr , nc});
					}
				}else {
					min = Math.min(map[nr][nc], min);
				}
			}
			
		}
		for(int i = 0 ; i < list.size(); i++) {
			int rr = list.get(i)[0];
			int cc = list.get(i)[1];
			result+= min - map[rr][cc];
		}
		
	}

	private static boolean bfs(int r, int c, int k) {
		boolean[][] v = new boolean[n][m];
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { r, c });
		v[r][c] = true;
		ArrayList<int[]> list = new ArrayList();

		while (!q.isEmpty()) {
			int[] arr = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = arr[0] + dr[i];
				int nc = arr[1] + dc[i];

				if (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] <= k && !v[nr][nc]) {
					if (nr == 0 || nr == n - 1 || nc == 0 || nc == m - 1) {
						return false;
					} else {
						v[nr][nc] = true;
						q.offer(new int[] { nr, nc });
					}
				}
			}
		}
		return true;
	}

}
