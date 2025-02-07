import org.junit.jupiter.api.Test;
import ua.yutin.processors.CharSequenceProcessor;
import ua.yutin.processors.CharSequenceProcessorOneIteration;
import ua.yutin.processors.ISequenceProcessor;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CharSequenceProcessorOneIterationTest {
    private final ISequenceProcessor processor = new CharSequenceProcessorOneIteration();


    @Test
    void testReplacementAtStart() {
        char[] source = new char[]{'1', '2', '3', '4', '5', '1', '2', '3', '4', '5', '6', '7', '1', '2', '3', '4', '5', '8'};
        char[] target = new char[]{'1', '2', '3', '4', '5'};
        char[] replacement = new char[]{'9', '9'};
        char[] expected = new char[]{'9', '9', '9', '9', '6', '7', '9', '9', '8'};
        assertArrayEquals(expected, processor.getModifiedCharSequence(source, target, replacement));
    }

    @Test
    void testReplacementInMiddle() {
        char[] source = new char[]{'1', '0', '2', '0', '3', '0'};
        char[] target = new char[]{'2', '0'};
        char[] replacement = new char[]{'7', '7'};
        char[] expected = new char[]{'1', '0', '7', '7', '3', '0'};
        assertArrayEquals(expected, processor.getModifiedCharSequence(source, target, replacement));
    }

    @Test
    void testReplacementAtEnd() {
        char[] source = new char[]{'5', '5', '5', '6', '6', '6'};
        char[] target = new char[]{'6', '6', '6'};
        char[] replacement = new char[]{'9', '9', '9'};
        char[] expected = new char[]{'5', '5', '5', '9', '9', '9'};
        assertArrayEquals(expected, processor.getModifiedCharSequence(source, target, replacement));
    }

    @Test
    void testMultipleReplacements() {
        char[] source = new char[]{'1', '1', '1', '1', '1', '1', '1', '1', '1'};
        char[] target = new char[]{'1', '1', '1'};
        char[] replacement = new char[]{'0'};
        char[] expected = new char[]{'0', '0', '0'};
        assertArrayEquals(expected, processor.getModifiedCharSequence(source, target, replacement));
    }

    @Test
    void testNoReplacements() {
        char[] source = new char[]{'9', '8', '7', '6', '5', '4'};
        char[] target = new char[]{'1', '2', '3'};
        char[] replacement = new char[]{'0', '0', '0'};
        char[] expected = new char[]{'9', '8', '7', '6', '5', '4'};
        assertArrayEquals(expected, processor.getModifiedCharSequence(source, target, replacement));
    }

    @Test
    void testEmptyTarget() {
        char[] source = new char[]{'4', '4', '4', '5', '5', '5'};
        char[] target = new char[]{};
        char[] replacement = new char[]{'0'};
        char[] expected = new char[]{'4', '4', '4', '5', '5', '5'};
        assertArrayEquals(expected, processor.getModifiedCharSequence(source, target, replacement));
    }

    @Test
    void testNullInputs() {
        assertThrows(IllegalArgumentException.class, () -> processor.getModifiedCharSequence(null, new char[]{'1'}, new char[]{'2'}));
        assertThrows(IllegalArgumentException.class, () -> processor.getModifiedCharSequence(new char[]{'1'}, null, new char[]{'2'}));
        assertThrows(IllegalArgumentException.class, () -> processor.getModifiedCharSequence(new char[]{'1'}, new char[]{'2'}, null));
    }

    @Test
    void testEmptySource() {
        char[] source = new char[]{};
        char[] target = new char[]{'1'};
        char[] replacement = new char[]{'2'};
        assertArrayEquals(source, processor.getModifiedCharSequence(source, target, replacement));
    }

    @Test
    void testTargetNotFound() {
        char[] source = new char[]{'3', '4', '5', '6'};
        char[] target = new char[]{'9', '9', '9'};
        char[] replacement = new char[]{'0', '0', '0'};
        assertArrayEquals(source, processor.getModifiedCharSequence(source, target, replacement));
    }

    @Test
    void testReplaceSingleOccurrence() {
        char[] source = new char[]{'7', '8', '8', '9'};
        char[] target = new char[]{'8', '8'};
        char[] replacement = new char[]{'0', '1'};
        char[] expected = new char[]{'7', '0', '1', '9'};
        assertArrayEquals(expected, processor.getModifiedCharSequence(source, target, replacement));
    }

    @Test
    void testReplaceMultipleOccurrences() {
        char[] source = new char[]{'2', '3', '4', '2', '3', '4'};
        char[] target = new char[]{'2', '3'};
        char[] replacement = new char[]{'8', '8'};
        char[] expected = new char[]{'8', '8', '4', '8', '8', '4'};
        assertArrayEquals(expected, processor.getModifiedCharSequence(source, target, replacement));
    }

    @Test
    void testReplaceWithEmpty() {
        char[] source = new char[]{'5', '6', '7', '5', '6', '7'};
        char[] target = new char[]{'5', '6'};
        char[] replacement = new char[]{};
        char[] expected = new char[]{'7', '7'};
        assertArrayEquals(expected, processor.getModifiedCharSequence(source, target, replacement));
    }

    @Test
    void testTargetSameAsReplacement() {
        char[] source = new char[]{'4', '5', '6'};
        char[] target = new char[]{'5'};
        char[] replacement = new char[]{'5'};
        assertArrayEquals(source, processor.getModifiedCharSequence(source, target, replacement));
    }



//    @Test
//    void testReplacementPerformance() {
//        long start = System.currentTimeMillis();
//        char[] source = {'a', 'b', 'c'};
//        char[] target = {'b'};
//        char[] replacement = {'b'};
//
//        for (int i = 0; i < 100000000; i++) {
//            processor.getModifiedCharSequence(source, target, replacement);
//        }
//
//        long end = System.currentTimeMillis();
//
//        System.out.println("Executing time: " + (end - start));
//
//        assertArrayEquals(source, processor.getModifiedCharSequence(source, target, replacement));
//    }
}
