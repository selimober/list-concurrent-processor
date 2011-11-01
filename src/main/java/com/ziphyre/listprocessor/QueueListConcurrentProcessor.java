package com.ziphyre.listprocessor;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Iterates a list simultaneously using a concurrent queue and a CountDownLatch.
 */
public class QueueListConcurrentProcessor<T> implements ListConcurrentProcessor<T> {

    private List<T> myList;
    private Queue<T> myQueue;
    private CountDownLatch endGate;
    private int THREAD_COUNT = 5;

    /**
     * Instantiate a processor with default number of threads
     * @param targetList list to be operated upon
     */
    public QueueListConcurrentProcessor(List<T> targetList) {
        this.myList = targetList;
        this.myQueue = new ConcurrentLinkedQueue<T>(targetList);
        this.endGate = new CountDownLatch(targetList.size());
    }

    /**
     * Instantiate a processor with an arbitrary number of threads
     * @param myList list to be operated upon
     * @param threadCount count of threads to be created in the ThreadPool
     */
    public QueueListConcurrentProcessor(List<T> myList, int threadCount) {
        this(myList);
        this.THREAD_COUNT = threadCount;
    }

    @Override
    public void processList(final Consumer<T> consumer) {
        ExecutorService exec = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < myList.size(); i++) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    T target = myQueue.poll();
                    consumer.consume(target);
                    endGate.countDown();
                }
            });
        }

        try {
            endGate.await();
            exec.shutdown();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}

