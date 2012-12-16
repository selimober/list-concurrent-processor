#list-concurrent-processor

Iterates over a list concurrently while processing each item in parallel

##Example usage
Takes a list of Foo objects, for every object, it multiplies the value **`in`** with 10 and writes the result to **`out`**.
This example employs **100** threads for this operation. The custom logic is implemented in the `Consumer` interface.
###Foo.java:
    public class Foo {
        public int in;
        public int out;
    }
###processor:
    ListConcurrentProcessor<Foo> lcp = new QueueListConcurrentProcessor<Foo>(listOfFoos, 100);
    lcp.processList(new Consumer<Foo>() {
        @Override
        public void consume(Foo foo) {
            foo.out = foo.in() * 10;
        }
    });
