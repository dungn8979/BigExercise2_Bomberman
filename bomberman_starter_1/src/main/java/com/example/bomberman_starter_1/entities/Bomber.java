package com.example.bomberman_starter_1.entities;

import com.example.bomberman_starter_1.GameManager;
import com.example.bomberman_starter_1.graphics.Sprite;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Bomber extends Entity {
    public int speed = 5;
    public Bomber(int xUnit, int yUnit, Image img, GameManager gameManager) {
        super(xUnit, yUnit, img, gameManager);
    }

    @Override
    public void update(KeyCode keyCode) {
        if (keyCode != null) {
            if (keyCode.equals(KeyCode.RIGHT)) {
                if (canStay(x + speed, y)) {
                    x += speed;
                } else {
//                    x = (x + speed) / GameManager.CELL_SIZE * GameManager.CELL_SIZE;
                }
            }
            else if (keyCode.equals(KeyCode.LEFT)) {
                if (canStay(x - speed, y)) {
                    x -= speed;
                } else {
//                    x = x / GameManager.CELL_SIZE * GameManager.CELL_SIZE;
                }
            }
            else if (keyCode.equals(KeyCode.UP)) {
                if (canStay(x, y - speed)) {
                    y -= speed;
                } else {
//                    y = y / GameManager.CELL_SIZE * GameManager.CELL_SIZE;
                }
            }
            else if (keyCode.equals(KeyCode.DOWN)) {
                if (canStay(x, y + speed)) {
                    y += speed;
                } else {
//                    y = (y + speed) / GameManager.CELL_SIZE * GameManager.CELL_SIZE;
                }
            }
            printCoordinates(x, y);
        }
    }

    private boolean canStay(int x, int y) {
        Bomber bomberFuture = new Bomber(0,0,null,game);
        bomberFuture.x = x;
        bomberFuture.y = y;

        for (Entity item: game.stillObjects) {
            if (item instanceof Wall && game.isOverlap(bomberFuture, item)) {
                System.out.println(item.type() + " :");
                Bomber.printCoordinates(item.x, item.y);
                return false;
            }
        }
        for (Entity item: game.entities) {
            if (item instanceof Brick && game.isOverlap(bomberFuture,item)) {
                System.out.println(item.type() + " :");
                Bomber.printCoordinates(item.x, item.y);
                return false;
            }
            else if (item instanceof Bomb && game.isOverlap(bomberFuture,item) && !game.isOverlap(this, item)) {
                return false;
            }
        }
        return true;
    }

    public static void printCoordinates(int x, int y) {
        System.out.println("(" + x + "," + y + ")");
    }


    @Override
    public String type() {
        return "Bomber";
    }

//    private void test_canMove() {
//        for (int y = 0; y < game.height; y++) {
//            for (int x = 0; x < game.width; x++) {
//                if (canStay(x, y))
//                    System.out.print('T');
//                else
//                    System.out.print('F');
//                System.out.print(' ');
//            }
//            System.out.println();
//        }
//    }
//
//    private boolean canStay(int xUnit, int yUnit) {
//        if (xUnit > -1 && xUnit < game.width && yUnit > -1 && yUnit < game.height) {
//            if (game.map[xUnit][yUnit].length() == 1) {
//                return game.map[xUnit][yUnit].charAt(0) != GameManager.WALL;
//            }
//            else if (game.map[xUnit][yUnit].length() > 1) {
//                return game.map[xUnit][yUnit].charAt(1) != GameManager.BRICK
//                        && game.map[xUnit][yUnit].charAt(1) != GameManager.BOMB;
//            }
//        }
//        return false;
//    }
//
//    private boolean check_distance_1(int x_small, int x_big) {
//        return ((x_big - x_small) <= (GameManager.CELL_SIZE - speed) && (x_big - x_small) >= 0);
//    }
//
//    private boolean check_distance_2(int x_small, int x_big) {
//        return ((x_big - x_small) <= GameManager.CELL_SIZE && (x_big - x_small) > GameManager.CELL_SIZE - speed);
//    }
//
//    @Override
//    public void update(KeyCode keyCode) {
//        if (keyCode != null) {
//            if (keyCode.equals(KeyCode.RIGHT)
//                    && canStay(x_old / GameManager.CELL_SIZE + 1, y_old / GameManager.CELL_SIZE)) {
//                if (check_distance_2(x_old, x_new)) {
//                    update_map(x_old / GameManager.CELL_SIZE,
//                            x_old / GameManager.CELL_SIZE + 1,
//                            y_old / GameManager.CELL_SIZE,
//                            y_old / GameManager.CELL_SIZE);
//                    x_old += GameManager.CELL_SIZE;
//                    x_new = x_old;
//                }
//                else
//                    x_new += speed;
//            }
//            else if (keyCode.equals(KeyCode.LEFT)
//                    && canStay(x_old / GameManager.CELL_SIZE - 1, y_old / GameManager.CELL_SIZE)) {
//                if (check_distance_2(x_new, x_old)) {
//                    update_map(x_old / GameManager.CELL_SIZE,
//                            x_old / GameManager.CELL_SIZE - 1,
//                            y_old / GameManager.CELL_SIZE,
//                            y_old / GameManager.CELL_SIZE);
//                    x_old -= GameManager.CELL_SIZE;
//                    x_new = x_old;
//                }
//                else
//                    x_new -= speed;
//            }
//            else if (keyCode.equals(KeyCode.UP)
//                    && canStay(x_old / GameManager.CELL_SIZE, y_old / GameManager.CELL_SIZE - 1)) {
//                if (check_distance_2(y_new, y_old)) {
//                    update_map(x_old / GameManager.CELL_SIZE,
//                            x_old / GameManager.CELL_SIZE,
//                            y_old / GameManager.CELL_SIZE,
//                            y_old / GameManager.CELL_SIZE - 1);
//                    y_old -= GameManager.CELL_SIZE;
//                    y_new = y_old;
//                }
//                else
//                    y_new -= speed;
//            }
//            else if (keyCode.equals(KeyCode.DOWN)
//            && canStay(x_old / GameManager.CELL_SIZE, y_old / GameManager.CELL_SIZE + 1)) {
//                if (check_distance_2(y_old, y_new)) {
//                    update_map(x_old / GameManager.CELL_SIZE,
//                            x_old / GameManager.CELL_SIZE,
//                            y_old / GameManager.CELL_SIZE,
//                            y_old / GameManager.CELL_SIZE + 1);
//                    y_old += GameManager.CELL_SIZE;
//                    y_new = y_old;
//                }
//                else
//                    y_new += speed;
//            }
//            printCoordinates();
//        }
//    }
//
//    private void update_map(int xUnit_old, int xUnit_new, int yUnit_old, int yUnit_new) {
//        try {
//            String a = game.map[xUnit_old][yUnit_old];
//            game.map[xUnit_old][yUnit_old]
//                    = a.substring(0, a.indexOf(GameManager.BOMBER)) + a.substring(a.indexOf(GameManager.BOMBER) + 1);
//            String b = game.map[xUnit_new][yUnit_new];
//            game.map[xUnit_new][yUnit_new]
//                    = String.valueOf(GameManager.GRASS) + String.valueOf(GameManager.BOMBER) + b.substring(1);
//        } catch (Exception e) {
//            System.out.println("error: " + e.getMessage());
//        }
//        printMap();
//    }
//
//    public void printMap() {
//        for (int y = 0; y < game.height; y++) {
//            for (int x = 0; x < game.width; x++) {
//                if (game.map[x][y].length() > 1) {
//                    System.out.printf("%c", game.map[x][y].charAt(1));
//                } else {
//                    System.out.printf("%c", game.map[x][y].charAt(0));
//                }
//            }
//            System.out.println();
//        }
////        System.out.println();
////        for (int y = 0; y < game.height; y++) {
////            for (int x = 0; x < game.width; x++) {
////                if (game.map[x][y].length() > 1) {
////                    System.out.printf("%c", game.map[x][y].charAt(1));
////                } else System.out.printf(" ");
////            }
////            System.out.println();
////        }
////        System.out.println();
////        for (int y = 0; y < game.height; y++) {
////            for (int x = 0; x < game.width; x++) {
////                if (game.map[x][y].length() > 2) {
////                    System.out.printf("%c", game.map[x][y].charAt(2));
////                } else System.out.printf(" ");
////            }
////            System.out.println();
////        }
//    }
//
//    private void printCoordinates() {
//        System.out.println("(" + x_old + "," + y_old + ")" + " (" + x_new + "," + y_new + ")");
//    }
}
