import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        
        while(!check(wallet, bill)) {
            int max = 0;
            int maxIdx = 0;
            if (bill[0] < bill[1]) {
                max = bill[1];
                maxIdx = 1;
            } else {
                max = bill[0];
                maxIdx = 0;
            }
            
            max = max / 2;
            bill[maxIdx] = max;
            answer++;
            
        }
        
        
        return answer;
    }
    
    public static boolean check(int[] wallet, int[] bill) {
        if (bill[0] <= wallet[0] && bill[1] <= wallet[1]) return true;
        else if (bill[0] <= wallet[1] && bill[1] <= wallet[0]) return true;
        else return false;
    }
}