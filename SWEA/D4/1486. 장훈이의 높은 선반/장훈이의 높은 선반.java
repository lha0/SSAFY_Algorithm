import java.io.*;
import java.util.*;
 
public class Solution {
    static int N, B;
    static int[] height;
    static int min = Integer.MAX_VALUE;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        int T = Integer.parseInt(br.readLine().split(" ")[0]);
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
             min = Integer.MAX_VALUE;
            StringTokenizer sts = new StringTokenizer(br.readLine());
            height = new int[N];
            for (int i = 0; i < N; i++) {
                height[i] = Integer.parseInt(sts.nextToken());
            }
 
            boolean[] sel = new boolean[N];
            comb(0, sel);
             
            int answer = min-B;
 
            bw.write("#" + tc + " " + answer + "\n");
 
        }
 
        bw.flush();
        bw.close();
        br.close();
 
    }
 
    private static void comb(int idx, boolean[] sel) {
        if (idx == sel.length) {
            cal(sel);
            return;
        }
        sel[idx] = true;
        comb(idx + 1, sel);
        sel[idx] = false;
        comb(idx + 1, sel);
    }
 
    private static void cal(boolean[] sel) {
        int sum = 0;
        for (int i = 0; i < sel.length; i++) {
            if (sel[i])
                sum += height[i];
        }
 
        if (sum >= B)
            min = Math.min(sum, min);
    }
 
}