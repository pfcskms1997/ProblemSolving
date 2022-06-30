/*
 * SWEA 07964. 부먹왕국의 차원 관문
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_07964_부먹왕국의차원관문 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty03/input_07964.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 도시의 수
			int D = Integer.parseInt(st.nextToken()); // 이동 제한 거리
			int[] city = new int[N];
			int cnt = 0;
			int result = 0;
			
			// 1차원 배열에 각 도시의 차원관문 정보 입력
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				city[i] = Integer.parseInt(st.nextToken());
			}

			// 0번 위치의 도시에는 차원 관문이 존재한다고 문제에 명시되어있기 때문
			if(city[0] == 0) {
				result++;
				city[0] = 1;
			}
			
			for(int i = 0; i < N; i++) {
				if(city[i] == 0) cnt++;
				else cnt = 0;
				
				if(cnt == D) {
					result++;
					cnt = 0;
				}
			}
			
			System.out.printf("#%d %d\n", test_case, result);
		}
		br.close();
	}
}
