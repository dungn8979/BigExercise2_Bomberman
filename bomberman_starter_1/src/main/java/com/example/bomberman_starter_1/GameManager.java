package com.example.bomberman_starter_1;

import com.example.bomberman_starter_1.entities.Bomber;
import com.example.bomberman_starter_1.entities.Entity;
import com.example.bomberman_starter_1.entities.Wall;
import com.example.bomberman_starter_1.graphics.Sprite;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class GameManager {
    public static final char WALL = '#';
    public static final char GRASS = ' ';
    public static final char BRICK = '*';
    public static final char BOMB = '+';
    public static final char FLAME = '-';
    public static final char PORTAL = 'x';
    public static final char BOMBER = 'p';
    public static final char BALLOON = '1';
    public static final char ONEAL = '2';
    public static final char BOMB_ITEM = 'b';
    public static final char FLAME_ITEM = 'f';
    public static final char SPEED_ITEM = 's';
    public static final int CELL_SIZE = Sprite.SCALED_SIZE;
    public int levels;
    public int height;
    public int width;
    private String file_path = "/levels/Level1.txt";
    public String[][] map;
    public List<Entity> entities = new ArrayList<>();
    public List<Entity> stillObjects = new ArrayList<>();
    public Bomber bomber = null;
    public int slippery = 5;
    public int bomb_num = 1;
    public int flame_length = 1;

    public GameManager() throws IOException {
        InputStream inputStream = GameManager.class.getResourceAsStream(file_path);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader b_reader = new BufferedReader(streamReader);

        String[] property_map = b_reader.readLine().split("\\\s");
        levels = Integer.parseInt(property_map[0]);
        height = Integer.parseInt(property_map[1]);
        width = Integer.parseInt(property_map[2]);
        map = new String[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x == 0 || x == width - 1 || y == 0 || y == height - 1)
                    map[x][y] = String.valueOf(GameManager.WALL);
                else
                    map[x][y] = String.valueOf(GameManager.GRASS);
            }
        }

        for (int y = 0; y < height; y++) {
            String buff = b_reader.readLine();
            for (int x = 0; x < width; x++) {
                if (buff.charAt(x) != GameManager.WALL && buff.charAt(x) != GameManager.GRASS)
                    map[x][y] += String.valueOf(buff.charAt(x));
                else
                    map[x][y] = String.valueOf(buff.charAt(x));
            }
        }


        // cách này không dùng được với path có kí tự đặc biệt hoặc có dấu cách
//        URL url1 = ClassLoaderUtil.getResource("test.csv", GameManager.class);
//        Path path = Paths.get(url1.toURI());
//        URL url = GameManager.class.getResource(file_path);
//        System.out.println(GameManager.class.getResource(file_path).getPath().substring(1));

//        FileReader f_read = new FileReader(file_path);
//        BufferedReader b_reader = new BufferedReader(f_read);

    }

    public boolean isOverlap(Entity e1, Entity e2) {
//         ++
//        --+
//        --
        if (e2.x < e1.x + CELL_SIZE - slippery && e2.x > e1.x
        && e2.y + CELL_SIZE - slippery < e1.y + CELL_SIZE - slippery && e2.y + CELL_SIZE - slippery > e1.y)
            return true;
//        ++
//        +--
//         --
        else if (e2.x + CELL_SIZE - slippery < e1.x + CELL_SIZE - slippery && e2.x + CELL_SIZE - slippery > e1.x
        && e2.y + CELL_SIZE - slippery > e1.y && e2.y + CELL_SIZE - slippery < e1.y + CELL_SIZE - slippery)
            return true;
//         --
//        +--
//        ++
        else if (e2.x + CELL_SIZE - slippery > e1.x && e2.x + CELL_SIZE - slippery < e1.x + CELL_SIZE - slippery
        && e2.y > e1.y && e2.y < e1.y + CELL_SIZE - slippery)
            return true;
//        --
//        -++
//         ++
        else if (e2.x > e1.x && e2.x < e1.x + CELL_SIZE - slippery
        && e2.y > e1.y && e2.y < e1.y + CELL_SIZE - slippery)
            return true;
//        ++
//        --
//        --
        else if (e2.x == e1.x
        && e2.y + CELL_SIZE - slippery > e1.y && e2.y + CELL_SIZE - slippery < e1.y + CELL_SIZE - slippery)
            return true;
//        +--
//        +--
        else if (e2.x + CELL_SIZE - slippery > e1.x && e2.x + CELL_SIZE - slippery < e1.x + CELL_SIZE - slippery
        && e2.y == e1.y)
            return true;
//        --
//        --
//        ++
        else if (e2.x == e1.x
        && e2.y > e1.y && e2.y < e1.y + CELL_SIZE - slippery)
            return true;
//        --+
//        --+
        else if (e2.x > e1.x && e2.x < e1.x + CELL_SIZE - slippery
        && e2.y == e1.y)
            return true;
//        --
//        --
        else if (e2.x == e1.x
        && e2.y == e1.y)
            return true;
        return false;
    }
}
