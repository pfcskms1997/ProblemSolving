/*
 * BAEKJOON 03985. 롤 케이크
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_03985_롤케이크 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_03985.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int L = Integer.parseInt(br.readLine()); // 롤케이크의 길이
			int N = Integer.parseInt(br.readLine()); // 방청객의 수
			int expecter = 0;
			int expectVal = 0;
			int maxBeneficiary = 0;
			int realMaxVal = 0;
			int[] cake = new int[L];
			int[] pieces = new int[N];
			
			// rollcake 배열 초기화
			for(int i = 0; i < L; i++) {
				cake[i] = 0;
			}
			
			for(int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				
				// 가장 많은 조각을 받을 것으로 기대하고 있던 방청객의 번호 기록
				if(expectVal < end - start - 1) {
					expectVal = end - start - 1;
					expecter = i;
				}
				
				// 어떤 방청객이 몇 번 조각을 가져갈 수 있는지 기록
				for(int j = start - 1; j < end; j++) {
					if(cake[j] == 0) cake[j] = i;
					else continue;
				}
			}
			
			// 방청객별 총 케이크 조각 수 기록
			for(int l = 0; l < cake.length; l++) {
				if(cake[l] == 0) continue;
				else pieces[cake[l] - 1]++;
			}
			
			// 가장 많은 조각을 가져간 방청객을 기록
			for(int m = 0; m < pieces.length; m++) {
				if(realMaxVal < pieces[m]) {
					realMaxVal = pieces[m];
					maxBeneficiary = m + 1;
				}
			}
			System.out.printf("#%d\n%d\n%d\n", test_case, expecter, maxBeneficiary);
			// System.out.println(Arrays.toString(cake)); // 케이크 조각 출력
			// System.out.println(Arrays.toString(pieces));
		}
		br.close();
	}
}