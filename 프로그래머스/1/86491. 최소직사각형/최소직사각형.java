class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        
        for (int i = 0; i < sizes.length; i++) {
            int[] cur = sizes[i];
            if (cur[0] < cur[1]) sizes[i] = new int[]{cur[1], cur[0]};
        }
        
        int maxw = 0;
        int maxh = 0;
        for (int i = 0; i < sizes.length; i++) {
            int[] cur = sizes[i];
            
            if (cur[0] > maxw) maxw = cur[0];
            if (cur[1] > maxh) maxh = cur[1];
        }
        
        answer = maxw * maxh;
        
        return answer;
        
    }
}