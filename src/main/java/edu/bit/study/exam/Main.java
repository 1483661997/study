package edu.bit.study.exam;

import java.util.*;

public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt(), m = scanner.nextInt();
//
//        Set<String> blocks = new HashSet<>();
//        int height = 0, width = 0;
//        for (int i = 0; i < n; i++) {
//            int x = scanner.nextInt(), y = scanner.nextInt();
//            blocks.add(x + "," + y);
//        }
//        scanner.nextLine();
//        String[] commands = scanner.nextLine().split(" ");
//        int curX = 0, curY = 0;
//        int resX = 0, resY = 0;
//        int flag = 0;
//        for (int i = 0; i < 2 * m; i += 2) {
//            String direction = commands[i];
//            int steps = Integer.parseInt(commands[i + 1]);
//            for (int step = 0; step < steps; step++) {
//                int nextX = curX;
//                int nextY = curY;
//                switch (direction) {
//                    case "R":
//                        nextX++;
//                        break;
//                    case "L":
//                        nextX--;
//                        break;
//                    case "U":
//                        nextY++;
//                        break;
//                    case "D":
//                        nextY--;
//                        break;
//                }
//                if (blocks.contains(nextX + "," + nextY)) {
//                    flag = 1;
//                    // System.out.println("障碍物"+nextX+" "+nextY);
//                    break;
//                }
//                curY = nextY;
//                curX = nextX;
//            }
//            if(flag == 0){
//                resY = curY;
//                resX = curX;
//            }
//            // System.out.println(curX + " " + curY + " ////");
//
//        }
//        System.out.println(resX + " " + resY);
//    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = " 123";

        System.out.println(s.contains("1"));
//        int n = scanner.nextInt(), m = scanner.nextInt();
//        int[] charge = new int[n];
//        for(int i = 0; i < n; i++){
//            charge[i] = scanner.nextInt();
//        }
//        int max = 0;
//        int  sum = 0;
//        int left = 0, right = 0;
//        for(; right < n; right++){
//            sum += charge[right];
//            while (sum > m){
//                sum -= charge[left++];
//            }
//            max = Math.max(max, right-left+1);
//        }
    }
}

























