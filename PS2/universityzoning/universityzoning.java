import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class universityzoning {

    public static class Pair {
        public int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public static long manhattanDist(Pair g, Student s) {
            return Math.abs(g.x - s.coords.x) + Math.abs(g.y - s.coords.y);
        }
        
        public static void order(List<Pair> grids) {
            Collections.sort(grids, new Comparator<Pair>() {
                
                public int compare(Pair g1, Pair g2) {
                    Integer x1 = g1.x;
                    Integer x2 = g2.x;
                    int comp = x1.compareTo(x2);
        
                    if (comp != 0) {
                       return comp;
                    } 
        
                    Integer y1 = g1.y;
                    Integer y2 = g2.y;
                    return y1.compareTo(y2);
                }
            });
        }
    }
    
    public static class Student {
        public int studentId;
        public int facultyId;
        public Pair coords;

        public Student(int id, int f, Pair p) {
            this.studentId = id;
            this.facultyId = f;
            this.coords = p;
        }
        
        public static void order(List<Student> students) { // studentId ascending order
            Collections.sort(students, new Comparator<Student>() {
        
                public int compare(Student s1, Student s2) {
        
                    Integer x1 = s1.studentId;
                    Integer x2 = s2.studentId;

                    return x1.compareTo(x2);
                }
            });
        }
    }

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] token = br.readLine().split(" "); // R, C, F, S, G

        int F = Integer.parseInt(token[2]);
        int S = Integer.parseInt(token[3]);
        int G = Integer.parseInt(token[4]);

        int[] count = new int[F];
        ArrayList<ArrayList<Long>> facDist = new ArrayList<>();
        ArrayList<ArrayList<Pair>> zones = new ArrayList<>();
        long[] facultyCompliance = new long[F];
        
        // Grid Assignment for F faculties
        for (int i = 0; i < F; i++) {
            count[i] = 0;
            ArrayList<Pair> zone = new ArrayList<>();
            facDist.add(new ArrayList<>());

            String[] grids = br.readLine().split(" ");
            int c = 1;

            for (int j = 0; j < Integer.parseInt(grids[0]); j++) {
                zone.add(new Pair(Integer.parseInt(grids[c]), Integer.parseInt(grids[c+1])));
                c += 2;
            }
            Pair.order(zone);
            zones.add(zone);
        }

        // Student locations
        ArrayList<Student> students = new ArrayList<>(); // Student List
        for (int i = 0; i < S; i++) {
            String[] s = br.readLine().split(" "); // r, c, id, fac

            students.add(new Student(Integer.parseInt(s[2]),
                                     Integer.parseInt(s[3]), 
                                     new Pair(Integer.parseInt(s[0]), Integer.parseInt(s[1]))
                                     ));
        }
        Student.order(students);
        
        // Calculate distance between assigned grid and student
        for (int j = 0; j < students.size(); j++) {
            Student s = students.get(j);
            int sFac = s.facultyId;
            facDist.get(sFac - 1).add(
                Pair.manhattanDist(zones.get(sFac - 1).get(count[sFac - 1]), s)
            );
            count[sFac - 1] += 1;
        }
        
        // Loops thru each faculty and calculate minDist needed to meet T students
        String[] t = br.readLine().split(" ");
        for (int i = 0; i < F; i++) {
            long minDist = 0;

            ArrayList<Long> d = facDist.get(i);
            Collections.sort(d);

            for (int j = 0; j < Integer.parseInt(t[i]); j++) {
                minDist += d.get(j);
            }
            facultyCompliance[i] = minDist;
        }

        // Calculate total steps required to fulfill >= G faculties
        long totalDist = 0;
        Arrays.sort(facultyCompliance);
        for (int i = 0; i < G; i++) {
            totalDist += facultyCompliance[i];
        }

        System.out.println(totalDist);
    }
}
