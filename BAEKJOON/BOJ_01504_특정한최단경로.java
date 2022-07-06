/*
 * BAEKJOON 01504. 특정한 최단 경로
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_01504_특정한최단경로 {
	
	static class Node implements Comparable<Node> {
		int e;
		int w;
		
		public Node(int e, int w) {
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}

		@Override
		public String toString() {
			return "[e=" + e + "]";
		}
	}
	
	static final int INF = 200000000;
	
	static int N, E;
	static ArrayList<Node>[] graph;
	static int[] distance;
	static boolean[] v;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_01504.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// [1] 정보 입력
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		E = Integer.parseInt(st.nextToken()); // 간선의 개수
		graph = new ArrayList[N+1]; // 인접리스트
		distance = new int[N+1]; // 최소 거리를 저장할 1차원 배열
		v = new boolean[N+1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Node>();
		}
		
		// 무방향 그래프 생성
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[start].add(new Node(end, weight));
			graph[end].add(new Node(start, weight));
		}
		
		//printGraph(graph);
		
		// 반드시 거쳐야 할 노드의 번호
		st = new StringTokenizer(br.readLine(), " ");
		int p1 = Integer.parseInt(st.nextToken());
		int p2 = Integer.parseInt(st.nextToken());
		
		// [2] 문제 해결
		int ans = solve(p1, p2);
		
		// [3] 결과 출력 및 종료
		bw.write(String.format("%d\n", ans));
		bw.flush();
		br.close();
		bw.close();
	}
	
	private static void printGraph(ArrayList<Node>[] graph) {
		for(int i = 1; i <= N; i++) {
			System.out.printf("%d번 노드 ", i);
			for(int j = 0; j < graph[i].size(); j++) {
				System.out.printf("- %s ", graph[i].get(j).toString());
			}
			System.out.println();
		}
	}

	public static int solve(int p1, int p2) {
		int path1 = dijkstra(1, p1) + dijkstra(p1, p2) + dijkstra(p2, N);
		int path2 = dijkstra(1, p2) + dijkstra(p2, p1) + dijkstra(p1, N);
		
		if(path1 >= INF && path2 >= INF) return -1;
		return Math.min(path1, path2);
	}

	public static int dijkstra(int start, int end) {
		// 최소 거리 배열 distance와 방문 배열 v 초기화
		Arrays.fill(distance, INF);
		Arrays.fill(v, false);
		
		PriorityQueue<Node> pQueue = new PriorityQueue<Node>();
		pQueue.offer(new Node(start, 0));
		distance[start] = 0;
		
		while(!pQueue.isEmpty()) {
			Node cur = pQueue.poll();
			
			// 이미 처리된 노드이면 스킵
			if(v[cur.e]) continue;

			v[cur.e] = true;
			for(int i = 0; i < graph[cur.e].size(); i++) {
				Node next = graph[cur.e].get(i);
				int nextNode = next.e;
				int	nextWeight = next.w;
				
				if(!v[nextNode] && distance[nextNode] > cur.w + nextWeight) {
					distance[nextNode] = cur.w + nextWeight;
					pQueue.offer(new Node(nextNode, distance[nextNode]));
				}
			}
		}
		
		return distance[end];
	}
}
