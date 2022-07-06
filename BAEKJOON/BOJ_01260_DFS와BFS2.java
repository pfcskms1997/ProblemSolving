/*
 * BAEKJOON 01260. DFS와 BFS
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_01260_DFS와BFS2 {
	
	static class Node implements Comparable<Node> {
		int vertex;
		Node link;
		
		public Node(int vertex, Node link) {
			this.vertex = vertex;
			this.link = link;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(o.vertex, this.vertex);
		}
		
		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", link=" + link + "]";
		}
	}
	
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input/input_01260.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int V = Integer.parseInt(st.nextToken()); // 정점의 개수
			int E = Integer.parseInt(st.nextToken()); // 간선의 개수
			int start = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점의 번호
			sb = new StringBuilder();
			
			int[][] edge = new int[E][2];
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < 2; j++) {
					edge[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Comparator<int[]> com = new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0] == o2[0] ? Integer.compare(o2[1], o1[1]) : Integer.compare(o2[0], o2[0]);
				}
			};
			
			Arrays.sort(edge, com);
			
			// 인접리스트 생성
			Node[] adjList = new Node[V+1];
			for(int i = 0; i < E; i++) {
//				st = new StringTokenizer(br.readLine(), " ");
//				int from = Integer.parseInt(st.nextToken());
//				int to = Integer.parseInt(st.nextToken());
//				
//				adjList[from] = new Node(to, adjList[from]);
//				adjList[to] = new Node(from, adjList[to]);
				
				int from = edge[i][0];
				int to = edge[i][1];
				
				adjList[from] = new Node(to, adjList[from]);
				adjList[to] = new Node(from, adjList[to]);
				
			}
			
			// printList(adjList);
			
			sb.append(String.format("#%d\n", test_case));
			DFS(adjList, new boolean[V+1], start);
			sb.append("\n");
			BFS(adjList, new boolean[V+1], start);
			sb.append("\n");
			
			bw.write(sb.toString());
			bw.flush();
		}
		br.close();
		bw.close();
	}
	
	public static void DFS(Node[] adjList, boolean[] v, int current) {
		v[current] = true;
		sb.append(current + " ");
		
		for(Node temp = adjList[current]; temp != null; temp = temp.link) {
			if(!v[temp.vertex]) {
				DFS(adjList, v, temp.vertex);
			}
		}
	}
	
	public static void BFS(Node[] adjList, boolean[] v, int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.offer(start);
		v[start] = true;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			sb.append(current + " ");
			
			for(Node temp = adjList[current]; temp != null; temp = temp.link) {
				if(!v[temp.vertex]) {
					q.offer(temp.vertex);
					v[temp.vertex] = true;
				}
			}
		}
	}
	
	public static void printList(Node[] list) {
		for(int i = 1; i < list.length; i++) {
			if(list[i] == null) {
				System.out.println(i + " NULL");
				continue;
			}
			System.out.println(i + " " + list[i].toString());
		}
	}
}
