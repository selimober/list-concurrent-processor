package com.selimober.listprocessor;

/**
 * Utilities for processing lists concurrently.
 */
public interface ListConcurrentProcessor<T> {
    /**
     * Iterates over a list concurrently and apply Consumer.consume method
     * to every item in list.
     * @param consumer contains implementation of the consume method to be applied on every item of the list.
     */
    public void processList(Consumer<T> consumer);
}
