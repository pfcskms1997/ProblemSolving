/*
 * BAEKJOON 16935. 배열 돌리기 3
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16935_배열돌리기3 {

	// 우 -> 하 -> 좌 -> 상
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_16935.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int[][] matrix = new int[N][M];
			
			// 배열 입력
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c = 0; c < M; c++) {
					matrix[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// printMatrix(matrix);
			
			// 1차원 배열에 input mode 입력
			int[] mode = new int[R];
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < mode.length; i++) {
				mode[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i < mode.length; i++) {
				switch(mode[i]) {
				case 1:
					mode1(matrix); // 상하반전
					break;
				case 2:
					mode2(matrix); // 좌우반전
					break;
				case 3:
					matrix = mode3(matrix); // 오른쪽으로 90도 회전
					break;
				case 4:
					matrix = mode4(matrix); // 왼쪽으로 90도 회전
					break;
				case 5:
					matrix = mode5(matrix); // 4개의 섹터가 시계방향으로 1칸씩 이동
					break;
				case 6:
					matrix = mode6(matrix); // 4개의 섹터가 반시계방향으로 1칸씩 이동
					break;
				default:
					break;
				}
			}
			System.out.printf("#%d\n", test_case);
			printMatrix(matrix);
		}
		br.close();
	}
	
	public static void mode1(int[][] matrix) {
		for(int r = 0; r < matrix.length / 2; r++) {
			for(int c = 0; c < matrix[0].length; c++) {
				int temp = matrix[r][c];
				matrix[r][c] = matrix[(matrix.length - 1) - r][c];
				matrix[(matrix.length - 1) - r][c] = temp;
			}
		}
	}
	
	public static void mode2(int[][] matrix) {
		for(int c = 0; c < matrix[0].length / 2; c++) {
			for(int r = 0; r < matrix.length; r++) {
				int temp = matrix[r][c];
				matrix[r][c] = matrix[r][(matrix[0].length - 1) - c];
				matrix[r][(matrix[0].length - 1) - c] = temp;
			}
		}
	}
	
	public static int[][] mode3(int[][] matrix) {
		int N = matrix[0].length;
		int M = matrix.length;
		int[][] rotMat = new int[N][M];
		
		for(int r = 0; r < rotMat.length; r++) {
			for(int c = 0; c < rotMat[r].length; c++) {
				rotMat[r][c] = matrix[M - 1 - c][r];
			}
		}
		return rotMat;
	}
	
	public static int[][] mode4(int[][] matrix) {
		int N = matrix[0].length;
		int M = matrix.length;
		int[][] rotMat = new int[N][M];
		
		for(int r = 0; r < rotMat.length; r++) {
			for(int c = 0; c < rotMat[r].length; c++) {
				rotMat[r][c] = matrix[c][N - 1 - r];
			}
		}
		return rotMat;
	}
	
	public static int[][] mode5(int[][] matrix) {
		int[][] tempArr = new int[matrix.length][matrix[0].length];
		
		// 1사분면
		for(int r = 0; r < matrix.length / 2; r++) {
			for(int c = matrix[r].length / 2; c < matrix[r].length; c++) {
				tempArr[r][c] = matrix[r][c - (matrix[0].length / 2)];
			}
		}
		
		// 2사분면
		for(int r = 0; r < matrix.length / 2; r++) {
			for(int c = 0; c < matrix[r].length / 2; c++) {
				tempArr[r][c] = matrix[matrix.length / 2 + r][c];
			}
		}
		
		// 3사분면
		for(int r = matrix.length / 2; r < matrix.length; r++) {
			for(int c = 0; c < matrix[r].length / 2; c++) {
				tempArr[r][c] = matrix[r][matrix[0].length / 2 + c];
			}
		}
		
		// 4사분면
		for(int r = matrix.length / 2; r < matrix.length; r++) {
			for(int c = matrix[r].length / 2; c < matrix[r].length; c++) {
				tempArr[r][c] = matrix[r - (matrix.length / 2)][c];
			}
		}
		
		return tempArr;
	}
	
	public static int[][] mode6(int[][] matrix) {
		int[][] tempArr = new int[matrix.length][matrix[0].length];
		
		// 1사분면
		for(int r = 0; r < matrix.length / 2; r++) {
			for(int c = matrix[r].length / 2; c < matrix[r].length; c++) {
				tempArr[r][c] = matrix[(matrix.length / 2) + r][c];
			}
		}
		
		// 2사분면
		for(int r = 0; r < matrix.length / 2; r++) {
			for(int c = 0; c < matrix[r].length / 2; c++) {
				tempArr[r][c] = matrix[r][(matrix[0].length / 2) + c];
			}
		}
		
		// 3사분면
		for(int r = matrix.length / 2; r < matrix.length; r++) {
			for(int c = 0; c < matrix[r].length / 2; c++) {
				tempArr[r][c] = matrix[r - (matrix.length / 2)][c];
			}
		}
		
		// 4사분면
		for(int r = matrix.length / 2; r < matrix.length; r++) {
			for(int c = matrix[r].length / 2; c < matrix[r].length; c++) {
				tempArr[r][c] = matrix[r][c - (matrix[0].length / 2)];
			}
		}
		
		return tempArr;
	}
	
	public static void printMatrix(int[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
