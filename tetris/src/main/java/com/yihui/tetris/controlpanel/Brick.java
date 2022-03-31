package com.yihui.tetris.controlpanel;

/**
 *
 */
public interface Brick {
    /**
     *
     * @param c content
     * @param w width
     * @param h height
     */
    void init(long[] c, int w, int h);

    /**
     *
     * @return content
     */
    long[] getContent();

    /**
     *
     * @return width
     */
    int getWidth();
    /**
     *
     * @return height
     */
    int getHeight();
    /**
     *
     * @return brick index
     */
    int getIndex();
    /**
     *
     * @return rotation
     */
    int getRotation();

    /**
     *
     * @param r rotation
     */
    void rotate(RotateDirection r);
    /**
     *
     * @param r rotation
     *
     * @return new brick
     */
    Brick getRotate(RotateDirection r);

    /**
     *
     * @param x x
     * @param y y
     * @return -1 out of range
     *         0 clear
     *         1 filled
     */
    int getBitAtPosition(int x, int y);
}
