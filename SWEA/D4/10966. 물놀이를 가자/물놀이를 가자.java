import java.io.*;
import java.util.*;
 
public class Solution {
	static int T, N, M, answer;
	static char[][] waterpark;
	static List<int[]> start;
	static int[][] visited;

	static int[] dx = new int[] {0, 0, 1, -1};
	static int[] dy = new int[] {1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	M = Integer.parseInt(st.nextToken());
        	
        	start = new ArrayList<>();
        	waterpark = new char[N][M];
        	for (int i = 0 ; i < N; i++) {
        		String[] inputs = br.readLine().split("");
        		for (int j = 0; j < M; j++) {
        			waterpark[i][j] =  inputs[j].charAt(0);
        		}
        	}
        	answer = 0;
        	
        	bfs();        	
        	
        	bw.write("#" + tc + " " + answer + "\n");
        }
        bw.close();
        br.close();
	}

	private static void bfs() {
		Queue<int[]> queue = new ArrayDeque<int[]>();
    	
    	for (int i =0 ; i < N; i++) {
    		for (int j = 0; j < M; j++) {
    			if (waterpark[i][j] == 'W') {
    				queue.offer(new int[] {i, j, 0});
    			}
    		}
    	}
    	
    	while (!queue.isEmpty()) {
    		int[] cur = queue.poll();
    		
    		for (int i = 0; i < 4; i++) {
    			int nx = cur[0] + dx[i];
    			int ny = cur[1] + dy[i];
    			
    			if (nx >= 0 && nx < N && ny >= 0 && ny < M  && waterpark[nx][ny] == 'L') {
    				queue.offer(new int[] {nx, ny, cur[2] + 1});
    				waterpark[nx][ny] = 'W';
    				answer += cur[2] + 1;
    			}
    		}
    	}
	}

}
