/*
 * SWEA 13038. 교환학생
 */

package difficulty03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_13038_교환학생 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty03/input_13038.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[] week = new int[7];
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < week.length; i++) {
				week[i] = Integer.parseInt(st.nextToken());
			}
			
			int min = Integer.MAX_VALUE;			
			for(int start = 0; start < 7; start++) {
				// 수업이 시작하는 모든 요일 처리
				if(week[start] == 0) continue; // 수업이 열리지 않는 날은 패스
				
				int day = start;
				int cnt = 0;
				while(true) {
					if(week[day % 7] == 1) cnt++; // 수업이 열리면 카운팅
					
					day++; // 하루 지남
					
					if(cnt == N) { // 머무른 최소 일수
						min = Math.min(min,  day - start);
						break;					
					}
				}
			}
			// 결과 출력
			bw.write(String.format("#%d %d\n", test_case, min));
			bw.flush();
		}
		br.close();
		bw.close();
	}
}
