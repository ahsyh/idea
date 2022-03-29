package com.yihui.tetris.util;

import com.yihui.tetris.controlpanel.Brick;
import com.yihui.tetris.controlpanel.RotateDirection;
import com.yihui.tetris.impl.BrickImpl;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

/**
 *
 */
@SuppressWarnings("checkstyle:MagicNumber")
public final class BrickUtil {

    /**
     *
     */
    @Data
    @RequiredArgsConstructor
    private static class Shape {
        @NonNull private final long[] content;
        private final int width;
        private final int height;
        private final int variantNumber;
    }

    private static ArrayList<Shape> allShapes = new ArrayList<>();

    private static final int SIZE_1 = 1;
    private static final int SIZE_2 = 2;
    private static final int SIZE_3 = 3;
    private static final int SIZE_4 = 4;

    private static final int VARIANT_NUMBER_1 = 1;
    private static final int VARIANT_NUMBER_2 = 2;
    private static final int VARIANT_NUMBER_4 = 4;

    private static final long B_001 = 0b001;
    private static final long B_010 = 0b010;
    private static final long B_011 = 0b011;
    private static final long B_100 = 0b100;
    private static final long B_110 = 0b110;
    private static final long B_111 = 0b111;
    private static final long B_1111 = 0b1111;

    static {
        allShapes.add(new Shape(new long[]{B_110, B_011}, SIZE_3, SIZE_2, VARIANT_NUMBER_2));
        allShapes.add(new Shape(new long[]{B_011, B_110}, SIZE_3, SIZE_2, VARIANT_NUMBER_2));
        allShapes.add(new Shape(new long[]{B_100, B_111}, SIZE_3, SIZE_2, VARIANT_NUMBER_4));
        allShapes.add(new Shape(new long[]{B_001, B_111}, SIZE_3, SIZE_2, VARIANT_NUMBER_4));
        allShapes.add(new Shape(new long[]{B_010, B_111}, SIZE_3, SIZE_2, VARIANT_NUMBER_4));
        allShapes.add(new Shape(new long[]{B_011, B_011}, SIZE_2, SIZE_2, VARIANT_NUMBER_1));
        allShapes.add(new Shape(new long[]{B_1111}, SIZE_4, SIZE_1, VARIANT_NUMBER_2));
    }

    private BrickUtil() {
    }

    /**
     *
     * @return brick
     */
    public static Brick getRandomBrick() {
        final long current = System.currentTimeMillis();
        final long random = (long) (Math.random() * current);
        final int index = (int) (random % allShapes.size());
        final Shape shape = allShapes.get(index);
        final int rotation = (int) (random % shape.getVariantNumber());

        Brick b = new BrickImpl(shape.content, shape.width, shape.height, index, rotation);
        for (int i = 0; i < rotation; i++) {
            b.rotate(RotateDirection.ClockWise);
        }

        return b;
    }
}
