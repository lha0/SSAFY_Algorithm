
import java.io.*;
import java.util.*;
public class Solution {
	static int T, D, W, K, min;
	static int[][] cell;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        T = Integer.parseInt(br.readLine().split(" ")[0]);
        
        for (int tc = 1; tc <= T; tc++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	D = Integer.parseInt(st.nextToken());
        	W = Integer.parseInt(st.nextToken());
        	K = Integer.parseInt(st.nextToken());
        	
        	cell = new int[D][W];
        	for (int i = 0; i < D; i++) {
        		st = new StringTokenizer(br.readLine());
        		for (int j = 0; j < W; j++) {
        			cell[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	min = Integer.MAX_VALUE;
        	
        	run(0, new boolean[D]);
        	
        	if (min == Integer.MAX_VALUE) min = 0;
        	
        	
        	bw.write("#" + tc + " " + min + "\n");
        }
        
        bw.close();
        br.close();
	}

	private static void run(int idx, boolean[] sel) {
		//약품 넣을 행 선택
		if (idx == sel.length) {
			
			//약품 넣을 행
			ArrayList<Integer> rows = new ArrayList<>();
			for (int i = 0; i < sel.length; i++) {
				if (sel[i]) rows.add(i);
			}
			
//			if (rows.size() == 2)
//			System.out.println("power set 종료 : "+rows);
			
			//cell 복사
			int[][] copyCell = new int[D][W];
			for (int i =0 ; i < D; i++) {
				for (int j = 0; j < W; j++) {
					copyCell[i][j] = cell[i][j];
				}
			}
			
			if (rows.size() > min) return;
			
			add(0, rows, copyCell);
			return;
		}
		
		//false로 시작, 작은 값부터 
		sel[idx] = false;
		run(idx+1, sel);
		sel[idx] = true;
		run(idx+1, sel);
	}

	private static void add(int idx, ArrayList<Integer> rows, int[][] copyCell) {
		if (idx == rows.size()) {
			
			if (test(copyCell)) {
				min = Math.min(min, rows.size());
//				System.out.println(min + " " + rows.size() + " " + rows);
//				print(copyCell);
			}
			
			return;
		}
		
		int rowIdx = rows.get(idx);
		// idx 행에 A넣기
		for (int j = 0; j < W; j++) {
			copyCell[rowIdx][j] = 0;
		}
		add(idx+1, rows, copyCell);
		
		// idx행에 B넣기
		for (int j = 0; j < W; j++) {
			copyCell[rowIdx][j] = 1;
		}
		add(idx+1, rows, copyCell);
		
	}

	private static boolean test(int[][] copyCell) {
//		System.out.println("test");
//		print(copyCell);
		//하나의 열마다 D개의 행들을 체크
		int pass = 0;
		for (int col = 0; col < W; col++) {
			int before = -1;
			int count = 0;
			for (int row = 0; row < D; row++) {
				int curType = copyCell[row][col];
				
				if (curType != before) {
					before = curType;
					count = 1;
				} else 
					count++;
				
				// K 넘으면 다음 열로
				if (count >= K) {
					pass++;
					break;
				}
			}
			
			//한 row가 끝났는데 count가 K를 못넘으면 그냥 fail return
			if (count < K) return false;
		} //for col end
		
		if (pass == W) return true;
		else return false;
	}

	private static void print(int[][] copyCell) {
		for (int i =0 ; i < D; i++) {
			System.out.println(Arrays.toString(copyCell[i]));
		}
		System.out.println();
	}

}
