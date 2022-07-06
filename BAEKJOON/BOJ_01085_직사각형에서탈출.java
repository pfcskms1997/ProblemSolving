/*
 * BAEKJOON 01085. 직사각형에서 탈출
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_01085_직사각형에서탈출 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_01085.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int[] dist = new int[4];
			
			for(int i = 0; i < dist.length; i++) {
				if(i < 2) dist[i] = Integer.parseInt(st.nextToken());
				else dist[i] = Integer.parseInt(st.nextToken()) - dist[i-2];
			}
			
			Arrays.sort(dist);
			
			// 결과 출력
			bw.write(String.format("#%d %d\n", test_case, dist[0]));
			bw.flush();
		}
		
		// 자원 반환
		br.close();
		bw.close();
	}
}
