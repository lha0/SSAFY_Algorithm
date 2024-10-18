import java.util.*;

class Solution {
    static List<int[]> numberList;
    static int N;
    static double answer;
    
    public List<Double> solution(int k, int[][] ranges) {
        //수 만들기
        numberList = new ArrayList<>();
        numberList.add(new int[] {0, k});
        makeNum(k);
        
        List<Double> answerList = new ArrayList<>();
        
        //range마다 너비 구하기
        for (int i = 0; i < ranges.length; i++) {
            int[] cur = ranges[i];
            
            answer = 0;
            cal(cur);
            
            answerList.add(answer);
        }
        
       
        return answerList;
    }
    
    public static void cal(int[] coord) {
        int a = coord[0];
        int b = coord[1];
        int last = N + b;
        
        // System.out.println(a + " " + last);
        
        if (a > last) {
            answer = -1;
            return;
        }
        
        for (int x = a; x < last && x+1 < numberList.size(); x++) {
            int[] first = numberList.get(x);
            int[] second = numberList.get(x+1);
            
            double area = (first[1] + second[1]) * 0.5;
            
            // System.out.println("area " + area);
            answer += area;
        }
        
    }
    
    public static void makeNum(int k) {
        int idx = 1;
        while(k != 1) {
            if (k % 2 == 0) {
                k /= 2;
            }
            
            else {
                k = k * 3 + 1;
            }
            
            numberList.add(new int[] {idx, k});
            idx++;
        }
        
        N = --idx;
        
    }
}