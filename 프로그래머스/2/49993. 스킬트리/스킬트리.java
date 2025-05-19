class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for (int i = 0; i < skill_trees.length; i++) {
            String cur = skill_trees[i];
            
            String[] curStringArr = cur.split("");
            int counting = 0;
            
            for (int j = 0; j < cur.length(); j++) {
                String curAlphabet = curStringArr[j];
                
                if (skill.contains(curAlphabet)) {
                    if (skill.indexOf(curAlphabet) != counting) {
                        break;
                    } else {
                        counting++;
                    }
                }
                
                if (counting == skill.length()-1 || j == cur.length()-1) {
                    answer++;
                    break;
                }
            }            
            
        }
        
        
        return answer;
    }
}