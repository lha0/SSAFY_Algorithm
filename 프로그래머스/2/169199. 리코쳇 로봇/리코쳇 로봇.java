import java.util.*;

class Solution {
    static String[][] boardMap;
    static int ROW, COL, startX, startY, endX, endY, answer;
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(String[] board) {
        ROW = board.length;
        COL = board[0].length();
        
        boardMap = new String[ROW][COL];
        
        for (int i = 0; i < ROW; i++) {
            String[] curBoard = board[i].split("");
            for (int j = 0; j < curBoard.length; j++) {
                String cur = curBoard[j];
                
                boardMap[i][j] = cur;
                
                if (cur.equals("R")) {
                    startX = i;
                    startY = j;
                } else if (cur.equals("G")) {
                    endX = i;
                    endY = j;
                }
            }
        }
        
        answer = Integer.MAX_VALUE;
        
        bfs();
        
        if (answer == Integer.MAX_VALUE) answer = -1;
        
        return answer;
    }
    
    public static void bfs() {
        Queue<int[]> deque = new ArrayDeque<>();
        
        boolean[][] visited = new boolean[ROW][COL];
        
        deque.offer(new int[] {startX, startY, 1});
        visited[startX][startY] = true;
        
        while(!deque.isEmpty()) {
            int[] cur = deque.poll();
            
            // System.out.println("curX " + cur[0] + " curY " + cur[1] + " cnt " + cur[2]);
            
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];  
                
                // System.out.println("!! i changed !!");
                
                while(true) {
                    
                    // System.out.println("## nx " + nx + " ny " + ny);
                    if (nx >= 0 && nx < ROW && ny >= 0 && ny < COL) {
                        if (boardMap[nx][ny].equals("D")) {
                            nx = nx - dx[i];
                            ny = ny - dy[i];
                            break;
                        } else {
                            nx = nx + dx[i];
                            ny = ny + dy[i];
                        }
                    } else {
                        nx = nx - dx[i];
                        ny = ny - dy[i];
                        break;
                    }
                }
                
                if (nx >= 0 && nx < ROW && ny >= 0 && ny < COL
                   && !visited[nx][ny]) {
                    
                    // System.out.println(">> nx " + nx + " ny " + ny);
                    
                    if (boardMap[nx][ny].equals("G")) {
                        answer = Math.min(answer, cur[2]);
                    }
        
                    else {
                        visited[nx][ny] = true;
                        deque.offer(new int[] {nx, ny, cur[2] + 1});
                    }
                }
                
                
                
            }
        }
    }
    
}