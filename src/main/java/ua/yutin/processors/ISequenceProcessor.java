package ua.yutin.processors;

public interface ISequenceProcessor {
    char[] getModifiedCharSequence(char[] source, char[] target, char[] replacement);
}
