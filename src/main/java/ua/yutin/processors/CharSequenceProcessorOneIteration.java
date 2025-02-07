package ua.yutin.processors;

import java.util.Arrays;


public class CharSequenceProcessorOneIteration implements ISequenceProcessor {
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

        int futureResultLength = sourceLength;
        for (int i = 0; i <= sourceLength - step; i++) {
            if (checkOverlap(source, i, target, step)) {
                futureResultLength += (replacementLength - step);
                i += step - 1;
            }
        }

        char[] result = new char[futureResultLength];
        int i = 0, j = 0;
        while (i < sourceLength) {
            if (getReplaceMoveCondition(source, target, i, sourceLength, step)) {
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

    private boolean getReplaceMoveCondition(char[] source, char[] target, int i, int sourceLength, int step) {
        return i <= sourceLength - step && checkOverlap(source, i, target, step);
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

