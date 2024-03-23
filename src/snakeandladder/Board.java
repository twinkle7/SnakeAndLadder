package snakeandladder;

import java.util.concurrent.ThreadLocalRandom;


public class Board {
        int boardSize;
        Cell[][] cells;

        Board(int boardSize, int snakes[][], int ladders[][]) {
            this.boardSize = boardSize;
            initializeCells();
            addSnakesLadders(snakes, ladders);
        }
        Board(int boardSize, int numberOfSnakes, int numberOfLadders) {
            this.boardSize = boardSize;
            initializeCells();
            // For randomly generating Snakes and Ladders points
            addSnakesLadders(cells, numberOfSnakes, numberOfLadders);
        }

        private void initializeCells() {
            cells = new Cell[boardSize][boardSize];

            for(int i=0;i<boardSize;i++) {
                for(int j=0; j<boardSize;j++) {
                    Cell cellObj = new Cell();
                    cells[i][j] = cellObj;
                }
            }
        }

        void addSnakesLadders(int[][] snakeJumps, int[][] ladderJumps) {
            for (int[] jump: snakeJumps) {
                Jump snakeObj = new Jump();
                snakeObj.start = jump[0];
                snakeObj.end = jump[1];

                Cell cell = getCell(jump[0]);
                cell.jump = snakeObj;
            }

            for (int[] jump: ladderJumps) {
                Jump ladderObj = new Jump();
                ladderObj.start = jump[0];
                ladderObj.end = jump[1];

                Cell cell = getCell(jump[0]);
                cell.jump = ladderObj;
            }
        }

        // Function to generate snakes and ladders random start and ending points programmatically
        private void addSnakesLadders(Cell[][] cells, int numberOfSnakes, int numberOfLadders){
            while(numberOfSnakes > 0) {
                int snakeHead = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length);
                int snakeTail = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length);
                if(snakeTail >= snakeHead) {
                    continue;
                }

                Jump snakeObj = new Jump();
                snakeObj.start = snakeHead;
                snakeObj.end = snakeTail;

                Cell cell = getCell(snakeHead);
                cell.jump = snakeObj;

                numberOfSnakes--;
            }

            while(numberOfLadders > 0) {
                int ladderStart = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length);
                int ladderEnd = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length);
                if(ladderStart >= ladderEnd) {
                    continue;
                }

                Jump ladderObj = new Jump();
                ladderObj.start = ladderStart;
                ladderObj.end = ladderEnd;

                Cell cell = getCell(ladderStart);
                cell.jump = ladderObj;

                numberOfLadders--;
            }

        }

        Cell getCell(int playerPosition) {
            int boardRow = (playerPosition-1) / cells.length;
            int boardColumn = (playerPosition-1) % cells.length;
            if (boardRow >=0 && boardRow <= cells.length && boardColumn >= 0 && boardColumn <= cells.length)
                return cells[boardRow][boardColumn];
            return null;
        }
}
