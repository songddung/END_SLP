import java.io.*;
import java.util.*;

public class MazeSearch {
    static int n, m;
    static int[][] maze;
    static boolean[][] visited;
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        maze = new int[n][m];
        visited = new boolean[n][m];
        
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                maze[i][j] = line.charAt(j) - '0';
            }
        }
        
        System.out.println(bfs(0, 0));
    }
    
    static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;
        
        int[][] dist = new int[n][m];
        dist[x][y] = 1;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0];
            int cy = cur[1];
            
            if (cx == n - 1 && cy == m - 1) {
                return dist[cx][cy];
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (!visited[nx][ny] && maze[nx][ny] == 1) {
                        visited[nx][ny] = true;
                        dist[nx][ny] = dist[cx][cy] + 1;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
        return -1;
    }
}
