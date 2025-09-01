package dfsbfs;

import java.util.*;
import java.io.*;

// 정점의 개수 N, 간선의 개수 M, 탐색 시작 정점 번호 V
// M개의 줄에 연결하는 두 정점의 번호
// 어떤 두 정점 사이에는 여러 개의 간선이 있을 수 있고, 주어지는 간선은 양방향

public class Yujin {
	static int N, M, V;
	static ArrayList<Integer>[] adjList;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	// DFS(깊이 우선 탐색)
	static void dfs(int node) {
		visited[node] = true;
		sb.append(node).append(' ');
		
		// 연결된 노드들을 작은 번호부터 방문
		for (int next : adjList[node]) {
			if (!visited[next]) {
				dfs(next); // 재귀 호출
			}
		}
	}
	
	// BFS(너비 우선 탐색)
	static void bfs(int start) {
		int[] queue = new int[N+1];
		int front = 0, rear = 0;
		
		visited[start] = true;
		queue[rear++] = start;
		
		while (front < rear) {
			int node = queue[front++];
			sb.append(node).append(' ');
			
			// 연결된 모든 노드 확인
			for (int next : adjList[node]) {
				if (!visited[next]) {
					visited[next] = true;
					queue[rear++] = next;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		// 인접리스트 초기화
		adjList = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		// 간선 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjList[a].add(b); // 양방향 간선
			adjList[b].add(a);
		}
		
		// 정점 번호가 작은 것부터 방문하기 위해 정렬
		for (int i = 1; i <= N; i++) {
			Collections.sort(adjList[i]);
		}
		
		// DFS 실행
		visited = new boolean[N+1];
		dfs(V);
		sb.append('\n');
		
		//BFS 실행
		Arrays.fill(visited, false); // 모든 배열을 false로 초기화
		bfs(V);
		
		System.out.println(sb.toString());
	}
}