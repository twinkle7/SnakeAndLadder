package snakeandladder;

import java.util.concurrent.ThreadLocalRandom;


public class Dice {

    int diceCount;
    int min = 1;
    int max =6;
    MovementStrategy strategy;

    public Dice(int diceCount, MovementStrategy strategy){
        this.diceCount = diceCount;
        this.strategy = strategy;
    }

    public int rollDice(){

        int sum=0, maxValue=Integer.MIN_VALUE, minValue=Integer.MAX_VALUE;
        int diceUsed=0;

        while(diceUsed<diceCount){
            int currentRoll = ThreadLocalRandom.current().nextInt(min,max+1);
            switch (strategy) {
                case MAX:
                    maxValue = Math.max(maxValue, currentRoll);
                    break;
                case MIN:
                    minValue = Math.min(minValue, currentRoll);
                    break;
                default:
                    sum += currentRoll;
            }
            diceUsed++;
        }

        return strategy.equals(MovementStrategy.MAX) ? maxValue : (strategy.equals(MovementStrategy.MIN) ? minValue : sum);
    }

}
