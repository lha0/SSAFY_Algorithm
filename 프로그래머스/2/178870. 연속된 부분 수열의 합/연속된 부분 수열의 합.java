class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        
        int start = 0;
        int end = 0;
        int sum = sequence[start];
        
        int minLen = Integer.MAX_VALUE;
        
        while(end < sequence.length && start < sequence.length) {
            if (sum == k) {
                int len = end - start + 1;
                if (len < minLen) {
                    minLen = len;
                    answer[0] = start;
                    answer[1] = end;
                }
            }
            
            if (sum < k) {
                end++;
                if (end < sequence.length) sum += sequence[end];
            } else {
                if (start < sequence.length) sum -= sequence[start];  
                start++; 
            } 
            
        }
        
        return answer;
    }
}