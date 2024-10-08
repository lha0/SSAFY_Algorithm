import java.util.*;

class Solution {
    static Map<Integer, Integer> maps;
    
    public int solution(int[] nums) {
        int answer = 0;
        int monNum = 0;
        int selNum = nums.length / 2;
        maps = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            
            if (maps.containsKey(cur)) continue;
            else {
                monNum++;
                maps.put(cur, 1);
            }
        }
        
        if (selNum > monNum) answer = monNum;
        else answer = selNum;
        return answer;
    }
}