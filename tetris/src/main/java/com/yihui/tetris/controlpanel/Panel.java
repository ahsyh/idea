package com.yihui.tetris.controlpanel;

/**
 *
 */
public interface Panel {
    /**
     *
     * @param w width
     * @param h height
     */
    void init(int w, int h);

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
     * @return content
     */
    long[] getContent();

    /**
     *
     * @return cleared line number
     */
    int cleanFullLine();

    /**
     *
     */
    void reset();
}
