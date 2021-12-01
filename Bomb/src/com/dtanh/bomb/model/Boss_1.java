package com.dtanh.bomb.model;

import javax.swing.*;
import java.awt.*;

public class Boss_1 extends Boss{
    public final Image[] BOSS ={
            new ImageIcon(getClass().getResource("/res/drawable/images/image/oneal_left1.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/oneal_right1.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/oneal_left2.png")).getImage(),
            new ImageIcon(getClass().getResource("/res/drawable/images/image/oneal_right3.png")).getImage(),
    };
    //    public final Image[] BOSS ={
//            new ImageIcon(getClass().getResource("/res/drawable/images/boss_left.png")).getImage(),
//            new ImageIcon(getClass().getResource("/res/drawable/images/boss_right.png")).getImage(),
//            new ImageIcon(getClass().getResource("/res/drawable/images/boss_up.png")).getImage(),
//            new ImageIcon(getClass().getResource("/res/drawable/images/boss_down.png")).getImage(),
//    };oneal_left1.png

    public Boss_1(int x, int y, int orient) {
        super(x, y, orient);
    }

    @Override
    public void creatOrient() {
        int percent = random.nextInt(100);
        if (percent > 95) {
            int newOrient = random.nextInt(4);
            changeOrient(newOrient);
            image = BOSS[newOrient];
        }
    }
}
