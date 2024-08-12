import java.io.*;
import java.util.*;

/*
하나의 톱니바퀴는 8개 톱니 (N, S)를 가짐 => 4개 => [4][8][2]
K번 회전 = 방향 2개

맞닿은 톱니의 극이 다르면, 반대로
같으면 회전 안 함


 * */

public class Main {
	static int[][] gear = new int[4][8];
	static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int i = 0; i < 4; i++) {
			String[] input = br.readLine().split("");
			for (int j = 0; j < 8; j++) {
				gear[i][j] = Integer.parseInt(input[j]);
			}
		}

		K = Integer.parseInt(br.readLine().split(" ")[0]);
		for (int i = 0; i < K; i++) {
			String[] input2 = br.readLine().split(" ");
			int no = Integer.parseInt(input2[0]);
			int dir = Integer.parseInt(input2[1]);

			// 번호와 방향을 받아 돌리기 시작
			round(no - 1, dir);
			
			//print();
		}

		int answer = 0;
		int add = 1;
		for (int i = 0; i < 4; i++) {
			if (gear[i][0] == 1) {
				answer += add;
			}
			add *= 2;
		}

		bw.write(answer + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	public static void round(int no, int dir) {
		// 인접한 바퀴 찾기
		// 1이면 2의 6 / 2이면 1의 2와 3의 6 / 3이면 2의 2와 4의 6 / 4면 3의 2
		// 값이 다르면 큐에 넣기, 아니면 패스
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { no, dir });
		//System.out.println("원래 값 " + no + " " + dir);

		int opDir = 0;
		if (dir == 1) {
			opDir = -1;
		} else {
			opDir = 1;
		}
		
		int leftNum = no;
		int rightNum = no;
		int leftOpDir = opDir;
		int rightOpDir = opDir;
		
		
		while (leftNum != -1 && rightNum != 4) {
			boolean change = false;
			
			if (leftNum - 1 != -1 && gear[leftNum][6] != gear[leftNum - 1][2]) {
				//System.out.println("작은 기어 " +  gear[leftNum][6] + " " + gear[leftNum - 1][2] + " " + leftOpDir);
				queue.offer(new int[] { leftNum - 1, leftOpDir });
				leftNum = leftNum-1;
				leftOpDir = -leftOpDir;
				change = true;
			} else if (rightNum + 1 != 4 && gear[rightNum][2] != gear[rightNum + 1][6]) {
				//System.out.println("큰 기어 " +  gear[rightNum][2] + " " + gear[rightNum + 1][6] + " " + rightOpDir);
				queue.offer(new int[] { rightNum + 1, rightOpDir });
				rightNum = rightNum+1;
				rightOpDir = -rightOpDir;
				change = true;
			}
			
			if (!change) break; 
		}
		
		// 하나씩 빼면서 gear 회전 업데이트
		while (queue.size() != 0) {
			int[] cur = queue.poll();
			

			// 시계방향 오른쪽, gear[0] = gear[7]
			if (cur[1] == 1) {
				int temp = gear[cur[0]][7];

				for (int i = 7; i >= 1; i--) {
					gear[cur[0]][i] = gear[cur[0]][i-1];
					//System.out.println("###### i ####### " + i);
					//print();
				}

				gear[cur[0]][0] = temp;
			}
			// 반시계방향 왼쪽, gear[7] = gear[0]
			else {
				int temp = gear[cur[0]][0];

				for (int i = 1; i < 8; i++) {
					gear[cur[0]][i - 1] = gear[cur[0]][i];
				}

				gear[cur[0]][7] = temp;
			}
		}
	}
	
	public static void print() {
		for (int i = 0; i < 4; i++) {
			System.out.println(Arrays.toString(gear[i]));
		}
		System.out.println();
	}

}
