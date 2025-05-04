import java.util.*;

class Solution {
    public static class Server implements Comparable<Server> {
        int startTime, endTime;
        boolean isRunning;
        
        Server(int startTime, int endTime, boolean isRunning) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.isRunning = isRunning;
        }
        
        @Override
        public int compareTo(Server o) {
            return this.endTime - o.endTime;
        }
    }
    
    static Queue<Server> runningQueue;
    static Queue<Server> endQueue;
    static int runningServerNum;
    static int M, K, answer;
    
    public int solution(int[] players, int m, int k) {

        runningQueue = new PriorityQueue<>();
        endQueue = new PriorityQueue<>();
        M = m;
        K = k;
        
        for (int time = 0; time < players.length; time++) {
            // System.out.println("### time " + time);
            //끝난 서버 체크
            checkEndServer(time);
            
            runningServerNum = runningQueue.size();
            
            // System.out.println("--- Cnt people");
            
            //인원 체크, 추가 서버 확인
            int users = players[time];
            int divServerNum = users / m;
            int needServerNum = divServerNum - runningServerNum;
            
//             System.out.println("runningServerNum " + runningServerNum);
            
//             System.out.println("users " + users + " divServerNum " + divServerNum + " needServerNum " + needServerNum);

            addServer(time, needServerNum);
            
            // System.out.println("answer " + answer);
            
        }
        return answer;
    }
    
    public static void addServer(int time, int num) {
        // System.out.println("--- Add Server " + num);
        while (num > 0) {
            Server newServer = new Server(time, time + K, true);   
            runningQueue.add(newServer);
            num--;
            answer++;
        }
        
    }
    
    public static void checkEndServer(int time){
        // System.out.println("--- Check End Server ");
        // System.out.println("runninQueue size " + runningQueue.size());
        
        while(!runningQueue.isEmpty()) {
            Server cur = runningQueue.peek();
            
            //넘었으면 삭제
            if (cur.endTime <= time) {
                runningQueue.poll();
            } else {
                break;
            }
        }
        // System.out.println("after remove " + runningQueue.size());
        
    }
}