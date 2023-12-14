// B first 25 points are "TRIVIAL"
import java.util.*;
import java.io.*;

class Indexes {
    int L;
    int R;

    public Indexes(int l, int r) {
        this.L = l;
        this.R = r;
    }
}

public class subarrays {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        String[] token = br.readLine().split(" ");
        int N = Integer.parseInt(token[0]);
        int K = Integer.parseInt(token[1]);
        long B = Long.parseLong(token[2]);
        ArrayList<Integer> list = new ArrayList<>();
        HashMap<Long, PriorityQueue<Indexes>> map = new HashMap<>();

        token = br.readLine().split(" ");
        for (int R = 0; R < N; R++) {
            list.add(Integer.parseInt(token[R]));
            long total = 0;
            int tmp = -1;
            for (int L = R; L >= 0; L--) {
                total += list.get((int) L);
                long beauty = total - ((R - L + 1) * K);
                if (beauty == B) {
                    tmp = L;
                }
            }
            if (tmp != -1) {
                System.out.println(tmp + " " + R);
                System.exit(0);
            }
        }
        pw.println(-1);

        pw.close();
    }
}