import java.util.*;

class Solution {
    static ArrayList<Integer>[] adjList;
    static int N;
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        N = n;
        adjList = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < wires.length; i++) {
            int[] cur = wires[i];
            int a = cur[0];
            int b = cur[1];
            
            adjList[a].add(b);
            adjList[b].add(a);
        }
        
        for (int i = 0; i < wires.length; i++) {
            int[] cur = wires[i];
            int a = cur[0];
            int b = cur[1];
            
            boolean[] visited = new boolean[N+1];
            
            int cntA = bfs(a, b, visited);
            int cntB = bfs(b, a, visited);
            
            answer = Math.min(Math.abs(cntA - cntB), answer);
        }
        
        return answer;
    }
    
    public static int bfs(int a, int other, boolean[] visited) {
        Queue<Integer> queue = new ArrayDeque<>();
        
        queue.offer(a);
        visited[a] = true;
        
        int cnt = 0;
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            
            cnt++;
            
            for (int next : adjList[cur]) {
                if (!visited[next] && next != other) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
        
        return cnt;
    }
    
}