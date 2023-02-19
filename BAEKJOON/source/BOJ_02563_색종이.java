/*
 * BAEKJOON 02563. 색종이
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_02563_색종이 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_02563.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 색종이의 수
		boolean[][] paper = new boolean[100][100];
		int area = 0;
		
		// 색종이의 좌측 하단 시작 값을 입력받아 색종이의 크기(가로 10, 세로 10) 만큼 true로 마킹
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int in_c = Integer.parseInt(st.nextToken()) - 1;
			int in_r = Integer.parseInt(st.nextToken()) - 1;
			
			for(int r = in_r; r < in_r + 10; r++) {
				for(int c = in_c; c < in_c + 10; c++) {
					paper[r][c] = true;
				}
			}
		}
		
		// 도화지에 색종이가 붙여진 부분의 넓이(true의 합)를 계산
		for(int i = 0; i < paper.length; i++) {
			for(int j = 0; j < paper[i].length; j++) {
				if(paper[i][j]) area++;
			}
		}
		System.out.println(area);
	}
}
