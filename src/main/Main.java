package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import dao.ReadFile;
import snakeandladder.MovementStrategy;
import snakeandladder.Player;
import snakeandladder.SnakeAndLadder;


public class Main {
    public static void main(String[] args) {
        ReadFile readFileObject = new ReadFile();
        List<String> lines = readFileObject.readFile();

        int boardSize=10, S, L, N, D=1;
        MovementStrategy strategy = MovementStrategy.SUM;
        int snakes[][], ladders[][];
        List<Player> players = new ArrayList<>();

        try {
            int i=0;
            S = Integer.parseInt(lines.get(i++));
            snakes = new int[S][2];
            for (int j=i; j<i+S; j++) {
                snakes[j-i] = Arrays.stream(lines.get(j).split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            i += S;
            L = Integer.parseInt(lines.get(i++));
            ladders = new int[L][2];
            for (int j=i; j<i+L; j++) {
                ladders[j-i] = Arrays.stream(lines.get(j).split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            i += L;
            N = Integer.parseInt(lines.get(i++));
            for (int j=i; j<i+N; j++) {
                String[] playerDetails = lines.get(j).split(" ");
                Player player = new Player(String.valueOf(j-i), playerDetails[0], Integer.parseInt(playerDetails[1]));
                players.add(player);
            }

            i += N;
            if (i < lines.size()) {
                D = Integer.parseInt(lines.get(i++));
            }

            if (i < lines.size()) {
                String strategyInput = lines.get(i++);
                strategy = strategyInput.equalsIgnoreCase("MAX") ? MovementStrategy.MAX : (strategyInput.equalsIgnoreCase("MIN")
                    ? MovementStrategy.MIN : MovementStrategy.SUM);
            }

            SnakeAndLadder snakeAndLadder = new SnakeAndLadder(boardSize, snakes, ladders, players, D, strategy);
            //        SnakeAndLadder snakeAndLadder = new SnakeAndLadder();
            snakeAndLadder.startGame();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

    }
}