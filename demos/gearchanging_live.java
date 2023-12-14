// TWO subtle-bugs inside...

import java.util.*;

class gearchanging {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt(), M = sc.nextInt(), P = sc.nextInt();
    ArrayList<Integer> C = new ArrayList<>();
    for (int i = 0; i < N; ++i)
      C.add(sc.nextInt());
    ArrayList<Integer> D = new ArrayList<>();
    for (int j = 0; j < M; ++j)
      D.add(sc.nextInt());

    ArrayList<Double> ratios = new ArrayList<>();
    for (int i = 0; i < N; ++i)
      for (int j = 0; j < M; ++j)
        ratios.add((double)C.get(i) / D.get(j));
        // System.out.println((double)C.get(i) / D.get(j));

    Collections.sort(ratios); // sort in non-decreasing

    Boolean RideOn = true;
    for (int i = 0; i < ratios.size()-1; ++i) {
      // System.out.println((ratios.get(i+1)-ratios.get(i)) / ratios.get(i));
      if ((ratios.get(i+1)-ratios.get(i)) / ratios.get(i) < P/100.0)
        RideOn = false;
    }

    System.out.println(RideOn ? "Ride on." : "Time to change gears!");

/*

crank: 50 34
back : 11 12 13 14 16 18 20 22 25 28 32

50/11 50/12 50/13 ... 50/32 34/11 34/12 .. 34/32
4.5 4.16 ...                             1.0625
*/


  }
}
