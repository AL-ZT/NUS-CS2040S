// do subtask 1 FIRST (for both tasks)
import java.util.*;
import java.io.*;

public class template {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        String[] token = br.readLine().split(" ");
        int N = Integer.parseInt(token[0]);
        int K = Integer.parseInt(token[1]);
        long B = Long.parseLong(token[2]);
        ArrayList<Integer> list = new ArrayList<>();
        pw.println(N + " " + K + " " + B);

        token = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(token[i]));
        }
        pw.println(list);

        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < N; j++) {
                int L = j;
                int R = j + i - 1;
                if (R > N - 1) {
                    break;
                }
                int total = 0;
                for (int k = L; k <= R; k++) {
                    total += list.get(k);
                }
                int beauty = total - ((R - L + 1) * K);
                if (beauty == B) {
                    pw.println(L + " " + R);
                    System.exit(0);
                }
            }
        }
        pw.println(-1);

        pw.close();
    }
}