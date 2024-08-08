package br.com.grupopipa;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.time.Duration;
import java.time.LocalTime;

public class App {

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        System.out.println(String.format("Width: %s", width));
        System.out.println(String.format("Height: %s", height));

        double blockSize = 100;

        double totalBlocksX = Math.ceil(width / blockSize);
        System.out.println(String.format("Total blocks on X: %s", totalBlocksX));

        double totalBlocksY = Math.ceil(height / blockSize);
        System.out.println(String.format("Total blocks on Y: %s", totalBlocksY));

        boolean keepGoing = true;

        double x = 0.0;
        double y = 0.0;
        double block = 0;

        LocalTime time = LocalTime.now();

        while (keepGoing) {
            /*if (MouseInfo.getPointerInfo().getLocation().getX() > x + blockSize || 
                    MouseInfo.getPointerInfo().getLocation().getX() < x - blockSize) {
                x = MouseInfo.getPointerInfo().getLocation().getX();
                System.out.println("new X: " + x);
            }

            if (MouseInfo.getPointerInfo().getLocation().getY() > y + blockSize || 
                    MouseInfo.getPointerInfo().getLocation().getY() < y - blockSize) {
                y = MouseInfo.getPointerInfo().getLocation().getY();
                System.out.println("new Y: " + y);
            }*/

            if (Math.ceil((MouseInfo.getPointerInfo().getLocation().getX() / width) * totalBlocksX) != x) {
                x = Math.ceil((MouseInfo.getPointerInfo().getLocation().getX() / width) * totalBlocksX);
                y = Math.ceil((MouseInfo.getPointerInfo().getLocation().getY() / height) * totalBlocksY);
                block = calculateBlock(y, x, totalBlocksX);
                System.out.println("new X: " + x);
                System.out.println("new Block Is: " + block);
                LocalTime newTime = LocalTime.now();
                System.out.println("Time on last block: " + Duration.between(time, newTime).toMillis());
                time = newTime;
            }

            if (Math.ceil((MouseInfo.getPointerInfo().getLocation().getY() / height) * totalBlocksY) != y) {
                y = Math.ceil((MouseInfo.getPointerInfo().getLocation().getY() / height) * totalBlocksY);
                x = Math.ceil((MouseInfo.getPointerInfo().getLocation().getX() / width) * totalBlocksX);
                block = calculateBlock(y, x, totalBlocksX);
                System.out.println("new Y: " + y);
                System.out.println("new Block Is: " + block);
                LocalTime newTime = LocalTime.now();
                System.out.println("Time on last block: " + Duration.between(time, newTime).toMillis());
                time = newTime;
            }

        }
    }

    public static double calculateBlock(double y, double x, double totalBlocksX) {
        if (y == 0) {
            return x;
        }

        return ((y - 1) * totalBlocksX) + x;
    }

}
