/*
 * BAEKJOON 01149. RGB거리
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_01149_RGB거리 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_01149.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 집의 개수
			int[] cost = new int[3]; // 도색 비용의 누적 값을 저장할 배열

			StringTokenizer st = null;
			for(int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int costR = Integer.parseInt(st.nextToken());
				int costG = Integer.parseInt(st.nextToken());
				int costB = Integer.parseInt(st.nextToken());
				
				// 이전에 누적된 값이 없으므로 첫 번째 집의 도색 비용을 저장
				if(i == 1) {
					cost[0] = costR;
					cost[1] = costG;
					cost[2] = costB;
					continue;
				}
				
				// cost배열을 바로 변경하면 다음 색깔에 대한 연산에 영향을 미치므로 임시 배열 선언 및 복사
				int[] temp = new int[3];
				System.arraycopy(cost, 0, temp, 0, cost.length);
				
				temp[0] = costR + Math.min(cost[1], cost[2]);
				temp[1] = costG + Math.min(cost[0], cost[2]);
				temp[2] = costB + Math.min(cost[0], cost[1]);
				
				// temp의 값을 다시 cost로 복사
				System.arraycopy(temp, 0, cost, 0, cost.length);
			}
			
			// 최소값을 가져오기 위해 정렬
			Arrays.sort(cost);
			
			// 결과 출력
			bw.write(String.format("#%d %d\n", test_case, cost[0]));
			bw.flush();
		}
		
		// 자원 반환
		br.close();
		bw.close();
	}
}
