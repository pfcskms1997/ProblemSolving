/*
 * SWEA 01946. 간단한 압축 풀기
 */

package difficulty02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_01946_간단한압축풀기 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty02/input_01946.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		// TestCase input 여부에 따라 변경
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 입력 개수
			String temp = "";
			
			// 주어진 알파벳을 입력된 횟수만큼 temp에 누적시킴
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				String alphabet = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				
				for(int j = 0; j < num; j++) {
					temp += alphabet;
				}
			}
			
			// 문자 10개마다 개행이 이루어지도록 함
			sb.append(String.format("#%d\n", test_case));
			for(int i = 0 ; i < temp.length(); i++) {
				sb.append(temp.charAt(i));
				
				if((i+1) % 10 == 0) sb.append("\n");
			}
			sb.append("\n");
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
