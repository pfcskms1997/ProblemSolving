/*
 * SWEA 02001. 파리 퇴치
 */

package difficulty02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_02001_파리퇴치 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/difficulty02/input_02001.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken()); // 파리채의 크기
			int[][] coord = new int[N][N];
			int max = 0;
			int sum = 0;
			
			// 배열 입력
			for(int r = 0; r < coord.length; r++) {
				StringTokenizer st1 = new StringTokenizer(in.readLine(), " ");
				for(int c = 0; c < coord[r].length; c++) {
					coord[r][c] = Integer.parseInt(st1.nextToken());
				}
			}
			
			// N*N 배열을 탐색하며 시작점 r과 c로부터 파리채의 범위 내의 파리의 합을 계산하여 최대값을 찾음
			for(int r = 0; r < coord.length - M + 1; r++) {
				for(int c = 0; c < coord[r].length - M + 1; c++) {
					sum = 0;
					for(int p = 0; p < M; p++) {
						for(int q = 0; q < M; q++) {
							sum += coord[r + p][c + q];
						}
					}
					max = Math.max(sum, max);
				}
			}
			System.out.printf("#%d %d\n", test_case, max);
		}
		in.close();
	}
}
