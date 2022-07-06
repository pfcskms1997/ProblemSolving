/*
 * BAEKJOON 01463. 1로 만들기
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_01463_1로만들기_Webex {

	static int[] memo;
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_01463.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			// 하향식
			// 하-1. 재귀(시간 초과)
//			int ans = recursive(N, 0);
			
			// 하-2. memo(풀이 불가)
//			memo = new int[N+1];
//			Arrays.fill(memo, -1);
//			int ans = recursive_memo(N, 0);
			
			// 상향식
			memo = new int[N+1];
			int ans = dp(N);
			
			// 결과 출력
			bw.write(String.format("#%d %d\n", test_case, ans));
			bw.flush();
		}
		
		// 자원 반환
		br.close();
		bw.close();
	}

	// 시간 초과
	public static int recursive(int n, int cnt) {
		if(n == 1) return cnt;
		
		int r1 = 987654321;
		int r2 = 987654321;
		int r3 = 987654321;
		
		if(n % 3 == 0) r3 = recursive(n/3, cnt+1);
		if(n % 2 == 0) r2 = recursive(n/2, cnt+1);
		r1 = recursive(n-1, cnt+1);
		
		return Math.min(r1,	Math.min(r2, r3));
	}

	// 오답 -> 이 문제는 하향식으로 풀 수 없음
	private static int recursive_memo(int n, int cnt) {
		if(n == 1) return cnt;
		
		if(memo[n] != -1) return memo[n];
			
		int r1 = 987654321;
		int r2 = 987654321;
		int r3 = 987654321;
		
		if(n % 3 == 0) r3 = recursive_memo(n/3, cnt+1);
		if(n % 2 == 0) r2 = recursive_memo(n/2, cnt+1);
		r1 = recursive_memo(n-1, cnt+1);

		return memo[n] = Math.min(r1, Math.min(r2, r3));
	}

	private static int dp(int N) {
		// f(n) = f(n-1)+1, f(n)=f(n/2)+1, f(n)=f(n/3)+1
		memo[0] = 0;
		memo[1] = 0;
		
		for(int i = 2; i <= N; i++) {
			memo[i] = memo[i-1] + 1;
			if(i % 2 == 0) memo[i] = Math.min(memo[i], memo[i/2] + 1);
			if(i % 3 == 0) memo[i] = Math.min(memo[i], memo[i/3] + 1);
		}
		
		return memo[N];
	}
}
