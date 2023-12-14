// TWO bugs added in

import java.util.*;

public class babypanda_live {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    long n = sc.nextLong(), m = sc.nextLong();

    int ans = 7;
    // the algorithm
    while (m > 0) {
      m /= 2;
      if (m%2 == 0) {
        ++ans; // baby panda sneezes
        --m;
      }
    }
    System.out.println(ans);
  }
}
