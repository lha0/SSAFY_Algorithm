import java.io.*;
import java.util.*;

public class Main {
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            String[] inputs = br.readLine().split("");

            HashMap<String, ArrayList<Integer>> hm = new HashMap<>();
            for (int i = 0; i < inputs.length; i++) {
                String cur = inputs[i];

                if (hm.containsKey(cur) && !hm.get(cur).isEmpty()) {
                    hm.get(cur).add(i);
                }
                else {
                    ArrayList<Integer> newArr = new ArrayList<>();
                    newArr.add(i);
                    hm.put(cur, newArr);
                }
            }

            K = Integer.parseInt(br.readLine().split(" ")[0]);

            int globalMinNum = Integer.MAX_VALUE;
            int globalMaxNum = Integer.MIN_VALUE;

            for(String key : hm.keySet()) {
                ArrayList<Integer> value = hm.get(key);
                if (value.size() >= K) {
                    int findMinDiff = getMinDiff(value);
                    int findMaxDiff = getMaxDiff(value);

                    globalMinNum = Math.min(globalMinNum, findMinDiff);
                    globalMaxNum = Math.max(globalMaxNum, findMaxDiff);
                }
            }

            if (globalMaxNum == Integer.MIN_VALUE || globalMinNum == Integer.MAX_VALUE) {
                bw.write(-1 + "\n");
            } else {
                bw.write(globalMinNum + " " + globalMaxNum + "\n");
            }
        }
        br.close();
        bw.close();

    }

    public static int getMinDiff(ArrayList<Integer> indexes) {
        int minNum = Integer.MAX_VALUE;
        for (int i = 0; i < indexes.size() - K + 1; i++) {
            int diff = indexes.get(i+K-1) - indexes.get(i) + 1;
            minNum = Math.min(diff, minNum);
        }
        return minNum;
    }

    public static int getMaxDiff(ArrayList<Integer> indexes) {
        int maxNum = Integer.MIN_VALUE;
        for (int i = 0; i < indexes.size() - K + 1; i++) {
            int diff = indexes.get(i+K-1) - indexes.get(i) + 1;
            maxNum = Math.max(diff, maxNum);
        }
        return maxNum;
    }
}
