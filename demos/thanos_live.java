// TWO subtle-bugs inside...

import java.io.*;

class thanos {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    while (T-- > 0) {
      String[] token = br.readLine().split(" ");
      long P = Long.parseLong(token[0]);
      long R = Long.parseLong(token[1]);
      long F = Long.parseLong(token[2]);
      int year = 1;
      while (P < F) { // while still got enough food
        ++year; // survives one more year
        P *= R;
      }
      System.out.println(year);
    }
  }
}
