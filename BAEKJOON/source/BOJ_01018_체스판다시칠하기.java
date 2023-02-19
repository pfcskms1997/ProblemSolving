/*
 * BAEKJOON 01018. 체스판 다시 칠하기
 */

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_01018_체스판다시칠하기 {

    static char[][] pattern = { {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
                                {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'} };

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input/input_01018.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            char[][] board = new char[N][];
            int ans = Integer.MAX_VALUE;

            // board 입력
            for (int r = 0; r < N; r++) {
                board[r] = br.readLine().toCharArray();
            }

            // solution
            for (int r = 0; r <= N - 8; r++) {
                for (int c = 0; c <= M - 8; c++) {
                    ans = Math.min(ans, minCnt(board, r, c));
                }
            }

            System.out.printf("#%d %d\n", test_case, ans);
        }
        br.close();
    }

    // 현재 범위에서 발생하는 최소 변경 횟수를 반환
    public static int minCnt(char[][] board, int sr, int sc) {
        int blackFirst = 0;
        int whiteFirst = 0;
        for (int r = sr; r < sr + 8; r++) {
            for (int c = sc; c < sc + 8; c++) {
                if(board[r][c] != pattern[(r - sr) % 2][c - sc]) {
                    blackFirst++;
                }

                if(board[r][c] != pattern[(r - sr + 1) % 2][c - sc]) {
                    whiteFirst++;
                }
            }
        }

        return Math.min(blackFirst, whiteFirst);
    }
}
