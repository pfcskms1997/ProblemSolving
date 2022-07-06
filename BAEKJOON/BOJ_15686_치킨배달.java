/*
 * BAEKJOON 15686. 치킨 배달
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15686_치킨배달 {

	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}
	}

	static int result;
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_15686.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 도시의 크기
			int M = Integer.parseInt(st.nextToken()); // 치킨집의 최대 개수
			int[][] city = new int[N+1][N+1];
			ArrayList<Point> pos = new ArrayList<Point>();
			result = Integer.MAX_VALUE;
			
			// 2차원 배열에 도시의 정보 입력
			for(int r = 1; r < city.length; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c = 1; c < city[r].length; c++) {
					city[r][c] = Integer.parseInt(st.nextToken());
					
					// 조합에 이용하기 위해 치킨집의 좌표만 따로 배열 리스트에 저장
					if(city[r][c] == 2) pos.add(new Point(r, c));
				}
			}
			
			// M개의 치킨집을 뽑는 조합을 이용하여 문제 풀이
			combination(city, pos, new Point[M], 0, 0);
			
			// 결과 출력
			bw.write(String.format("#%d %d\n", test_case, result));
			bw.flush();
		}
		// 자원 반납
		br.close();
		bw.close();
	}
	
	
	public static void combination(int[][] city, ArrayList<Point> pos, Point[] sel, int start, int cnt) {
		if(cnt == sel.length) {
			// System.out.println(Arrays.toString(sel));
			int chickenDistance = 0; // 도시의 치킨 거리
			
			// 도시를 (1, 1)부터 탐색하며 거리 계산하기
			for(int r = 1; r < city.length; r++) {
				for(int c = 1; c < city[r].length; c++) {
					// 현재 위치가 집인 경우, 뽑힌 모든 치킨집과의 거리를 계산하여 그 중에 최소값을 취함
					if(city[r][c] == 1) {
						int minDistance = Integer.MAX_VALUE; // 각각의 집과 치킨집 사이의 거리를 저장할 임시 변수
						
						for(int k = 0; k < cnt; k++) {
							int distance = Math.abs(r - sel[k].r) + Math.abs(c - sel[k].c);
									
							minDistance = Math.min(minDistance, distance);
						}
						chickenDistance += minDistance; // 현재 집과 치킨집 사이의 거리를 도시의 치킨 거리에 누적
					}
				}
			}
			
			result = Math.min(result, chickenDistance);
			return;
		}
		
		for(int i = start; i < pos.size(); i++) {
			sel[cnt] = pos.get(i);
			combination(city, pos, sel, i+1, cnt+1);
		}
	}
}
