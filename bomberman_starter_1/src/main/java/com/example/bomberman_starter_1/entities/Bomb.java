package com.example.bomberman_starter_1.entities;

import com.example.bomberman_starter_1.GameManager;
import com.example.bomberman_starter_1.graphics.Sprite;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Bomb extends Entity {
    public long time_set;
    public long time_explosive = 5000;

    public Bomb(int xUnit, int yUnit, Image img, GameManager game) {
        super(xUnit, yUnit, img, game);
        time_set = System.currentTimeMillis();
    }

    @Override
    public void update(KeyCode keyCode) {
        if (System.currentTimeMillis() - time_set > time_explosive) {
             game.entities.removeIf(o -> (o instanceof Bomb) && o.x == this.x && o.y == this.y);
             game.entities.add(new Flame(x / GameManager.CELL_SIZE, y / GameManager.CELL_SIZE, Sprite.bomb_exploded.getFxImage(), game));
             
        }
    }

    @Override
    public String type() {
        return "Bomb";
    }
}
