/*
 * BAEKJOON 02564. 경비원
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_02564_경비원 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_02564.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int W = Integer.parseInt(st.nextToken()); // 가로 길이
		int H = Integer.parseInt(st.nextToken()); // 세로 길이
		int N = Integer.parseInt(br.readLine()); // 상가 수
		int[][] pos = new int[N+1][]; // 상가와 경비원의 위치를 입력할 배열
		
		// 상가와 경비원의 위치를 배열에 입력
		for(int i = 0; i < pos.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int dir = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			pos[i] = new int[] {dir, dist};
		}
		
		// 문제 풀이
		int minSum = 0;
		int guardDist = distance(H, W, pos[pos.length - 1]);
		
		for(int i = 0; i < N; i++) {
			int dist = distance(H, W, pos[i]);
			
			int clockwise = Math.abs(guardDist - dist);
			int counterClockwise = 2 * (H + W) - clockwise;
			
			minSum += Math.min(clockwise, counterClockwise);
		}
	
		// 결과 출력 및 프로그램 종료
		System.out.println(minSum);
		br.close();
	}
	
	public static int distance(int H, int W, int[] pos) {
		switch(pos[0]) {
		case 1: // North
			return pos[1];
		case 2: // South
			return W + H + (W - pos[1]);
		case 3: // West
			return 2 * (W + H) - pos[1];
		case 4: // East
			return W + pos[1];
		default:
			return 0;
		}
	}
}
