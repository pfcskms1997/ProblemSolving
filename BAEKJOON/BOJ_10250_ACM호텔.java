/*
 * BAEKJOON 10250. ACM 호텔
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_10250_ACM호텔 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_10250.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int H = Integer.parseInt(st.nextToken()); // 층 수
			int W = Integer.parseInt(st.nextToken()); // 각 층의 방의 수
			int N = Integer.parseInt(st.nextToken()); // N번째 손님
			
			int YY = 0; // 배정 층수
			int XX = 0; // 배정 방 번호
			
			if(N % H == 0) {
				YY = H * (N % H + 1);
				XX = N / H;
			}
			else {
				YY = N % H;
				XX = N / H + 1;
			}
			
			//System.out.printf("%d%02d\n", YY, XX);
			sb.append(String.format("%d%02d\n", YY, XX));
		}
		
		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
