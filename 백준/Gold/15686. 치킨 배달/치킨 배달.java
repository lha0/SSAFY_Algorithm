import java.io.*;
import java.util.*;

public class Main {
	static int N, M, storeNum, answer;
	static List<Home> homeList;
	static List<Store> storeList;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	static class Home {
		int x, y;
		
		Home(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Store {
		int x, y;
		
		Store (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		storeNum = 0;
		homeList = new ArrayList<>();
		storeList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			 st = new StringTokenizer(br.readLine());
			 for (int j = 0; j < N; j++) {
				 int n = Integer.parseInt(st.nextToken());
				 
				 //집
				 if (n == 1) {
					 homeList.add(new Home(i,  j));
				 } else if (n == 2) {
					 storeList.add(new Store(i, j));
					 storeNum++;
				 }
			 }
		}
		
		answer = Integer.MAX_VALUE;
		
		//storeNum 중에 M개 고르기
		comb(0, new boolean[storeNum]);
		
		bw.write(answer + "\n");
		br.close();
		bw.close();
	}

	private static void comb(int idx, boolean[] sel) {
		if (idx == sel.length) {
			int cnt = 0;
			ArrayList<Store> selected = new ArrayList<>();
			for (int i = 0; i < sel.length; i++) {
				if (sel[i]) {
					selected.add(storeList.get(i));
					cnt++;
				}
			}
			
			//최대 수익 내야하니까 M이 아니면 그냥 다 리턴
			if (cnt != M) return;
			
			int cost = 0;
			for (int i = 0; i < homeList.size(); i++) {
				Home cur = homeList.get(i);
				
				//거리 구하는 식으로 그냥 집과 가게 사이 거리 다 구하면 됨
				// 굳이 bfs 안써도 된다,,
				cost += calDist(cur, selected);
//				cost += bfs(cur, selectedMap);
			}
			
			answer = Math.min(cost, answer);
			return;
		}
		
		sel[idx] = true;
		comb(idx+1, sel);
		sel[idx] = false;
		comb(idx+1, sel);
	}

	private static int calDist(Home cur, ArrayList<Store> selected) {
		int totalCost = Integer.MAX_VALUE;
		for (int i = 0; i < selected.size(); i++) {
			Store curStore = selected.get(i);
			
			int dist = Math.abs(cur.x - curStore.x) + Math.abs(cur.y - curStore.y);
			
			totalCost = Math.min(dist,  totalCost);
		}
		return totalCost;
		
	}
}
