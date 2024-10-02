class Solution {
    static String[] strArr;
    static int idx, answer;
    static String[] alpha = {"A", "E", "I", "O", "U"};
    static boolean find = false;
    
    public int solution(String word) {
        answer = 0;
        
        strArr = word.split("");
        
        run(word, "");
        
        return answer;
    }
    
    private static void run(String findWord, String curWord) {
        if (curWord.equals(findWord)) {
            find = true;
            answer = idx;
            return;
        }
        
        if (curWord.length() == 5) {
            return;
        }
        
        
        for (int i = 0; i < alpha.length; i++) {
             idx++;
            run(findWord, curWord + alpha[i]);
            if (find) return;
        }
        
    }
}