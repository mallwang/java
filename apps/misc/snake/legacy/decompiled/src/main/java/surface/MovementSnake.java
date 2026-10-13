/*
 * Decompiled with CFR 0.152.
 */
package surface;

import control.Direction;
import control.SnakeCollideException;
import control.helper.Position;
import java.util.Queue;
import main.Snake;
import surface.CanvasSnake;
import surface.SurfaceSnake;

public class MovementSnake
extends Thread {
    private Position position = new Position(3, 10);
    private Direction direction = Direction.RIGHT;
    private Queue<Position> queue;
    private int score;
    private CanvasSnake canvas;

    public MovementSnake(CanvasSnake canvas, Queue<Position> queue) {
        this.queue = queue;
        this.score = 0;
        this.canvas = canvas;
        int i = 2;
        while (i >= 0) {
            queue.offer(new Position(this.position.getX() - i, this.position.getY()));
            --i;
        }
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                if (this.canvas.getNextTile() == null) {
                    this.canvas.setNextTile(this.nextTilePosition());
                }
                this.moveSnake();
                this.canvas.repaint();
                Thread.sleep(90L);
            }
            catch (SnakeCollideException ex) {
                this.canvas.gameOver();
                this.canvas.repaint();
                this.interrupt();
            }
            catch (InterruptedException interruptedException) {
                // empty catch block
            }
        }
    }

    private Position nextTilePosition() {
        int y;
        int x;
        if (this.queue.size() == 600) {
            // empty if block
        }
        while (this.queue.contains(new Position(x = (int)(Math.random() * 30.0), y = (int)(Math.random() * 20.0)))) {
        }
        return new Position(x, y);
    }

    private void moveSnake() throws SnakeCollideException {
        Position newPosition_Head = null;
        switch (this.direction) {
            case LEFT: {
                if (this.position.getX() == 0 || this.queue.contains(this.position.adjacentLeft())) {
                    throw new SnakeCollideException();
                }
                newPosition_Head = this.position.adjacentLeft();
                break;
            }
            case UP: {
                if (this.position.getY() == 0 || this.queue.contains(this.position.adjacentUp())) {
                    throw new SnakeCollideException();
                }
                newPosition_Head = this.position.adjacentUp();
                break;
            }
            case RIGHT: {
                if (this.position.getX() == 29 || this.queue.contains(this.position.adjacentRight())) {
                    throw new SnakeCollideException();
                }
                newPosition_Head = this.position.adjacentRight();
                break;
            }
            case DOWN: {
                if (this.position.getY() == 19 || this.queue.contains(this.position.adjacentDown())) {
                    throw new SnakeCollideException();
                }
                newPosition_Head = this.position.adjacentDown();
            }
        }
        boolean tileCaught = newPosition_Head.equals(this.canvas.getNextTile());
        if (!tileCaught) {
            this.queue.poll();
        }
        this.queue.offer(newPosition_Head);
        if (tileCaught) {
            this.canvas.setNextTile(this.nextTilePosition());
            this.score += 10;
            SurfaceSnake sfs = Snake.getSurf();
            String infostring = sfs.prepareInfoString(this.score, this.queue.size(), this.queue.size() - 3);
            sfs.getScoreLabel().setText(infostring);
        }
        this.position = newPosition_Head;
    }
}

