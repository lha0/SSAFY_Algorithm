import java.io.*;
import java.util.*;

/*
 * 주어진 값으로 인접한 구역을 파악할 수 있는 인접리스트 만들고
 * 1. 그 리스트를 가지고 구역을 두개로 나누기 => 부분집합
 * 2. 나눈 구역들 연결되어 있는지 확인 => bfs
 * 3. 총합들 간의 차이 계산
 * 4. 차이와 min 값 비교 후 갱신
 * 5. 다시 1로 돌아가서 다르게 구역 나눠서 총합 구하고 차이계산, min 갱신
 * */

public class Main {
	static int N;
	static int min = Integer.MAX_VALUE;
	static int[] number;
	static ArrayList<Integer>[] adjList;
	static boolean[] sel;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);

		number = new int[N];
		String[] inputTwo = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			number[i] = Integer.parseInt(inputTwo[i]);
		}

		adjList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			for (int j = 0; j < M; j++) {
				int adjArea = Integer.parseInt(st.nextToken());
				adjList[i].add(adjArea - 1);
			}
		}

		// 부분집합부터 구하기
		sel = new boolean[N];
		comb(0);

		if (min == Integer.MAX_VALUE) min = -1;
		
		bw.write(min + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	public static void comb(int idx) {
		if (idx == N) {
			ArrayList<Integer> aList = new ArrayList<Integer>();
			ArrayList<Integer> bList = new ArrayList<Integer>();
			
			for (int i = 0; i < N; i++) {
				if (sel[i]) {
					aList.add(i);
				} else {
					bList.add(i);
				}
			}
			
			// 2구역으로 나뉘어졌는가
			if (aList.size() == 0 || bList.size() == 0) {
				return;
			}
			
			// 올바르게 연결되었는지 확인 후 인구 계산
			if (check(aList) && check(bList)) {
				cal(aList, bList);
			}
			return;

		}

		sel[idx] = true;
		comb(idx + 1);
		sel[idx] = false;
		comb(idx + 1);
	}

	public static boolean check(ArrayList<Integer> list) {
		// 인접 노드들 중에 list에 포함되어 있는 애들로만 newList 만들어서 비교
		Queue<Integer> newList = new ArrayDeque<Integer>();
		Queue<Integer> needVisit = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[N];

		needVisit.offer(list.get(0));
		visited[list.get(0)] = true;

		while (needVisit.size() != 0) {
			int cur = needVisit.poll();
			newList.add(cur);

			for (int x : adjList[cur]) {
				if (list.contains(x) && !visited[x]) {
					needVisit.add(x);
					visited[x] = true;
				}
			}
		}

		// 두 리스트의 크기가 같다면 올바른 리스트
		if (list.size() == newList.size()) {
			return true;
		}

		return false;
	}

	// 인구수 계산
	public static void cal(ArrayList<Integer> aList, ArrayList<Integer> bList) {
		int asum = 0;
		for (int i = 0; i < aList.size(); i++) {
			asum += number[aList.get(i)];
		}

		int bsum = 0;
		for (int i = 0; i < bList.size(); i++) {
			bsum += number[bList.get(i)];
		}
		
		min = Math.min(Math.abs(asum - bsum), min);
	}

}