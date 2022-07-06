/*
 * BAEKJOON 01244. 스위치 켜고 끄기
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_01244_스위치켜고끄기 {

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_01244.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int switchNum = Integer.parseInt(br.readLine()); // 스위치의 개수
		int[] switchState = new int[switchNum+1]; // index를 1부터 함

		// 스위치 상태 배열 입력
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i < switchState.length; i++) {
			switchState[i] = Integer.parseInt(st.nextToken());
		}
		
		// 학생 수만큼 학생의 성별과 학생이 받은 수를 입력
		int studentNum = Integer.parseInt(br.readLine()); // 학생 수
		int[][] student = new int[studentNum][];
		for(int i = 0; i < studentNum; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int gender = Integer.parseInt(st.nextToken()); // 학생의 성별
			int num = Integer.parseInt(st.nextToken()); // 학생이 받은 수
			
			student[i] = new int[] {gender, num};
		}
		
		// 문제 풀이
		for(int i = 0; i < studentNum; i++) {
			if(student[i][0] == 1) { // 남학생인 경우
				// 스위치의 번호가 학생이 받은 번호의 배수에 해당하면 상태를 바꿈
				for(int j = 1; j < switchState.length; j++) {
					if(j % student[i][1] == 0) {
						switchState[j] = (switchState[j] == 0) ? 1 : 0;
					}
				}
			}
			else { // 여학생인 경우
				int dist = 1;
				while(true) {
					int prev = student[i][1] - dist;
					int next = student[i][1] + dist;
					
					// 범위 체크
					if(prev > 0 && next < switchState.length) {
						if(switchState[prev] == switchState[next]) {
							dist++;
							continue;
						}
						dist--;
						break;
					}
					else {
						dist--;
						break;
					}
				}
				
				for(int j = student[i][1] - dist; j <= student[i][1] + dist; j++) {
					switchState[j] = (switchState[j] == 0) ? 1 : 0;
				}
			}
		}
		
		// 스위치를 20개씩 출력하도록 문자열 조작
		for(int i = 1; i < switchState.length; i++) {
			sb.append(switchState[i] + " ");
			if(i % 20 == 0) {
				sb.append("\n");
			}
		}

		// 결과 출력 및 프로그램 종료
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
