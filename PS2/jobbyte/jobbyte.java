import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.io.*;
import java.util.*;

public class jobbyte {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        int count = 0;
        int N = sc.nextInt();
        sc.nextLine();
        int[] jobList = Stream.of(sc.nextLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
                                
 
        HashMap<Integer, Integer> jobPositions = new HashMap<>();
        for (int i = 0; i < jobList.length; i++) {
            jobPositions.put(jobList[i], i);
        }
        
        Arrays.sort(jobList);
    
        boolean[] visited = new boolean[jobList.length];
        Arrays.fill(visited, false);
 
        for (int i = 0; i < jobList.length; i++) {
            if (visited[i] || jobPositions.get(jobList[i]) == i) {
                continue;
            }

            int cycles = 0; int j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = jobPositions.get(jobList[j]);
                cycles++;
            }
 
            if (cycles > 0) {
                count += (cycles - 1);
            }
        }
        
        System.out.println(count);
    }
}