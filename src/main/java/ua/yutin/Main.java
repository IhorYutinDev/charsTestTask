package ua.yutin;

import ua.yutin.processors.CharSequenceProcessor;
import ua.yutin.processors.CharSequenceProcessorOneIteration;
import ua.yutin.processors.ISequenceProcessor;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        char[] source = new char[]{'1', '2', '3', '4', '5', '3', '4'};
        char[] target = new char[]{'3', '4'};
        //char[] target = null;
        char[] replacement = new char[]{'a', 'b', 'c'};
        char[] expectedResult = new char[]{'1', '2', 'a', 'b', 'c', '5', 'a', 'b', 'c'};

        //Added tests CharSequenceProcessorTest

        ISequenceProcessor processor = new CharSequenceProcessor();

        try {
            char[] actualResult = processor.getModifiedCharSequence(source, target, replacement);

            System.out.println("Is it cool processor: " + Arrays.equals(expectedResult, actualResult));
            System.out.println(Arrays.toString(expectedResult));
            System.out.println(Arrays.toString(actualResult));


            // the best performance ***
//            CharSequenceProcessor execution time:  37 ms
//            CharSequenceProcessorOneIteration execution time: 40 ms
            ISequenceProcessor processor2 = new CharSequenceProcessorOneIteration();

            actualResult = processor2.getModifiedCharSequence(source, target, replacement);

            System.out.println("Is it cool processor: " + Arrays.equals(expectedResult, actualResult));
            System.out.println(Arrays.toString(expectedResult));
            System.out.println(Arrays.toString(actualResult));


        } catch (IllegalArgumentException e) {
            System.out.println("Provided not valid args");
        }
    }
}