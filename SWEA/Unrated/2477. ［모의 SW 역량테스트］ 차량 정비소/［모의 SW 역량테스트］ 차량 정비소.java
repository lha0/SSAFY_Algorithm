import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	static class Customer {
		int receptionNum;
		int fixNum;
		Customer(int receptionNum, int fixNum) {
			this.receptionNum = receptionNum;
			this.fixNum = fixNum;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			
			int N = sc.nextInt();   // 접수 창구의 개수
			int M = sc.nextInt();   // 정비 창구의 개수
			int K = sc.nextInt();   // 차량 정비소를 방문한 고객의 수
			int A = sc.nextInt();   // 지갑을 두고 간 고객이 이용한 접수 창구번호
			int B = sc.nextInt();   // 지갑을 두고 간 고객이 이용한 정비 창구번호
			
			int[] receiveTime = new int[N+1];
			int[] receiveTimeCopied = new int[N+1];
			int[] fixTime = new int[M+1];
			int[] fixTimeCopied = new int[N+1];
			int[] visitTime = new int[K+1];
			Queue<Integer> receptionWaiting = new ArrayDeque<Integer>();
			Queue<Integer> fixWaiting = new ArrayDeque<Integer>();
			int[] reception = new int[N+1];
			int[] fix = new int[M+1];
			Customer[] customers = new Customer[K+1];
			int count = 0;
			int answer = 0;
			
			for (int i = 1; i <= N; i++) {
				receiveTime[i] = sc.nextInt();
			}
			for (int i = 1; i <= M; i++) {
				fixTime[i] = sc.nextInt();
			}
			for (int i = 1; i <= K; i++) {
				visitTime[i] = sc.nextInt();
			}
			
			int t = 0;
			
			// 시간을 기준으로 턴 돌기
			L: while(true) {
				// 이번 시간에 새로운 사람이 방문했다면 (여긴 잘됨)
				for (int i = 1; i <= K; i++) {
					if (visitTime[i] == t) {
						receptionWaiting.add(i);   // 접수 대기줄에 해당 고객을 넣음
					}					
				}
				
				// 접수창구에 빈 곳이 있고 접수 대기줄에 사람이 있다면 
				// 대기줄에서 맨 앞사람을 빼서 접수창구에 넣고
				// 해당 고객에게 접수창구 번호를 할당하고
				// 접수창구 처리 시간을 시작함
				for (int i = 1; i <= N; i++) {   // i: 접수창구 번호 / 정비창구 번호
					if (reception[i] == 0 && !receptionWaiting.isEmpty()) {
						int customerNum = receptionWaiting.poll();
						reception[i] = customerNum;
						customers[customerNum] = new Customer(i, 0);
						receiveTimeCopied[i] = receiveTime[i];   // 접수창구 처리 시작 
					}
				}
				
				// 정비창구에 빈 곳이 있고 정비 대기줄에 사람이 있다면
				// 대기줄에서 맨 앞사람을 빼서 정비창구에 넣고
				// 해당 고객에게 정비창구 번호를 할당하고
				// 정비창구 처리 시간을 시작함
				for (int i = 1; i <= M; i++) {
					if (fix[i] == 0 && !fixWaiting.isEmpty()) {
						int customerNum = fixWaiting.poll();
						fix[i] = customerNum;
						customers[customerNum].fixNum = i;
						fixTimeCopied[i] = fixTime[i];   // 정비창구 처리 시작 
					}
					
					if (count == K) {   // 모든 고객이 정비까지 끝났다면
						break L;
					}
				}
				
				for (int i = 1; i <= N; i++) {
					if (reception[i] != 0) {
						receiveTimeCopied[i]--;   // 접수창구 처리 중
						
						if (receiveTimeCopied[i] == 0) {   // 접수창구 처리가 끝났다면
							fixWaiting.add(reception[i]);   // 정비 대기줄에 해당 고객을 넣고
							reception[i] = 0;   // 접수창구를 비움
						}
					}
				}
				for (int i = 1; i <= M; i++) {
					if (fix[i] != 0) {
						fixTimeCopied[i]--;   // 정비창구 처리 중
						
						if (fixTimeCopied[i] == 0) {   // 정비창구 처리가 끝났다면
							count++;
							fix[i] = 0;   // 정비창구를 비움
						}
					}
				}
				
				t++;   // 시간 증가
			}
			
			for (int i = 1; i <= K; i++) {
				if (customers[i].receptionNum == A && customers[i].fixNum == B) {
					answer += i;
				}
			}
			if (answer == 0) answer = -1;
			
			System.out.println("#" + test_case + " " + answer);
		}
		
		sc.close();
	}
}
