package com.example.bomberman_starter_1.entities;

import com.example.bomberman_starter_1.GameManager;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class FlameItem extends Entity {

    public FlameItem(int xUnit, int yUnit, Image img, GameManager gameManager) {
        super(xUnit, yUnit, img, gameManager);
    }

    @Override
    public void update(KeyCode keyCode) {

    }

    @Override
    public String type() {
        return "FlameItem";
    }
}
