package snakeandladder;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


public class SnakeAndLadder {
    Board board;
    Dice dice;
    List<Player> players;
    Deque<Player> playersList = new LinkedList<>();
    Player winner;


    public SnakeAndLadder() {
        initializeGame(10);
    }

    public SnakeAndLadder(int boardSize, int snakes[][], int ladders[][], List<Player> players, int D, MovementStrategy strategy) {
        initializeGame(boardSize, snakes, ladders, players, D, strategy);
    }

    void initializeGame(int boardSize, int snakes[][], int ladders[][], List<Player> players, int D, MovementStrategy strategy) {
        board = new Board(boardSize, snakes, ladders);
        dice = new Dice(D, strategy);
        winner = null;
        // addPlayers();
        this.players = players;
        for (Player player: players) {
            this.playersList.add(player);
        }
    }

    void initializeGame(int boardSize) {
        board = new Board(boardSize, 5, 4);
        dice = new Dice(1, MovementStrategy.SUM);
        winner = null;
        addPlayers();

    }

    private void addPlayers() {
        Player player1 = new Player("p1", "A", 1);
        Player player2 = new Player("p2", "B", 1);
        playersList.add(player1);
        playersList.add(player2);
    }

    public void startGame() {

        while (winner == null) {

            //check whose turn now
            Player playerTurn = findPlayerTurn();

            //roll the dice
            int diceNumbers = dice.rollDice();

            //get the new position
            int playerExpectedPosition = playerTurn.currentPosition + diceNumbers;
            List<JumpType> jumpBy = new ArrayList<>();
            int playerNewPosition = jumpCheck(playerTurn.currentPosition, playerExpectedPosition, jumpBy);
            if (jumpBy.size()>0 && jumpBy.get(0).equals(JumpType.LADDER)) {
                System.out.println(playerTurn.getName() + " rolled a " + diceNumbers + " and climbed the ladder at "
                        + playerExpectedPosition + " and moved from " + playerTurn.currentPosition + " to " + playerNewPosition);
            } else if (jumpBy.size()>0 && jumpBy.get(0).equals(JumpType.SNAKE)) {
                System.out.println(playerTurn.getName() + " rolled a " + diceNumbers + " and bitten by snake at "
                        + playerExpectedPosition + " and moved from " + playerTurn.currentPosition + " to " + playerNewPosition);
            } else {
                System.out.println(playerTurn.getName() + " rolled a " + diceNumbers + " and moved from "
                        + playerTurn.currentPosition + " to " + playerNewPosition);
            }
            playerTurn.currentPosition = playerNewPosition;

            //check for winning condition
            if (playerNewPosition >= board.cells.length * board.cells.length) {
                winner = playerTurn;
            }

        }

        System.out.println("WINNER IS:" + winner.getName());
    }

    private Player findPlayerTurn() {

        Player playerTurns = playersList.removeFirst();
        playersList.addLast(playerTurns);
        return playerTurns;
    }

    private int jumpCheck(int currentPosition, int playerNewPosition, List<JumpType> jumpBy) {

        if (playerNewPosition == board.cells.length * board.cells.length) {
            return playerNewPosition;
        } else if (playerNewPosition > board.cells.length * board.cells.length) {
            return currentPosition;
        }

        Cell cell = board.getCell(playerNewPosition);
        if (cell.jump != null && cell.jump.start == playerNewPosition) {
            jumpBy.add((cell.jump.start < cell.jump.end) ? JumpType.LADDER : JumpType.SNAKE);
            playerNewPosition = cell.jump.end;
        }

        final int finalPlayerNewPosition = playerNewPosition;

        // If a player (A) comes to a cell where another player (B) is placed already, the previously
        // placed player (B) has to start again from 1.
        players.stream().filter(p -> p.currentPosition == finalPlayerNewPosition).forEach(p -> p.currentPosition = 1);

        return finalPlayerNewPosition;
    }
}
