/*
 * BAEKJOON 17281. ⚾
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_17281_야구 {

	static int maxScore;
	
	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/input_17281.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine()); // 전체 이닝 수
			int[][] game = new int[N][10];
			maxScore = 0;
			
			StringTokenizer st = null;
			for(int i = 0; i < game.length; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				for(int j = 1; j < game[i].length; j++) {
					game[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[] sel = new int[10];
			sel[4] = 1; // 1번 선수는 4번 타자로 고정
			permutation(game, sel, 0, 1);
			
			bw.write(String.format("#%d %d\n", test_case, maxScore));
			bw.flush();
		}
		br.close();
		bw.close();
	}
	
	public static void permutation(int[][] game, int[] sel, int flag, int cnt) {
		if(cnt == sel.length) {
			// 현재 구한 타순으로 경기 시뮬레이션
			simulation(game, sel);
			return;
		}
		
		for(int i = 2; i < sel.length; i++) {
			if((flag & 1 << i) != 0) continue;
			
			if(cnt == 4) {
				cnt++; // 다음 순번으로 건너 뜀
				i--; // i 선수를 건너뛰지 않도록 1 빼줌
				continue;
			}
			
			sel[cnt] = i;
			permutation(game, sel, (flag | 1 << i), cnt+1);
		}
	}
	
	public static void simulation(int[][] game, int[] order) {
		int score = 0;
		int player = 1; // 1번 타자가 최초 시작 
		
		for(int i = 0; i < game.length; i++) {
			boolean[] base = {false, false, false, false}; // 홈, 1루, 2루, 3루
			int outCnt = 0; // 아웃된 횟수를 기록
			
			// 각 이닝(3아웃 되면 한 이닝 종료)
			while(outCnt < 3) {
				if(player > 9) player = 1;
				
				base[0] = true; // 선수가 타석(홈)에 올라옴
				int hit = game[i][order[player]]; // 현재 타자가 공을 친 결과
				int getPoint = changeBaseState(base, hit);
				
				if(getPoint < 0) outCnt++; // 현재 타자가 아웃 된 경우
				else score += getPoint;

				player = (player % 9) + 1; // 다음 플레이어 호출
			}
		}
		
		// 모든 이닝이 종료되었을 때의 최대 점수를 기록
		maxScore = Math.max(maxScore, score);
	}
	
	// 획득한 점수를 반환, 또는 획득한 점수가 없으면 -1을 반환
	public static int changeBaseState(boolean[] base, int hit) {
		int score = 0;
		
		switch(hit) {
		case 0: // out
			return -1;
		case 1: // 안타
			for(int i = 3; i >= 0; i--) {
				if(base[i]) {
					if(i == 3) {
						score++;
						base[i] = false;
					}
					else {
						base[i+1] = true;
						base[i] = false;
					}
				}
			}
			return score;
		case 2: // 2루타
			for(int i = 3; i >= 0; i--) {
				if(base[i]) {
					if(i == 3 || i == 2) {
						score++;
						base[i] = false;
					}
					else {
						base[i+2] = true;
						base[i] = false;
					}
				}
			}
			return score;
		case 3: // 3루타
			for(int i = 3; i >= 0; i--) {
				if(base[i]) {
					if(i != 0) { // 홈이 아닌 나머지 base에 선수가 있는 경우
						score++;
						base[i] = false;
					}
					else {
						base[i+3] = true;
						base[i] = false;
					}
				}
			}
			return score;
		case 4: // 홈런
			// 모든 base를 확인하여 선수를 합산하고, 모든 base를 초기화
			for(int i = 0; i < base.length; i++) {
				if(base[i]) score++;
				base[i] = false;
			}
			return score;
		}
		return -1;
	}
}
