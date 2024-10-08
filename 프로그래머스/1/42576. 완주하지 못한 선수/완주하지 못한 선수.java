import java.util.*;

class Solution {
    static HashMap<String, Integer> maps;
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        maps = new HashMap<>();
        for (int i = 0; i < completion.length; i++) {
            if (maps.containsKey(completion[i])) maps.put(completion[i], maps.get(completion[i]) + 1 );
            else maps.put(completion[i], 1);
        }
        
        for (int i = 0; i < participant.length; i++) {
            if (maps.containsKey(participant[i])) {
                maps.put(participant[i], maps.get(participant[i]) - 1 );
                
                if (maps.get(participant[i]) < 0) {
                    answer = participant[i];
                    break;
                }
            }
            else {
                answer = participant[i];
                break;
            }
        }
        
        return answer;
    }
}