package com.example.bomberman_starter_1;

import com.example.bomberman_starter_1.entities.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import com.example.bomberman_starter_1.graphics.Sprite;


import java.io.IOException;
import java.net.URISyntaxException;

public class BombermanGame extends Application {
    public GameManager game = new GameManager();
    public int width = game.width;
    public int height = game.height;
    private GraphicsContext gc;
    private Canvas canvas;

    public BombermanGame() throws IOException, URISyntaxException {
    }

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * width - 600, Sprite.SCALED_SIZE * height - 100);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        scene.setOnKeyPressed(k -> keyEvent(k));
        createMap();
    }

    public void keyEvent(KeyEvent k) {
        if (k.getCode().equals(KeyCode.UP) || k.getCode().equals(KeyCode.RIGHT)
                || k.getCode().equals(KeyCode.LEFT) || k.getCode().equals(KeyCode.DOWN)) {
            game.entities.forEach(item -> {
                if (item instanceof Bomber)
                    item.update(k.getCode());
            });
        } else if (k.getCode().equals(KeyCode.SPACE) && game.bomber != null) {
            int xUnit = game.bomber.x / GameManager.CELL_SIZE;
            int yUnit = game.bomber.y / GameManager.CELL_SIZE;
            for (Entity item: game.entities) {
                if ((!(item instanceof Bomber) && item.x == xUnit * GameManager.CELL_SIZE && item.y == yUnit * GameManager.CELL_SIZE) || item instanceof Wall)
                    return;
            }

            game.entities.add(0, new Bomb(game.bomber.x / GameManager.CELL_SIZE, game.bomber.y / GameManager.CELL_SIZE, Sprite.bomb.getFxImage(), game));
         }
    }

    public void createMap() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (game.map[x][y].charAt(0) == GameManager.WALL) {
                    game.stillObjects.add(new Wall(x, y, Sprite.wall.getFxImage(), game));
                }
                else {
                    game.stillObjects.add(new Grass(x, y, Sprite.grass.getFxImage(), game));
                }
                if (game.map[x][y].length() > 1) {
                    if (game.map[x][y].charAt(1) == GameManager.WALL) {
                        game.entities.add(new Wall(x, y, Sprite.wall.getFxImage(), game));
                    }
                    if (game.map[x][y].charAt(1) == GameManager.BOMBER) {
                        game.bomber = new Bomber(1, 1, Sprite.player_right.getFxImage(), game);
                        game.entities.add(game.bomber);
                    }
                    if (game.map[x][y].charAt(1) == GameManager.BALLOON) {
                        game.entities.add(new Balloon(x, y, Sprite.balloom_left1.getFxImage(), game));
                    }
                    if (game.map[x][y].charAt(1) == GameManager.ONEAL) {
                        game.entities.add(new Oneal(x, y, Sprite.oneal_left1.getFxImage(), game));
                    }
                    if (game.map[x][y].charAt(1) == GameManager.BOMB) {
                        game.entities.add(new Bomb(x, y, Sprite.bomb.getFxImage(), game));
                    }
                    if (game.map[x][y].charAt(1) == GameManager.BRICK) {
                        game.entities.add(new Brick(x, y, Sprite.brick.getFxImage(), game));
                    }
                    if (game.map[x][y].charAt(1) == GameManager.PORTAL) {
                        game.entities.add(0, new Portal(x, y, Sprite.portal.getFxImage(), game));
                    }
                    if (game.map[x][y].charAt(1) == GameManager.FLAME_ITEM) {
                        game.entities.add(0, new FlameItem(x, y, Sprite.powerup_flames.getFxImage(), game));
                    }
                }
            }
        }
    }

    public void update() {
        game.entities.forEach(item -> item.update(null));
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        game.stillObjects.forEach(g -> g.render(gc));
        game.entities.forEach(g -> g.render(gc));
    }

    public void printMap() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.printf("%c", game.map[x][y].charAt(0));
            }
            System.out.println();
        }
        System.out.println();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (game.map[x][y].length() > 1) {
                    System.out.printf("%c", game.map[x][y].charAt(1));
                } else System.out.printf(" ");
            }
            System.out.println();
        }
        System.out.println();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (game.map[x][y].length() > 2) {
                    System.out.printf("%c", game.map[x][y].charAt(2));
                } else System.out.printf(" ");
            }
            System.out.println();
        }
    }
}
