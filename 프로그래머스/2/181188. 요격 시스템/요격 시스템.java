import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 1;
        
        Arrays.sort(targets, (a, b) -> {
             if (a[1] == b[1]) {
                    return a[0] - b[0];
                }
                return a[1] - b[1];
        });
        
        int s = targets[0][0];
        int e = targets[0][1];
        
        for (int i = 0; i < targets.length-1; i++) {
            int[] cur = targets[i];
            int[] next = targets[i+1];
            
            int cs = cur[0];
            int ce = cur[1];
            
            int ns = next[0];
            int ne = next[1];
            
            if (ns >= e) {
                s = ns;
                e = ne;
                answer++;
            } 
        }
        
        return answer;
    }
}