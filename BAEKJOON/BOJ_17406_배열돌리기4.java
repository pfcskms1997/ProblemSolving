/*
 * BAEKJOON 17406. 배열 돌리기 4
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17406_배열돌리기4 {

	static class Inst {
		int r;
		int c;
		int s;
		
		public Inst(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}

		@Override
		public String toString() {
			return "Inst [r=" + r + ", c=" + c + ", s=" + s + "]";
		}
	}
	
	static int[][] originalMatrix;
	static int N;
	static int M;
	static int K;
	static int minVal;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_17406.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 행 크기
		M = Integer.parseInt(st.nextToken()); // 열 크기
		K = Integer.parseInt(st.nextToken()); // 회전 연산의 개수
		originalMatrix = new int[N][M];
		minVal = 987654321;
		
		// 배열 입력
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c = 0; c < M; c++) {
				originalMatrix[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 연산 위치 정보(r, c, s)를 연산 수 K개 만큼 입력
		Inst[] ins = new Inst[K];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			ins[i] = new Inst(r, c, s);
		}
		
		// 조합 연산
		Inst[] selection = new Inst[K];
		boolean[] isSelected = new boolean[K];
		permutation(ins, selection, isSelected, 0);

		// 결과 출력 및 프로그램 종료
		System.out.println(minVal);
		br.close();
	}
	
	public static void permutation(Inst[] ins, Inst[] sel, boolean[] isSelected, int cnt) {
		// 순열의 Base Part
		if(cnt == sel.length) {
			//System.out.println(Arrays.toString(sel));
			int[][] tempMatrix = new int[N][M];
			
			// 임시 배열 복사
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					tempMatrix[r][c] = originalMatrix[r][c];
				}
			}
			
			// 명령어 개수만큼 rotate 함수를 반복 호출
			for(int rot = 0; rot < K; rot++) {
				rotate(tempMatrix, sel[rot]);
				//printMatrix(tempMatrix);
			}

			int val = 0;
			
			// 행별 합계의 최소값 구하기
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					val += tempMatrix[r][c];
				}
				minVal = Math.min(minVal, val);
				val = 0;
			}
			
			return;
		}
		
		// 순열의 Inductive Part
		for(int i = 0; i < ins.length; i++) {
			if(isSelected[i]) continue;
			
			sel[cnt] = ins[i];
			isSelected[i] = true;
			permutation(ins, sel, isSelected, cnt+1);
			isSelected[i] = false;
		}
	}

	// 하 -> 우 -> 상 -> 좌
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void rotate(int[][] matrix, Inst in) {		
		int n = (in.r + in.s) - (in.r - in.s) + 1;
		int m = (in.c + in.s) - (in.c - in.s) + 1;
		int rept = Math.min(n, m) / 2;
		
		// 지도의 크기에 따라 min(n, m) / 2 횟수만큼 반복
		for(int i = 0; i < rept; i++) {
			// 이동 방향 변수
			int dir = 0;
			// 시작점
			int sr = in.r - in.s - 1 + i;
			int sc = in.c - in.s - 1 + i;
			
			// 시작점 임시 저장
			int tmp_sr = sr;
			int tmp_sc = sc;
			
			// 기준점에 있는 값을 임시 저장
			int temp = matrix[sr][sc];
			// 방향을 4번 바꾸어 줌
			while(dir < 4) {
				int nr = sr + dr[dir];
				int nc = sc + dc[dir];
				
				//System.out.printf("경계선 : %d %d\n", in.r + in.s - i, in.c + in.s - i);
				// 경계선 체크
				if(nr >= tmp_sr && nr < (in.r + in.s) - i && nc >= tmp_sc && nc < (in.c + in.s) - i) {
					matrix[sr][sc] = matrix[nr][nc];
					sr = nr;
					sc = nc;
				}
				
				// 만약 경계선 밖으로 벗어나면 방향을 전환
				else dir++;
			}
			matrix[tmp_sr][tmp_sc + 1] = temp;
		}
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
