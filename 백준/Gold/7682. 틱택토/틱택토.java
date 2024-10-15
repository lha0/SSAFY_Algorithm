import java.util.*;
import java.io.*;

public class Main {
	static int Xcnt, Ocnt;
	static String[][] maps;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String input = br.readLine().split(" ")[0];
		while (!input.equals("end")) {
			String[] arr = input.split("");
			Xcnt = 0;
			Ocnt = 0;
			maps = new String[3][3];
			for (int i = 0; i < arr.length; i++) {
				String cur = arr[i];

				maps[i / 3][i % 3] = cur;

				if (cur.equals("X"))
					Xcnt++;
				else if (cur.equals("O"))
					Ocnt++;
			}

			 if (Xcnt == Ocnt) {
				if (Xcnt <= 2 && Ocnt <= 2) {
					bw.write("invalid\n");
				}
				else {
					if ((row("X") && row("O")) || (col("X") && col("O")) || (diagonal("X") && diagonal("O"))) {
						bw.write("invalid\n");
					}
					// 가로 판별
					else if (row("O") || col("O") || diagonal("O")) {
						bw.write("valid\n");
					}
					
					else {
						bw.write("invalid\n");
						
					}
				}	
			} 
			
			else if (Xcnt == Ocnt + 1) {
				if (row("O") || col("O") || diagonal("O")) {
					bw.write("invalid\n");
				} 
				
				else if (Xcnt >= 3 && (row("X") || col("X") || diagonal("X"))) {
					bw.write("valid\n");
				}
				
				else if (Xcnt + Ocnt == 9) {
					bw.write("valid\n");
				}

				else bw.write("invalid\n");
			} 
			
			else {
				bw.write("invalid\n");
			}

			input = br.readLine().split(" ")[0];
		}

		br.close();
		bw.close();
	}

	private static boolean diagonal(String ST) {
		if (maps[0][0].equals(maps[1][1]) && maps[1][1].equals(maps[2][2]) && maps[2][2].equals(ST)) {
			return true;
		} else if (maps[0][2].equals(maps[1][1]) && maps[1][1].equals(maps[2][0]) && maps[2][0].equals(ST)) {
			return true;
		}
		return false;
	}

	private static boolean col(String ST) {
		for (int c = 0; c < maps.length; c++) {
			if (maps[0][c].equals(maps[1][c]) && maps[1][c].equals(maps[2][c]) && maps[2][c].equals(ST)) {
				return true;
			}
		}
		return false;
	}

	private static boolean row(String ST) {
		for (int r = 0; r < maps.length; r++) {
			if (maps[r][0].equals(maps[r][1]) && maps[r][1].equals(maps[r][2]) && maps[r][2].equals(ST)) {
				return true;
			}
		}

		return false;
	}

}
