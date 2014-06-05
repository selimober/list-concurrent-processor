package com.selimober.listprocessor;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class QueueListConcurrentProcessorTest {
    private List<Foo> fooList;

    @Before
    public void setUp() throws Exception {
        fooList = new ArrayList<Foo>();
        for (int i = 0; i < 50; i++) {
            fooList.add(new Foo(i));
        }
    }

    @Test
    public void testProcessList() throws Exception {
        ListConcurrentProcessor<Foo> listConcurrentProcessor = new QueueListConcurrentProcessor<Foo>(fooList, 100);

        long start = new Date().getTime();

        listConcurrentProcessor.processList(new Consumer<Foo>() {
            @Override
            public void consume(Foo object) {
                object.setOut(object.getIn() * 10);
            }
        });

        long end = new Date().getTime();

        printOutDelta(start, end);

        printOut(fooList);
    }

    private void printOutDelta(long start, long end) {
        long delta = end - start;

        System.out.println(String.valueOf(delta));
    }

    private void printOut(List<Foo> myList) {
        for (Foo foo : myList) {
            String in = String.valueOf(foo.getIn());
            String out = String.valueOf(foo.getOut());
            System.out.println("In: " + in + ", Out: " + out);
        }
    }
}