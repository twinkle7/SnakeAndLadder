package snakeandladder;

public class Player {
    String id;
    String name;
    int currentPosition;

    public Player(String id, String name, int currentPosition) {
        this.id = id;
        this.name = name;
        this.currentPosition = currentPosition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}
