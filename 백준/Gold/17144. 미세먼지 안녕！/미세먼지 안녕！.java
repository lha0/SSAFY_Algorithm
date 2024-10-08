import java.io.*;
import java.util.*;

public class Main {
	static int R, C, T, acUX, acUY, acDX, acDY;
	static List<Dust> dustList;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int[][] windDx = {{-1, 0, 1, 0}, {1, 0, -1, 0}};
	static int[][] windDy = {{0, 1, 0, -1}, {0, 1, 0, -1}};
	static int[][] originMap;
	
	static class Dust {
		int x, y, amount;
		
		Dust(int x, int y, int amount) {
			this.x = x;
			this.y = y;
			this.amount = amount;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		originMap = new int[R][C];
		dustList = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				int next = Integer.parseInt(st.nextToken());
				
				originMap[i][j] = next;
				
				if (next == -1 && acUX == 0) {
					acUX = i;
					acUY = j;
				} else if (next == -1 && acUX != 0) {
					acDX = i;
					acDY = j;
				} else if (next > 0) {
					dustList.add(new Dust(i, j, next));
				}
			}
		}
		
		run();
		
		//최종 미세먼지 양
		int answer = 0;
		for (int i = 0; i < originMap.length; i++) {
			for (int j = 0; j < originMap[i].length; j++) {
				if (originMap[i][j] > 0) {
					answer += originMap[i][j];
				}
			}
		}
		
		bw.write(answer + "\n");
		
		br.close();
		bw.close();
	}

	private static void run() {
		int time = 0;
		while(time < T) {
			time++;
			//확산
			expand();
			
			//작동
			//1 : 반시계, 2: 시계
//			System.out.println("반시계");
			wind(acUX, acUY, 0);
//			System.out.println("시계");
			wind(acDX, acDY, 1);
			
			//dustList 초기화
			dustList = new ArrayList<Dust>();
			for (int i = 0; i < originMap.length; i++) {
				for (int j = 0; j < originMap[i].length; j++) {
					if (originMap[i][j] > 0) {
						dustList.add(new Dust(i, j, originMap[i][j]));
					}
				}
			}
			
		}
	}

	private static void wind(int sx, int sy, int dir) {
		int[] wDx = windDx[dir];
		int[] wDy = windDy[dir];
		
		int wIdx = 0;
		int initX = sx;
		int initY = sy;
		sx = sx + wDx[wIdx];
		sy = sy + wDy[wIdx];
		int nx = sx + wDx[wIdx];
		int ny = sy + wDy[wIdx];
		
		while(true) {
//			System.out.println("sx " + sx + " " + sy);
//			System.out.println("nx " + nx + " " + ny);
			
			if (originMap[nx][ny] == -1) {
				originMap[sx][sy] = 0;
				break;
			}
			
			originMap[sx][sy] = originMap[nx][ny];
			
			sx = nx;
			sy = ny;
			
			if ((nx == 0 && ny == 0) || (nx == R-1 && ny == 0)) {
				wIdx++;
			} else if ((nx == 0 && ny == C-1) || (nx == R-1 && ny == C-1)) {
				wIdx++;
			} else if (nx == initX && ny == C-1) {
				wIdx++;
			}
			
			nx = sx + wDx[wIdx];
			ny = sy + wDy[wIdx];
			
		}
		
//		for (int i = 0; i < originMap.length; i++) {
//			System.out.println(Arrays.toString(originMap[i]));
//		}
//		System.out.println();
		
	}

	private static void expand() {
		int[][] newMap = new int[R][C];
		
		for (int i = 0; i < dustList.size(); i++) {
			Dust curDust = dustList.get(i);
			
			List<int[]> blankCoord = new ArrayList<int[]>();
			int findBlank = blank(curDust.x, curDust.y,  blankCoord);
			
			int expandAmount = (int) Math.floor(curDust.amount / 5);
			int leftAmount = curDust.amount - (expandAmount * findBlank);
			
			newMap[curDust.x][curDust.y] += leftAmount;
			for (int j = 0; j < blankCoord.size(); j++) {
				int[] next = blankCoord.get(j);
				newMap[next[0]][next[1]] += expandAmount;
			}
			
		}
		
		//originmap update
		for (int i = 0; i < originMap.length; i++) {
			for (int j = 0; j < originMap[i].length; j++) {
				if (originMap[i][j] == -1) continue;
				originMap[i][j] = newMap[i][j];
			}
		}
		
//		System.out.println("확산");
//		for (int i = 0; i < newMap.length; i++) {
//			System.out.println(Arrays.toString(originMap[i]));
//		}
//		System.out.println();
	}

	private static int blank(int x, int y, List<int[]> coord) {
		int num = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx >= 0 && nx < R && ny >= 0 && ny < C && originMap[nx][ny] != -1) {
				coord.add(new int[] {nx, ny});
				num++;
			}
		}
		return num;
	}

}
