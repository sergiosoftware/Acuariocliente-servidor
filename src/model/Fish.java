/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toshiba
 */
public class Fish extends img{
    private Thread T;
    private int direc;

    public Fish(int x, int y, int width, int height, String Source, int dir) {
        super(x, y, width, height, Source);
        this.direc = dir;
     }


    /**
     * @return the direc
     */
    public int getDirec() {
        return direc;
    }

    /**
     * @param direc the direc to set
     */
    public void setDirec(int direc) {
        this.direc = direc;
    }
}
