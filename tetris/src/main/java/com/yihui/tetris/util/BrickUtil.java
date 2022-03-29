package com.yihui.tetris.util;

import com.yihui.tetris.controlpanel.Brick;
import com.yihui.tetris.controlpanel.RotateDirection;
import com.yihui.tetris.impl.BrickImpl;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

public class BrickUtil {

    @Data
    @RequiredArgsConstructor
    private static class Shape {
        @NonNull private final long[] content;
        private final int width;
        private final int height;
        private final int variantNumber;
    }

    private static ArrayList<Shape> allShapes = new ArrayList<>();

    static {
        allShapes.add(new Shape(new long[]{0b110, 0b011}, 3, 2, 2));
        allShapes.add(new Shape(new long[]{0b011, 0b110}, 3, 2, 2));
        allShapes.add(new Shape(new long[]{0b100, 0b111}, 3, 2, 4));
        allShapes.add(new Shape(new long[]{0b001, 0b111}, 3, 2, 4));
        allShapes.add(new Shape(new long[]{0b010, 0b111}, 3, 2, 4));
        allShapes.add(new Shape(new long[]{0b11, 0b11}, 2, 2, 1));
        allShapes.add(new Shape(new long[]{0b1111}, 4, 1, 2));
    }

    public static Brick getRandomBrick() {
        final long current = System.currentTimeMillis();
        final long random = (long)(Math.random() * current);
        final int index = (int)(random % allShapes.size());
        final Shape shape = allShapes.get(index);
        final int rotation = (int)(random % shape.getVariantNumber());

        Brick b = new BrickImpl(shape.content, shape.width, shape.height, index, rotation);
        for (int i = 0; i < rotation; i++) {
            b.rotate(RotateDirection.ClockWise);
        }

        return b;
    }
}
