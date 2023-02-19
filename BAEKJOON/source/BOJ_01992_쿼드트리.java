/*
 * BAEKJOON 01992. 쿼드트리
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BOJ_01992_쿼드트리 {
	
	static String result;

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_01992.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 영상의 크기
		int[][] image = new int[N][N];
		result = "";
		
		// 2차원 배열에 흑백 이미지 배열 입력
		for(int r = 0; r < N; r++) {
			String temp = br.readLine();
			for(int c = 0; c < N; c++) {
				image[r][c] = temp.charAt(c) - '0';
			}
		}
		
		quadTree(image, 0, 0, N);
		
		System.out.println(result); // 시작 좌표와 시작 크기를 인자로 넘김
		
		br.close();
	}
	
	public static void quadTree(int[][] image, int r, int c, int N) {
		// 현재 영역이 압축 가능한지 확인
		boolean isCompressible = true;
		int i = r;
		
		// 시작점의 값과 현재 탐색하는 범위의 값이 모두 같은지 확인
		while(isCompressible && i < r + N) {
			for(int j = c; j < c + N; j++) {
				if(image[r][c] != image[i][j]) {
					isCompressible = false;
				}
			}
			i++;
		}
		
		// 압축이 가능하면 시작점의 값을 문자열로 append
		if(isCompressible) {
			result += Integer.toString(image[r][c]);
			return;
		}
		
		// 현재 영역이 압축 불가하면 영역을 4분할하여 재귀호출
		result += "(";

		quadTree(image, r, c, N/2); // 2사분면
		quadTree(image, r, c + N/2, N/2); // 1사분면
		quadTree(image, r + N/2, c, N/2); // 3사분면
		quadTree(image, r + N/2, c + N/2, N/2); // 4사분면
		
		result += ")";
	}

	public static void printMap(int[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
