package util;

public class Position {
    private int x, y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void translate(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void translate(Position delta) {
        this.x += delta.x;
        this.y += delta.y;
    }

    public static boolean equals(Position p1, Position p2) {
        return p1.x == p2.x && p1.y == p2.y;
    }

}
