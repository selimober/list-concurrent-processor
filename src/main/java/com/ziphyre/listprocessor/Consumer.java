package com.selimober.listprocessor;

/**
 * Used in ListConcurrentProcessor.
 * Needs to be instantiated with new and by overriding its sole method 'consume'.
 */
public interface Consumer<T> {

    /**
     * Operates on given object
     * @param target object to be operated upon.
     */
    public void consume(T target);
}
