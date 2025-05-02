import java.util.*;

class Solution {
    static int minA = Integer.MAX_VALUE;
    static int[][] staticInfo;
    static int N, M;
    
    static Set<String> sets;
    
    public int solution(int[][] info, int n, int m) {
        int answer = 0;
        
        staticInfo = info;
        N = n;
        M = m;
        
        sets = new HashSet<>();
        
        //첫번째 물건을 A가 훔칠 때
        steal(1, 0, info[0][0], 0);
            
        //첫번째 물건을 B가 훔칠 때
        steal(1, 0, 0, info[0][1]);
                
        if (minA == Integer.MAX_VALUE) answer = -1;
        else answer = minA;
        
        return answer;
    }
    
    public static void steal(int curThingIndex, int stealPersonNum, int Asum, int Bsum) {
        if (Asum >= N) return;
        if (Bsum >= M) return;
        
        if (curThingIndex == staticInfo.length) {
            minA = Math.min(minA, Asum);
            return;
        }
        
        String key = Integer.toString(curThingIndex)+ " " + Integer.toString(Asum) + " " + Integer.toString(Bsum);
        if (sets.contains(key)) return;
        else sets.add(key);
        
        //다음 물건을 A가 훔칠 때
        int newA = Asum + staticInfo[curThingIndex][0];
        if (newA < N && newA < minA) steal(curThingIndex+1, 0, Asum + staticInfo[curThingIndex][0], Bsum);
        
        //B가 훔칠 때
        if (Bsum + staticInfo[curThingIndex][1] < M) steal(curThingIndex+1, 0, Asum , Bsum+  staticInfo[curThingIndex][1]);
        
        return;
        
    }
}