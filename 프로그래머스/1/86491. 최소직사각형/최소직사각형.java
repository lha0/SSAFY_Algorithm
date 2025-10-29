import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int max_w = 0;
        int max_h = 0;
        
        for (int i = 0; i < sizes.length; i++) {
            int[] item = sizes[i];
            
            int width = item[0];
            int height = item[1];
            
            if (width < height) {
                int temp = height;
                height = width;
                width = temp;
            }
            
            max_w = Math.max(width, max_w);
            max_h = Math.max(height, max_h);
        }
        
        answer = max_w * max_h;
        
        return answer;
    }
}