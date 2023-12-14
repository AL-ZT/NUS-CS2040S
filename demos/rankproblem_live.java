// TWO bugs added in

import java.util.*;
import java.io.*;

public class rankproblem_live {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    // Scanner sc = new Scanner(new File("in.txt"));
    int numTeams = sc.nextInt(), matches = sc.nextInt(); sc.nextLine();

    // ArrayList<Integer> A = new ArrayList<>();
    ProfHalimListArray<Integer> A = new ProfHalimListArray<>();
    for (int i = 1; i <= numTeams; ++i)
      A.add(i-1, i); // insert 1, 2, ..., numTeams

    // for (int i = 0; i < matches; ++i) { // repeat matches times
    while (matches-- > 0) { // same thing
      String line = sc.nextLine();
      String[] token = line.split(" ");

      int i = Integer.parseInt(token[0].substring(1));
      int j = Integer.parseInt(token[1].substring(1));

      int n = A.indexOf(i);
      int m = A.indexOf(j);

      if (n < m) { // need to shuffle positions
        A.remove(m); // remove team j from index m, everybody behind moves +1 rank
        A.add(n, i); // slot team j at this position (behind team i)
      }

      // System.out.println("match between " + i + " and " + j);
      // for (int x = 0; x < numTeams; ++x)
      //   System.out.print("T"+A.get(x) + " ");
      // System.out.println();
    }

    // for (var Ai : A)
    //   System.out.print("T"+Ai + " ");
    A.printList();
  }
}



class ProfHalimListArray<T> {
  private int N;
  private Object[] A = new Object[100]; // n <= 100, so [0..99]

  public ProfHalimListArray() {
    N = 0;
  }

  @SuppressWarnings("unchecked")
  public T get(int i) {
    return (T) A[i];
  }

  public int indexOf(T v) {
    for (int i = 0; i < N; ++i)
      if (A[i].equals(v))
        return i;
    return -1;
  }

  public void add(int i, T v) {
    if (N == 100 || i < 0 || i > N)
      return;

    for (int j = N-1; j >= i; --j)
      A[j+1] = A[j];
    A[i] = v;
    ++N;
  }

  public void remove(int i) {
    for (int j = i; j < N-1; ++j)
      A[j] = A[j+1];
    --N;
  }

  public void printList() {
    for (int i = 0; i < N; ++i)
      System.out.print((i > 0 ? " T" : "T") + A[i]);
    System.out.println();
  }
}
