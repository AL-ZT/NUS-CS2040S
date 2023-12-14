// TWO subtle-bugs inside...

import java.util.*;

class delimitersoup {
  public static boolean isOK(char open, char close) {
    if ((open == '(' && close == ')') ||
        (open == '[' && close == ']') ||
        (open == '{' && close == '}'))
      return true;
    return false;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt(); sc.nextLine();
    String L = sc.nextLine();
    Stack<Character> S = new Stack<>();
    String open = "([}";
    for (int i = 0; i < n; ++i) {
      char Li = L.charAt(i);
// System.out.println("index i = " + i + ", Li = " + Li);
      if (Li == ' ') // space, ignore
        continue;
      else if (open.indexOf(Li) != -1)
        S.push(Li);
      else {
        if (S.isEmpty() || !isOK(S.peek(), Li)) {
          System.out.println(Li + " " + i);
          System.exit(0);
        }
        else
          S.pop();
      }
    }
    System.out.println("ok so far!");
  }
}
