package finalsprep;

// TLE
import java.io.*;
import java.util.*;

public class onepunch {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    StringTokenizer st = new StringTokenizer(br.readLine());
    int rows = Integer.parseInt(st.nextToken()), cols = Integer.parseInt(st.nextToken());
    int queries = Integer.parseInt(st.nextToken());
    char[][] maze = new char[rows][cols];
    for (int i = 0; i < rows; i++) {
      maze[i] = br.readLine().toCharArray();
    }
    while (queries-- > 0) {
      int[][] visited = new int[rows][cols];
      st = new StringTokenizer(br.readLine());
      int startR = Integer.parseInt(st.nextToken()) - 1, startC = Integer.parseInt(st.nextToken()) - 1;
      int endR = Integer.parseInt(st.nextToken()) - 1, endC = Integer.parseInt(st.nextToken()) - 1;
      int punches = Integer.parseInt(st.nextToken());
      boolean reachable = onepunch.reachable(startR, startC, endR, endC, rows - 1, cols - 1, punches, maze, visited);
      pw.println(reachable ? "yes" : "no");
    }
    pw.close();
    br.close();
  }

  public static boolean reachable(
      int r, int c,
      int endR, int endC,
      int rLim, int cLim,
      int punches,
      char[][] maze,
      int[][] visited) {

    if (r == endR && c == endC) {
      return true;
    }

    if (visited[r][c] >= punches + 1) {
      return false;
    }

    if (maze[r][c] == '#') {
      if (punches == 0) {
        return false;
      }
      punches -= 1;
    }

    visited[r][c] = punches + 1;

    // UP
    if (r != 0 && reachable(r - 1, c, endR, endC, rLim, cLim, punches, maze, visited)) {
      return true;
    }

    // Down
    if (r != rLim && reachable(r + 1, c, endR, endC, rLim, cLim, punches, maze, visited)) {
      return true;
    }

    // Left
    if (c != 0 && reachable(r, c - 1, endR, endC, rLim, cLim, punches, maze, visited)) {
      return true;
    }

    // Right
    if (c != cLim && reachable(r, c + 1, endR, endC, rLim, cLim, punches, maze, visited)) {
      return true;
    }

    return false;
  }
}
