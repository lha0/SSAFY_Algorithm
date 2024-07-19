import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] map;
	static ArrayList<Integer> origin;
	
	public static void main(String[] args) throws IOException {
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        
        N = Integer.parseInt(input[0]);
        
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
        	String[] inputMap = br.readLine().split(" ");
        	for (int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(inputMap[j]);
        	} 
        }
        
        origin = new ArrayList<>();
        for (int i = 0; i < N; i++) {
        	origin.add(i);
        }
        
        int[] sel = new int[N/2];
        ArrayList<int[]> combi = new ArrayList<>();
        comb(0, 0, sel, combi);
        
		/* 동작 */
		int result = run(combi, map, origin);
		
		/* 출력 */
		bw.write(result + "\n");
        bw.flush();
        br.close();
        bw.close();

	}

	public static void comb(int n, int r,  int[] sel, ArrayList<int[]> combi) {
		if (r == N/2) {
			int[] temp = Arrays.copyOfRange(sel, 0, N/2);
			combi.add(temp);
			return;
		}
		if (n == N) {
			return;
		}
		
		sel[r] = origin.get(n);
		comb(n+1, r+1, sel, combi);
		comb(n+1, r, sel, combi);
	}
	
	
	public static int run (ArrayList<int[]> combi, int[][] map, ArrayList<Integer> origin) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < combi.size() / 2; i++) {
			int[] temp = combi.get(i); //[0, 1, 2]
			
			ArrayList<Integer> other = (ArrayList<Integer>) origin.clone();
			for (int l = 0; l < temp.length; l++) {
				other.remove(Integer.valueOf(temp[l]));
			}
			
			int one_sum = 0;
			for (int x = 0; x < temp.length-1; x++) {
				for (int y = x+1; y <  temp.length; y++) {
					int t = temp[x];
					int l = temp[y];
					one_sum += map[t][l] + map[l][t];
				}
			}
			
			int two_sum = 0;
			for (int x = 0; x < other.size() - 1; x++) {
				for (int y = x+1; y <  other.size(); y++) {
					int t = other.get(x);
					int l = other.get(y);
					two_sum += map[t][l] + map[l][t];
				}
			}
			
			min = Math.min(min, Math.abs(one_sum - two_sum));
		}
		return min;
	}
}