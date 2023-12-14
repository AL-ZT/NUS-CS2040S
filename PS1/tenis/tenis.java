// Lim Zheng Ting
// A0253168B
// L03
// TA: Ramapriyan

import java.util.Scanner;
import java.lang.Math;
import java.util.stream.Stream;
import java.util.stream.IntStream;

public class tenis {
    
    public static int findSmallestIndex(int[] a) {
        
        int index = 0;
        
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    index = j;
                } else if (a[i] < a[j]) {
                    index = i;
                } else {
                    index = -1;
                }
            }
        }
        
        return index;
    }
    
    public static int findLargestIndex(int[] a) {
        
        int index = 0;
        
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    index = i;
                } else if (a[i] < a[j]) {
                    index = j;
                } else {
                    index = -1;
                }
            }
        }
        
        return index;
    }
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        String[] names = sc.nextLine().split(" ");
        int matches = sc.nextInt();
        sc.nextLine();
        
        // Matches
        for (int i = 0; i < matches; i++) {
            String[] sets = sc.nextLine().split(" ");
            int[] result = {0, 0};
            boolean valid = true;
            
            if (sets.length > 3) {
                System.out.println("ne");
                continue;
            }
            
            // Sets
            for (int j = 0; j < sets.length; j++) {
                
                if (valid) {
                    // Individual scores
                    int[] games = Stream.of(sets[j].split(":"))
                                            .mapToInt(Integer::parseInt)
                                            .toArray();
                                            
                    int winner = findLargestIndex(games);
                    int loser = findSmallestIndex(games);
                                            
                    if (j == 2) {
                        if (Math.abs(games[0] - games[1]) >= 2) {
                            if (games[winner] >= 6) {
                                if (names[loser].equals("federer")) {
                                    valid = false;
                                } else {
                                    result[winner] += 1;
                                }
                            } else {
                                valid = false;
                            }
                        } else {
                            valid = false;
                        }
                    } else {
                        if (Math.abs(games[0] - games[1]) >= 2) {
                            if (games[winner] == 6 || games[winner] == 7) {
                                if (names[loser].equals("federer")) {
                                    valid = false;
                                } else {
                                    result[winner] += 1;
                                }
                            } else {
                                valid = false;
                            }
                        } else if (Math.abs(games[0] - games[1]) == 1) {
                            if (games[winner] == 7) {
                                if (names[loser].equals("federer")) {
                                    valid = false;
                                } else {
                                    result[winner] += 1;
                                }
                            } else {
                                valid = false;
                            }
                        } else {
                            valid = false;
                        }
                    }
                }
            }
            
            if (valid) {
                if (IntStream.of(result).anyMatch(x -> x == 2)) {
                    System.out.println("da");
                } else {
                    System.out.println("ne");
                }
            } else {
                System.out.println("ne");
            }
        }
    }
}
