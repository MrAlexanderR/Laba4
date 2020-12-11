package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Masks {

    public static void kontur(File image, File output, String First, String Second) {
        BufferedImage sourceImage = null;
        try {
            sourceImage = ImageIO.read(image);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        int Width = sourceImage.getWidth();
        int Height = sourceImage.getHeight();
        BufferedImage outputPicture = new BufferedImage(Width, Height, sourceImage.getType());
        int GxRed = 0;
        int GyRed = 0;
        int GxGreen = 0;
        int GyGreen = 0;
        int GxBlue = 0;
        int GyBlue = 0;
        int Red = 0;
        int Green = 0;
        int Blue = 0;
        int Hui = 0;
        int Gx = 0;
        int Gy = 0;
        int[][] Mx = new int[3][3];
        int[][] My = new int[3][3];
        switch (First) {
            case "C": {
                Mx = new int[][]{
                        { 1,  1,  1},
                        { 1, -2,  1},
                        {-1, -1, -1}
                };
            }
            break;
            case "С-В": {
                Mx = new int[][]{
                        {1,  1,  1},
                        {-1, -2, 1},
                        {-1, -1, 1}
                };
            }
            break;
            case "В": {
                Mx = new int[][]{
                        {-1,  1, 1},
                        {-1, -2, 1},
                        {-1,  1, 1}
                };
            }
            break;
            case "Ю-В": {
                Mx = new int[][]{
                        {-1, -1, 1},
                        {-1, -2, 1},
                        { 1,  1, 1}
                };
            }
            break;
            case "Ю": {
                Mx = new int[][]{
                        {-1, -1, -1},
                        { 1, -2,  1},
                        { 1,  1,  1}
                };
            }
            break;
            case "Ю-З": {
                Mx = new int[][]{
                        {1, -1, -1},
                        {1, -2, -1},
                        {1,  1,  1}
                };
            }
            break;
            case "З": {
                Mx = new int[][]{
                        {1,  1, -1},
                        {1, -2, -1},
                        {1,  1, -1}
                };
            }
            break;
            case "С-З": {
                Mx = new int[][]{
                        {1,  1,  1},
                        {1, -2, -1},
                        {1, -1, -1}
                };
            }
            break;
        }

        switch (Second) {
            case "C": {
                My = new int[][]{
                        { 1,  1,  1},
                        { 1, -2,  1},
                        {-1, -1, -1}
                };
            }
            break;
            case "С-В": {
                My = new int[][]{
                        {1,  1,  1},
                        {-1, -2, 1},
                        {-1, -1, 1}
                };
            }
            break;
            case "В": {
                My = new int[][]{
                        {-1,  1, 1},
                        {-1, -2, 1},
                        {-1,  1, 1}
                };
            }
            break;
            case "Ю-В": {
                My = new int[][]{
                        {-1, -1, 1},
                        {-1, -2, 1},
                        { 1,  1, 1}
                };
            }
            break;
            case "Ю": {
                My = new int[][]{
                        {-1, -1, -1},
                        { 1, -2,  1},
                        { 1,  1,  1}
                };
            }
            break;
            case "Ю-З": {
                My = new int[][]{
                        {1, -1, -1},
                        {1, -2, -1},
                        {1,  1,  1}
                };
            }
            break;
            case "З": {
                My = new int[][]{
                        {1,  1, -1},
                        {1, -2, -1},
                        {1,  1, -1}
                };
            }
            break;
            case "С-З": {
                My = new int[][]{
                        {1,  1,  1},
                        {1, -2, -1},
                        {1, -1, -1}
                };
            }
            break;
        }

        for (int i = 1; i < Height - 1; i++) {
            for (int j = 1; j < Width - 1; j++) {
                for (int col = i - 1; col < i + 1; col++) {
                    for (int row = j - 1; row < j + 1; row++) {
                        Color color = new Color(sourceImage.getRGB(row, col));
                        GxRed += color.getRed() * Mx[col - i + 1][row - j + 1];
                        GxGreen += color.getGreen() * Mx[col - i + 1][row - j + 1];
                        GxBlue += color.getBlue() * Mx[col - i + 1][row - j + 1];
                        GyRed += color.getRed() * My[col - i + 1][row - j + 1];
                        GyGreen += color.getGreen() * My[col - i + 1][row - j + 1];
                        GyBlue += color.getBlue() * My[col - i + 1][row - j + 1];
                    }
                }
                Gx = GxBlue + GxRed + GxGreen;
                Gy = GyBlue + GyRed + GyGreen;

                int pisxel = 0;
                pisxel = (int) Math.sqrt(Math.pow(Gx, 2) + Math.pow(Gy, 2));
                if (pisxel > 255) {
                    Red = 255;
                    Green = 255;
                    Blue = 255;
                }
                if (pisxel < 0) {
                    Red = 0;
                    Green = 0;
                    Blue = 0;
                }
                Color color2 = new Color(Red, Green, Blue, Hui);
                outputPicture.setRGB(j, i, color2.getRGB());
                Red = 0;
                Green = 0;
                Blue = 0;
                GxRed = 0;
                GxGreen = 0;
                GxBlue = 0;
                GyRed = 0;
                GyGreen = 0;
                GyBlue = 0;
            }
        }
        try {
            ImageIO.write(outputPicture, "bmp", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}