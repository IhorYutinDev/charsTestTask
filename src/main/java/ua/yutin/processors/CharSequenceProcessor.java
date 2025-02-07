package ua.yutin.processors;

import java.util.Arrays;

public class CharSequenceProcessor implements ISequenceProcessor {
    @Override
    public char[] getModifiedCharSequence(char[] source, char[] target, char[] replacement) {
        if (source == null || target == null || replacement == null) {
            throw new IllegalArgumentException("Provided arrays cannot be null");
        }

        if (target.length == 0 || source.length == 0 || source.length < target.length || Arrays.equals(target, replacement)) {
            return source;
        }

        int sourceLength = source.length;
        int step = target.length;
        int replacementLength = replacement.length;

        int quantityInjections = getQuantityInjections(source, target, step);
        int finalArrLength = calculateLengthOfModifiedArray(sourceLength, step, replacementLength, quantityInjections);

        return modifyWithInsertion(source, target, replacement, step, finalArrLength);
    }

    private int getQuantityInjections(char[] source, char[] target, int step) {
        int iterationLengthWithoutStep = source.length - step;

        int quantityInjections = 0;
        int index = 0;
        while (index <= iterationLengthWithoutStep) {
            if (checkOverlap(source, index, target, step)) {
                quantityInjections++;
                index += step;
            } else {
                index++;
            }
        }

        return quantityInjections;
    }


    private char[] modifyWithInsertion(char[] source, char[] target, char[] replacement, int step, int finalLength) {
        char[] result = new char[finalLength];

        int i = 0;
        int j = 0;
        while (i < source.length) {
            if (getReplaceMoveCondition(source, target, step, i)) {
                for (char c : replacement) {
                    result[j++] = c;
                }
                i += step;
            } else {
                result[j++] = source[i++];
            }
        }

        return result;
    }

    private boolean getReplaceMoveCondition(char[] source, char[] target, int step, int i) {
        return i <= source.length - step && checkOverlap(source, i, target, step);
    }


    private int calculateLengthOfModifiedArray(int providedSourceLength, int step, int replacementLength, int quantityInjections) {
        int reduceLength = quantityInjections * step;
        int increaseLength = quantityInjections * replacementLength;

        return providedSourceLength - reduceLength + increaseLength;
    }

    private boolean checkOverlap(char[] source, int beginIndex, char[] target, int step) {
        boolean result = true;
        for (int i = 0; i < step; i++) {
            int movedIndex = beginIndex + i;
            if (source[movedIndex] != target[i]) {
                result = false;
                break;
            }
        }

        return result;
    }
}
