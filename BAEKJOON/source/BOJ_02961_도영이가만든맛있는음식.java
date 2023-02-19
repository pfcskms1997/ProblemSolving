/*
 * BAEKJOON 02961. 도영이가 만든 맛있는 음식
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_02961_도영이가만든맛있는음식 {
	
	static int Ans;
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_02961.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// TestCase input 여부에 따라 변경
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 재료의 개수
			int[][] taste = new int[N][];
			Ans = 987654321;
			
			// 각 재료의 신맛과 쓴맛을 2차원 배열에 입력
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int sour = Integer.parseInt(st.nextToken()); // 신 맛(곱)
				int bitter = Integer.parseInt(st.nextToken()); // 쓴 맛(합)
				taste[i] = new int[] {sour, bitter};
			}
			
			// 문제 풀이(신 맛과 쓴 맛의 차이를 작게 해야 함)
			powerSet(taste, 0, 0);
			
			// 결과 출력
			System.out.printf("#%d %d\n", test_case, Ans);
		}
		br.close();
	}
	
	
	public static void powerSet(int[][] taste, int cnt, int flag) {
		// Base Part
		if(cnt == taste.length) {
			// 부분집합 결과 확인
//			for(int i = 0; i < N; i++) {
//				System.out.print(((flag & 1 << i) != 0 ? i + " " : "X "));
//			}
//			System.out.println();
			
			// 원소가 하나라도 선택이 된 경우(flag가 0이면 선택된 원소가 없음)
			if(flag != 0) {
				int sScore = 1;
				int bScore = 0;
				
				for(int i = 0; i < taste.length; i++) {
					if((flag & 1 << i) != 0) {
						sScore *= taste[i][0];
						bScore += taste[i][1];
					}
				}
				
				Ans = Math.min(Ans, Math.abs(sScore - bScore));
			}
			return;
		}
		
		// Inductive Part
		powerSet(taste, cnt+1, flag | 1 << cnt);
		powerSet(taste, cnt+1, flag);
	}
}
