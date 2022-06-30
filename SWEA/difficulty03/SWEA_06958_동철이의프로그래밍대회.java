/*
 * SWEA 06958. 동철이의 프로그래밍 대회
 * BufferedReader / StringTokenizer를 적용하여 Input을 받음
 * StringBuilder를 적용하여 결과 출력
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_06958_동철이의프로그래밍대회 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty03/input_06958.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 참가자 수
			int M = Integer.parseInt(st.nextToken()); // 문제 수
			int winners = 0;
			int maxScore = 0;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int scoreSum = 0;
				
				for(int j = 0; j < M; j++) {
					scoreSum += Integer.parseInt(st.nextToken());
				}
				
				if(maxScore == scoreSum) winners++;
				else if(maxScore < scoreSum) {
					maxScore = scoreSum;
					winners = 1;
				}
			}
			
			sb.append(String.format("#%d %d %d\n", test_case, winners, maxScore));
			//System.out.printf("#%d %d %d\n", test_case, winners, ansMax);
		}
		System.out.println(sb.toString());
		in.close();
	}
}