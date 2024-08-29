import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, personNum, min;
	static int[][] maps;
	static List<Person> personList;
	static Stair[] stairList;
	
	static class Person implements Comparable<Person> {
		int x, y, dist, downEnd;
		
		Person(int x, int y, int dist, int downEnd) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.downEnd = downEnd;
		}

		@Override
		public int compareTo(Person o) {
			return this.dist == o.dist ? this.downEnd - o.downEnd : this.dist - o.dist;
		}
	}
	
	static class Stair {
		int x, y, len;
		
		Stair(int x, int y, int len) {
			this.x = x;
			this.y = y;
			this.len = len;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine().split(" ")[0]);
		
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().split(" ")[0]);
			
			maps = new int[N][N];
			stairList = new Stair[2];
			personList = new ArrayList<>();
			
			personNum = 0;
			min = Integer.MAX_VALUE;
			
			int sIdx = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					maps[i][j] = Integer.parseInt(st.nextToken());
					
					if (maps[i][j] >= 2) {
						stairList[sIdx++] = new Stair(i, j, maps[i][j]);
					} 
					
					else if (maps[i][j] == 1) {
						personNum++;
						personList.add(new Person(i, j, Integer.MAX_VALUE, 0));
					}
				}
			}
			
			run(0, new boolean[personNum]);
			
			bw.write("#" + tc + " " + min + "\n");
		}
		
		
		
		bw.flush();
		bw.close();
		br.close();
	}

	private static void run(int k, boolean[] sel) {
		if (k == sel.length) {
			Queue<Person> stair1 = new PriorityQueue<Person>();
			Queue<Person> stair2 = new PriorityQueue<Person>();
			
			for (int i = 0; i < k; i++) {
				//true면 stair 1
				if (sel[i]) {
					Stair s = stairList[0];
					Person p = personList.get(i);
					
					int distCal = Math.abs(p.x - s.x) + Math.abs(p.y - s.y);
					
					stair1.add(new Person(p.x, p.y, distCal, 0));
				} else {
					Stair s = stairList[1];
					Person p = personList.get(i);
					
					int distCal = Math.abs(p.x - s.x) + Math.abs(p.y - s.y);
				
					stair2.add(new Person(p.x, p.y, distCal, 0));
				}
			}
			
			move(stair1, stair2);

			return;
		}
		
		sel[k] = true;
		run(k+1, sel);
		sel[k] = false;
		run(k+1, sel);
	}
	
	private static void move(Queue<Person> stair1, Queue<Person> stair2) {
		//내려가는 사람 리스트
		Queue<Person> stair1Down = new PriorityQueue<Person>();
		Queue<Person> stair2Down = new PriorityQueue<Person>();
		
		// 가장 먼저 입구에 도착한 사람 시간부터 시작
		
		int time = 0;
		if (stair1.size() != 0 && stair2.size() != 0) {
			time = stair1.peek().dist <= stair2.peek().dist ? stair1.peek().dist : stair2.peek().dist;
		} else if (stair1.size() != 0) {
			time = stair1.peek().dist;
		} else {
			time = stair2.peek().dist;
		}
		
		boolean start = true;
		
		while (true) {
			//계단 1
			//다 내려갔는지 체크
			for (int i =0 ; i < stair1Down.size(); i++) {
				if (stair1Down.peek().downEnd == time) {
					stair1Down.poll();
					i--;
				}
			}
			
			//입구 도착한 사람 있으면 그 사람을 계단 내려가는 리스트에 추가
			for (int i = 0; i < stair1.size(); i++) {
				//도착했고, 계단 내 인원수가 len보다 작으면
				if (stair1.peek().dist == time && stair1Down.size() < 3) {
					Person p = stair1.poll();
					stair1Down.add(new Person(p.x, p.y, p.dist, time + stairList[0].len + 1));
					i--;
				} 
				//대기 중이다가 사람 빠져서 들어갈 수 있으면
				else if (stair1.peek().dist < time && stair1Down.size() < 3) {
					
					Person p = stair1.poll();
					stair1Down.add(new Person(p.x, p.y, p.dist, time + stairList[0].len));
					i--;
				}
			}
			
			//계단 2
			//다 내려갔는지 체크
			for (int i =0 ; i < stair2Down.size(); i++) {
				if (stair2Down.peek().downEnd == time) {
					stair2Down.poll();
					i--;
				}
			}
			
			//입구 도착한 사람 있으면 그 사람을 계단 내려가는 리스트에 추가
			
			for (int i = 0; i < stair2.size(); i++) {
				//도착했고, 계단 내 인원수가 len보다 작으면

				if (stair2.peek().dist == time && stair2Down.size() < 3) {
					Person p = stair2.poll();
					stair2Down.add(new Person(p.x, p.y, p.dist, time + stairList[1].len + 1));
					i--;
				} 
				
				//대기 중이다가 사람 빠져서 들어갈 수 있으면
				else if (stair2.peek().dist < time && stair2Down.size() < 3) {
					Person p = stair2.poll();
					stair2Down.add(new Person(p.x, p.y, p.dist, time + stairList[1].len));
					i--;
				}
			}
			
			time++;

			if (start) start = false;
			else if (!start && stair1.size() == 0 && stair2.size() == 0 && stair1Down.size() == 0 && stair2Down.size() == 0) {
				min = Math.min(min, time-1);
				break;
			}
			
		}
	}

}
