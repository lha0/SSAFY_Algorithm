import java.util.*;

class Solution {
    public List<Integer> solution(String[] genres, int[] plays) {
        HashMap<String, Integer> genrePlay = new HashMap<>();
        
        HashMap<String, PriorityQueue<int[]>> playDetail = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            String curGenre = genres[i];
            int curPlay = plays[i];
            
            if (genrePlay.containsKey(curGenre)) genrePlay.put(curGenre, genrePlay.get(curGenre) + curPlay);
            else genrePlay.put(curGenre, curPlay);
            
            if (playDetail.containsKey(curGenre)) {
                PriorityQueue<int[]> pq = playDetail.get(curGenre);
                pq.offer(new int[] {i, curPlay});
            } else {
              PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
                  @Override
                  public int compare(int[] o1, int[] o2) {
                      return o2[1] - o1[1];
                  }
              });    
                pq.offer(new int[] {i, curPlay});
                playDetail.put(curGenre, pq);
            }
        }
        
        List<String> sortedGenres = new ArrayList<>(genrePlay.keySet());
        sortedGenres.sort((a, b) -> genrePlay.get(b) - genrePlay.get(a));
        
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < sortedGenres.size(); i++) {
            String cur = sortedGenres.get(i);
            
            PriorityQueue<int[]> pq = playDetail.get(cur);
            
            int cnt = 2;
            while(cnt > 0 && !pq.isEmpty()) {
                int[] curPQ = pq.poll();
                answer.add(curPQ[0]);
                cnt--;
            }
        }
        
        return answer;
    }
}