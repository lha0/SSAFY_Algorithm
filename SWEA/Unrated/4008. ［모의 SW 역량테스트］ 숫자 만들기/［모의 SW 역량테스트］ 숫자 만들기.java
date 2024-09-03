
import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, max, min;
    static int[] num;
    static String[] op;
    static HashMap<String, Integer> cList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        T = Integer.parseInt(br.readLine().split(" ")[0]);
        
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().split(" ")[0]);
            
            //0 : + , 1 : -, 2 : *, 3 : /
            op = new String[N-1];
            num = new int[N];
            
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            cList = new HashMap<String, Integer>();
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = 0;
            for (int i = 0; i < 4; i++) {
                int count = Integer.parseInt(st.nextToken());
                
                while (count--> 0) {
                    if (i == 0) op[idx++] = "+";
                    else if (i == 1) op[idx++] = "-";
                    else if (i == 2) op[idx++] = "*";
                    else if (i == 3) op[idx++] = "/";
                }
                
            }
            
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }
            
            run(0, new String[N-1], new boolean[N-1]);
            
            bw.write("#" + tc + " " + (max - min) + "\n");
        }
        
        bw.close();
        br.close();
        
    }

    private static void run(int k, String[] sel, boolean[] visit) {
        
        if (k == sel.length) {
            String conc = String.join("", sel);
            
            if (cList.containsKey(conc)) return;
            else cList.put(conc, 1);
            
            cal(sel);
//            System.out.println(max + " " + min);
            return;
        }

        String before = "";
        for (int i = 0; i < N-1; i++) {
            if (before != op[i] && !visit[i]) {
                visit[i] = true;
                sel[k] = op[i];
                before = op[i];
                run(k+1, sel, visit);
                visit[i] = false;
            }
        }
    }

    private static void cal(String[] sel) {
        int result = num[0];
        for (int i =0 ; i < sel.length; i++) {
            switch(sel[i]) {
            case "+":
                result = result + num[i+1];
                break;
            case "-":
                result = result - num[i+1];
                break;
            case "*":
                result = result * num[i+1];
                break;
            case "/":
                result = result / num[i+1];
                break;
            }
        }
        
        min = Math.min(min, result);
        max = Math.max(max, result);
    }

}
