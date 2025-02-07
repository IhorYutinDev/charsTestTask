import org.junit.jupiter.api.Test;
import ua.yutin.processors.CharSequenceProcessor;
import ua.yutin.processors.CharSequenceProcessorOneIteration;

public class PerformanceTest {
    @Test
    public void comparingOfPerformanceTest() {
        String source = "aabbccfsdhlkhwj'jrtwjjwyjqrejyhyqiqryiqryhrehddeeffaabbccdd";
        String target = "bb";
        String replacement = "xx";

        char[] sourceArray = source.toCharArray();
        char[] targetArray = target.toCharArray();
        char[] replacementArray = replacement.toCharArray();

        long startTime, endTime;

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            CharSequenceProcessor processor1 = new CharSequenceProcessor();
            processor1.getModifiedCharSequence(sourceArray, targetArray, replacementArray);
        }
        endTime = System.currentTimeMillis();

        System.out.println("CharSequenceProcessor execution time:  " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            CharSequenceProcessorOneIteration processor2 = new CharSequenceProcessorOneIteration();
            processor2.getModifiedCharSequence(sourceArray, targetArray, replacementArray);
        }
        endTime = System.currentTimeMillis();

        System.out.println("CharSequenceProcessorOneIteration execution time: " + (endTime - startTime) + " ms");
    }
}
