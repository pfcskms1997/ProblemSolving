/*
 * SWEA 09760. Poker Game
 */

package difficulty04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_09760_PokerGame_X {
	
	static String[] handRankings = {"",
									"Straight Flush",
									"Four of a Kind",
									"Full House",				
									"Flush",
									"Straight",
									"Three of a kind",
									"Two pair",
									"One pair",
									"High card"};

	public static void main(String[] args) throws Exception {
		// File I/O
		System.setIn(new FileInputStream("./input/difficulty04/input_09760.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		// TestCase input 여부에 따라 변경
		int T = Integer.parseInt(br.readLine());
		
		// 각 TestCase별 수행
		for(int test_case = 1; test_case <= T; test_case++) {
			int char
			int rank = 9;
			
			
			bw.write(String.format("#%d \n", test_case));
			bw.flush();
		}

		br.close();
		bw.close();
	}
}
