/*
 * SWEA 08458. 원점으로 집합
 */

package difficulty04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_08458_원점으로집합_X {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty04/input_08458.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[][] coords = new int[N][];
			int bitFlag = 0;
			
			StringTokenizer st = null;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int distance = Math.abs(x) + Math.abs(y);
				
				coords[i] = new int[] {x, y};
				
				// 원점으로부터 입력 받은 좌표까지의 거리에 대한 홀짝 정보를 비트배열에 마스킹(0: 짝, 1: 홀)
				if(distance % 2 == 1) bitFlag |= 1 << i;
			}
			
			int ans = 0;
			if(bitFlag == 0 || bitFlag == (1 << N)-1) ans = 1;
			else ans = -1;
			
			bw.write(String.format("#%d %d\n", test_case, ans));
			bw.flush();
		}
		
		// 결과 출력 및 프로그램 종료
		br.close();
		bw.close();
	}
}
