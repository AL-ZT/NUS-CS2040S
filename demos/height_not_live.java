// TWO subtle-bugs inside...

import java.util.*;

public class height {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int P = sc.nextInt();
    while (P-- > 0) {
      int K = sc.nextInt();
      int n = 20; // always 20
      int[] H = new int[n];
      for (int i = 0; i < n; ++i)
        H[i] = sc.nextInt();

      int ans = 0;
      for (int i = 1; i < n; ++i) {
        int X = H[i];
        int j = i-1;
        while ((j > 0) && (H[j] > X)) {
          H[j+1] = H[j];
          --j;
          --ans;
        }
        H[j+1] = X;
      }

      System.out.println(K + " " + ans);
    }
  }
}
