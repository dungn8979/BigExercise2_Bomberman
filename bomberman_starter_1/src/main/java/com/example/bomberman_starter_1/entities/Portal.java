package com.example.bomberman_starter_1.entities;

import com.example.bomberman_starter_1.GameManager;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Portal extends Entity {

    public Portal(int xUnit, int yUnit, Image img, GameManager gameManager) {
        super(xUnit, yUnit, img, gameManager);
    }

    @Override
    public void update(KeyCode keyCode) {

    }

    @Override
    public String type() {
        return "Portal";
    }
}
