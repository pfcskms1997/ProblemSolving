/*
 * BAEKJOON 09205. 맥주 마시면서 걸어가기
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_09205_맥주마시면서걸어가기 {
	
	static class Point {
		int no;
		int x;
		int y;
		
		public Point(int no, int x, int y) {
			this.no = no;
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return String.format("x: %d, y: %d", this.x, this.y);
		}
	}
	
	static int n;
	static ArrayList<Point>[] graph;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_09205.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			n = Integer.parseInt(br.readLine()); // 편의점의 개수
			graph = new ArrayList[n+2]; // 인접리스트
			
			// 그래프 초기화
			for(int i = 0; i < graph.length; i++) {
				graph[i] = new ArrayList<Point>();
			}
			
			// 모든 좌표 정보 입력
			// [0]: home, [n+1]: festa
			Point[] coords = new Point[n+2];
			StringTokenizer st = null;
			for(int i = 0; i < coords.length; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				coords[i] = new Point(i, x, y);
			}
			
			// 모든 정점을 서로 비교하며 그래프 생성
			for(int i = 0; i < coords.length; i++) {
				for(int j = 0; j < coords.length; j++) {
					if(i == j) continue; // 같은 정점이면 skip
					
					// 서로 다른 두 정점의 거리가 1000 이하(맥주 20병을 모두 소비하고 갈 수 있는 최대 거리)일 때 연결
					if(calcDistance(coords[i], coords[j]) <= 1000) {
						graph[i].add(coords[j]);
					}
				}
			}
			
			String ans = BFS(graph) ? "happy" : "sad";
			
			bw.write(String.format("#%d %s\n", test_case, ans));
			bw.flush();
		}
		br.close();
		bw.close();
	}
	
	// 맨해튼 거리 계산 방식으로 두 정점의 거리를 계산하여 반환
	public static int calcDistance(Point a, Point b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}
	
	// BFS 방식으로 graph가 출발점부터 도착점까지 연결되어 있는지를 판단
	public static boolean BFS(ArrayList<Point>[] graph) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] v = new boolean[n+2];
		
		queue.offer(0);
		v[0] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			if(cur == n+1) return true; // 도착점을 찾음
			
			for(int i = 0; i < graph[cur].size(); i++) {
				int nextNode = graph[cur].get(i).no; // 다음 노드의 번호
				
				if(!v[nextNode]) {
					queue.offer(nextNode);
					v[nextNode] = true;
				}
			}
		}
		
		return false; // BFS를 마칠 때까지 도착점을 찾지 못함
	}
}
