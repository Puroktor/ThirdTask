package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Main extends Canvas {
    private final static int IMAGE_HEIGHT = 800, IMAGE_WIDTH = 800;
    private final static int CANVAS_ABS_SIZE = 10;
    private BufferedImage img;
    // TODO: тут перечислите ваши объекты
    public static final Circle circle = new Circle(-3, -3, 4);
    //public static final VerticalParabola vp = new VerticalParabola(1.0/2, -5, 25.0/2);
    public static final HorizontalParabola hpU = new HorizontalParabola(2, 3, -1);
    public static final HorizontalParabola hpM = new HorizontalParabola(-3, -3, -0.25);
    public static final HorizontalParabola hpD = new HorizontalParabola(-6, -6, 1);

    public Main() {
        img = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    // Эта функция будет определять, что будет нарисовано на форме
    // Для заданных координат x [-10; 10] и y [-10; 10] нужно вернуть цвет точки
    private Color getColor(double x, double y) {
        boolean isFirstOk = hpU.isPointLeftOfParabola(x,y);
        boolean isSecondOk = hpM.isPointLeftOfParabola(x,y);
        boolean isThirdOk = !(hpD.isPointLeftOfParabola(x,y));
        boolean isInCirc = circle.isInCircle(x,y);
        if(isThirdOk && isSecondOk)
            return Color.YELLOW;
        if(isThirdOk && !isInCirc)
            return Color.GREEN;
        if(isFirstOk && isSecondOk)
            return Color.YELLOW;
        if(isFirstOk && isInCirc)
            return Color.WHITE;
        if(isSecondOk && !isInCirc)
            return Color.BLUE;
        if(isFirstOk)
            return Color.BLUE;
        if(isInCirc)
            return Color.GRAY;
        if(y>=-4)
            return Color.ORANGE;
        return Color.YELLOW;
    }

    public void paint(Graphics g) {
        int x_unit = IMAGE_WIDTH / CANVAS_ABS_SIZE / 2, y_unit = IMAGE_HEIGHT / CANVAS_ABS_SIZE / 2;
        for (int x = 0; x < IMAGE_WIDTH; x++) {
            for (int y = 0; y < IMAGE_HEIGHT; y++) {
                double canv_x = (double) x / x_unit - CANVAS_ABS_SIZE;
                double canv_y = CANVAS_ABS_SIZE - (double) y / y_unit;
                if (x % x_unit == 0 || y % y_unit == 0) {
                    img.setRGB(x, y, Color.GRAY.getRGB());
                } else {
                    img.setRGB(x, y, getColor(canv_x, canv_y).getRGB());
                }
            }
        }
        g.drawImage(img, 0, 0, null);
        // оси координат
        g.setColor(Color.BLACK);
        ((Graphics2D)g).setStroke(new BasicStroke(2));
        g.drawLine(IMAGE_WIDTH / 2, 0, IMAGE_WIDTH / 2, IMAGE_HEIGHT);
        g.drawLine(0, IMAGE_HEIGHT / 2, IMAGE_WIDTH, IMAGE_HEIGHT / 2);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Main canvas = new Main();
        canvas.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }
}