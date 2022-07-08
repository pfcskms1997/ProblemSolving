package im_problem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class P42_미로도착지점 {
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/Solution42.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 배열의 크기
			int R = Integer.parseInt(st.nextToken()); // 시작 행 좌표
			int C = Integer.parseInt(st.nextToken()); // 시작 열 좌표
			int J = Integer.parseInt(st.nextToken()); // 점퍼의 개수
			int[][] maze = new int[N+1][N+1]; // 미로 배열
			
			// 2차원 미로 배열에 점퍼의 위치를 입력
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < J; i++) {
				int jr = Integer.parseInt(st.nextToken()); // 점퍼의 행 좌표
				int jc = Integer.parseInt(st.nextToken()); // 점퍼의 열 좌표
				
				maze[jr][jc] = 1; // 점퍼 좌표에 1을 표시
			}
			
			// 이동 지시를 2차원 배열에 저장
			int M = Integer.parseInt(br.readLine()); // 이동지시 개수
			int[][] moveInst = new int[M][];
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < M; i++) {
				int dir = Integer.parseInt(st.nextToken()); // 이동 방향
				int space = Integer.parseInt(st.nextToken()); // 이동 칸 수
				
				moveInst[i] = new int[] {dir, space};
			}
			
			int[] lastPos = findLastPosition(maze, moveInst, R, C);
			
			// 결과 출력
			bw.write(String.format("#%d %d %d\n", test_case, lastPos[0], lastPos[1]));
			bw.flush();
		}
		// 자원 반납
		br.close();
		bw.close();
	}
	
	//				   X   북      동      남      서  
	static int[] dr = {0, -1,  0,  1,  0};
	static int[] dc = {0,  0,  1,  0, -1};
	
	public static int[] findLastPosition(int[][] maze, int[][] in, int r, int c) {
		int nr = r;
		int nc = c;
		
		// 이동지시의 수만큼 동작
		for(int i = 0; i < in.length; i++) {
			nr += dr[in[i][0]] * in[i][1];
			nc += dc[in[i][0]] * in[i][1];
			
			// 유효 범위이고 이동할 좌표가 점프가 아닌경우
			if(nr >= 1 && nr < maze.length && nc >= 1 && nc < maze[i].length && maze[nr][nc] != 1) continue;
			// 유효 범위 밖이거나 이동할 좌표가 점프인 경우
			else {
				nr = 0;
				nc = 0;
				break;
			}
		}
		
		return new int[] {nr, nc};
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
