// TWO subtle-bugs inside...

import java.util.*;

public class heirsdilemma {
  private static Boolean ok(int v) { // v is a 6 digits decimal
    int[] f = new int[10]; // frequenc(ies) of digit [0,1,2,...,9] all zeroes first
    int rem = v; // keep v intact, needed below
    while (rem > 0) { // while still have a digit, O(# of digits in rem) * a bunch of O(1)s = O(6 * a few steps) ~= O(1)
      int d = rem%10; // get the last digit
      if (d == 0) return false; // The combination c is a sequence of six non-zero decimal digits.
      ++f[d];
      if (f[d] < 1) return false; // Your mother recalls that she heard your uncle mention that all the digits are different.
      if (v%d != 0) return false; // You remember that your uncle once said that the six digit number was divisible by each of its individual digits.
      rem /= 10; // remove the last digit
    }
    return true;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int L = sc.nextInt(), H = sc.nextInt();
    int ans = 1;
    for (int c = L; c <= H; ++c) // O(H*1) = O(H)
      if (ok(c)) // we analyze this first -> O(1)
        ++ans; // 6 digits, only uses digit 1..9, each digit is only used once, and c is divisible by all its digits
    System.out.println(ans);
  }
}
