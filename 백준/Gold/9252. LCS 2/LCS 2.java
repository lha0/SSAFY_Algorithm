import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] str1 = br.readLine().split("");
        String[] str2 = br.readLine().split("");
        int[][] match = new int[str1.length+1][str2.length+1];
        
        for (int i = 1; i < str1.length+1; i++) {
            for (int j = 1; j < str2.length+1; j++) {
                if (str1[i-1].equals(str2[j-1])) {
                    match[i][j] = match[i-1][j-1] + 1;
                } else {
                    match[i][j] = Math.max(match[i-1][j], match[i][j-1]);
                }
            }
        }
        
        int cnt = match[str1.length][str2.length];
        
        String str = "";
        
        int i = str1.length;
        int j = str2.length;
        while (i >= 1 && j >= 1) {
        	if (match[i][j] == match[i-1][j]) {
        		i--;
        		continue;
        	}
        	
        	else if (match[i][j] == match[i][j-1]) {
        		j--;
        		continue;
        	}
        	
        	else if (match[i][j] != match[i-1][j] && match[i][j] != match[i][j-1]) {
        		i--;
        		j--;
        		str += str1[i];
        		continue;
        	}
        }
        
        String reverse = "";
        for (int idx = str.length()-1; idx >= 0; idx--) {
        	reverse += str.charAt(idx);
        }
      
       
        bw.write(cnt + "\n");
        bw.write(reverse + "\n");
        bw.close();
        br.close();
    }

}
