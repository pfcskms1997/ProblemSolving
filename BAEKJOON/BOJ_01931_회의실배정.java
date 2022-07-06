/*
 * BAEKJOON 01931. 회의실 배정
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_01931_회의실배정 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_01931.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 회의의 수
		int[][] meeting = new int[N][]; // 미팅 정보(idx 0: 시작시간, idx 1: 종료시간)
		
		// 각 회의에 대한 정보를 2차원 배열에 입력
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken()); // 회의 시작시간
			int end = Integer.parseInt(st.nextToken()); // 회의 종료시간
			meeting[i] = new int[] {start, end};
		}
		
		// 회의 배열을 정렬하기 위한 Comparator
		Comparator<int[]> com = new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// 종료시간이 다를 경우 종료시간으로 오름차순 정렬. 같을 경우는 시작시간으로 오름차순 정렬
				return o1[1] != o2[1] ? o1[1] - o2[1] : o1[0] - o2[0];
			}
		};
		
		Arrays.sort(meeting, com);
		
		// 배정 가능한 회의 수 세기
		int count = 1;
		int idx = 0;
		for(int i = 1; i < meeting.length; i++) {
			if(meeting[idx][1] <= meeting[i][0]) {
				count++;
				idx = i; // 다음 비교 때에는 가장 최근에 추가된 회의의 정보를 사용해야 하므로, 그에 해당하는 인덱스로 바꾸어 줌
			}
		}
		
		// 결과 출력 및 프로그램 종료
		System.out.println(count);
		br.close();
	}
}
