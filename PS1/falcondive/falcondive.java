import java.util.Scanner;
import java.util.ArrayList;

public class falcondive {

    public static class Pair {
        public int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Pair findNewCoords(Pair first, Pair second) {
            return new Pair(second.x + (second.x - first.x), second.y + (second.y - first.y));
        }

        @Override
        public String toString() {
          return String.format("(%d, %d)", this.x, this.y);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] firstLine = sc.nextLine().split(" ");

        int M = Integer.parseInt(firstLine[0]), N = Integer.parseInt(firstLine[1]);
        char C = firstLine[2].charAt(1);
        char[][] ascii_3 = new char[M][N];

        ArrayList<Pair> coords_1 = new ArrayList<>();
        ArrayList<Pair> coords_2 = new ArrayList<>();

        //String newLine = System.getProperty("line.separator");

//        System.out.println("Ascii 1");
        for (int i = 0; i < M; i++) {

            char[] temp = sc.nextLine().toCharArray();

            for (int j = 0; j < N; j++) {
                if (!(temp[j] == C)) {
                    ascii_3[i][j] = temp[j];
                } else {
                    coords_1.add(new Pair(i, j));
                }
            }
        }
//        System.out.println("Coords with " + C + "=" + coords_1);

        // Empty line
        sc.nextLine();

//        System.out.println("Ascii 2");
        for (int i = 0; i < M; i++) {

            char[] temp = sc.nextLine().toCharArray();

            for (int j = 0; j < N; j++) {
                if (ascii_3[i][j] == 0) {
                    ascii_3[i][j] = temp[j];
                }

                if (temp[j] == C) {
                    coords_2.add(new Pair(i, j));
                }
            }
        }
//        System.out.println("Coords with " + C + "=" + coords_2);

//        System.out.println("Rebuilt ascii:");
        if (coords_1.size() == coords_2.size()) {
            for (int i = 0; i < coords_1.size(); i++) {

                Pair tempCoords = Pair.findNewCoords(coords_1.get(i), coords_2.get(i));

                if (tempCoords.x >= 0 || tempCoords.x < M) {
                    ascii_3[tempCoords.x][tempCoords.y] = C;
                } else if (tempCoords.y >= 0 || tempCoords.y < N) {
                    ascii_3[tempCoords.x][tempCoords.y] = C;
                }
            }
         }

        for (int i = 0; i < M; i++) {
            System.out.println(ascii_3[i]);
        }
    }
}
