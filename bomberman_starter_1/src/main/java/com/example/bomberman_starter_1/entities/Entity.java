package com.example.bomberman_starter_1.entities;

import com.example.bomberman_starter_1.GameManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public abstract class Entity {
    //Tọa độ thực X tính từ góc trái trên trong Canvas
    public int x;

    //Tọa độ thực Y tính từ góc trái trên trong Canvas
    public int y;

    public Image img;
    public GameManager game;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img, GameManager game) {
        this.x = xUnit * game.CELL_SIZE;
        this.y = yUnit * game.CELL_SIZE;
        this.img = img;
        this.game = game;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update(KeyCode keyCode);

    public abstract String type();
}
