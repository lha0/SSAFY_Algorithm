import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, answer;
    static int[] candies, newcandies;
    static List<Integer>[] adjList;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        //사탕개수 및 인접리스트 초기화
        candies = new int[N+1];
        adjList = new ArrayList[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            candies[i] = Integer.parseInt(st.nextToken());
            adjList[i] = new ArrayList<Integer>();
        }
    
        //인접리스트 생성
        for (int k = 0; k < M; k++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            adjList[a].add(b);
            adjList[b].add(a);
        }
        
        //부모 리스트 초기화
        int[] parent = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parent[i]= -1; 
        }
        
        answer = 0;
        run(parent);
        
        bw.write(answer + "\n");
        
        bw.close();
        br.close();
    }

    private static void run(int[] parent) {
        //union
        for (int i = 1; i <= N; i++) {
            for (int elem : adjList[i]) {
                union(parent, elem, i);
            }
        }
        
        //[친구 수, 사탕 개수] 로 새로운 리스트 생성
        List<int[]> setArr = new ArrayList<>();
        boolean[] visited = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
        	if (parent[i] < 0) 
        		setArr.add(new int[] {Math.abs(parent[i]), candies[i]});
        }
        
        //최대 사탕 개수 구하기
        dp(setArr);
    }
    
    private static void dp(List<int[]> setArr) {
    	int size = setArr.size();
		int[][] maps = new int[size+1][K];
		
		for (int i = 1; i <= size; i++) {
			int[] cur = setArr.get(i-1);
			int friend = cur[0];
			int candy = cur[1];
			for (int k = 1; k < K; k++) {
				if (friend <= k) { //담을 수 있다
					maps[i][k] = Math.max(maps[i-1][k], maps[i-1][k-friend] + candy);
				} else {
					maps[i][k] = Math.max(maps[i-1][k], maps[i][k-1]);
				}
			}
		}
		
		answer = maps[size][K-1];
	}

	private static boolean union(int[] parent, int a, int b) {
        int parentA = find(parent, a);
        int parentB = find(parent, b);
        
        if (parentA == parentB) return false;
        
        // 작은 값으로 부모 통일
        // 부모 값에 candy 개수도 통일
        if (parentA < parentB) {
            parent[parentA] += parent[parentB];
            parent[parentB] = parentA;
            candies[parentA] += candies[parentB];
            candies[parentB] = 0;
        } else {
            parent[parentB] += parent[parentA];
            parent[parentA] = parentB;
            candies[parentB] += candies[parentA];
            candies[parentA] = 0;
        }
        
        return true;
    }

    private static int find(int[] parent, int a) {
        if (parent[a] < 0) return a;
        return parent[a] = find(parent, parent[a]);
    }

}
