import java.util.Scanner;

public class Main {
	static int N;
	static boolean[][] v;
	static int result = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		v = new boolean[N][N];

		dfs(0);
		System.out.println(result);

	}

	private static void dfs(int cnt) {
		if (cnt == N) {
			result++;
			return;
		}
		for( int i = 0 ; i < N ; i++) {
			if(check(cnt , i)) {
				v[cnt][i]=true;
				dfs(cnt+1);
			}
			v[cnt][i]= false;
			
		}
	}

	private static boolean check(int y, int x) {

		// 위 , 대각선 왼쪽 대각선 오른쪽만 검사하면 됨

		// 위 검사 y 값이 계속 -된다
		for (int i = y; i >= 0; i--) {
			if (v[i][x]) {
				return false;
			}
		}

		// 대각선 위 검사
		for (int i = y, j = x; i >= 0 && j >= 0; i--, j--) {
			if (v[i][j]) {
				return false;
			}
		}

		for (int i = y, j = x; i >= 0 && j < N; i--, j++) {
			if (v[i][j]) {
				return false;
			}
		}

		return true;
	}

}
