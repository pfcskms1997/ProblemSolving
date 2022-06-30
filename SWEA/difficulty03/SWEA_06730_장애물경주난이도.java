/*
 * SWEA 06730. 장애물 경주 난이도
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_06730_장애물경주난이도 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty03/input_06730.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[] obstacles = new int[N];
			int upDifficulty = 0;
			int downDifficulty = 0;
			
			// 장애물 블록 입력
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				obstacles[i] = Integer.parseInt(st.nextToken());
			}
			
			// 장애물 코스 난이도 계산
			for(int i = 0; i < N - 1; i++) {
				 int interval = obstacles[i] - obstacles[i + 1];
				 
				 // 낮은 곳에서 높은 곳을 빼면 음수가 되므로 이는 올라가기 난이도를 의미
				 if(interval < 0) {
					 if(upDifficulty < Math.abs(interval)) {
						 upDifficulty = Math.max(upDifficulty, Math.abs(interval));
					 }
				 }
				 // 그 외의 경우에는 다음 장애물의 높이가 같은 경우이거나, 낮은 경우(내려오기 난이도)임
				 else {
					 downDifficulty = Math.max(downDifficulty, interval);
				 }
			}
			System.out.printf("#%d %d %d\n", test_case, upDifficulty, downDifficulty);
		}
		br.close();
	}
}
