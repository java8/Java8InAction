package lambdasinaction.appc;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Adapted from http://mail.openjdk.java.net/pipermail/lambda-dev/2013-November/011516.html
 */
public class StreamForker<T> {

    private final Stream<T> stream;
    private final Map<Object, Function<Stream<T>, ?>> forks = new HashMap<>();

    public StreamForker(Stream<T> stream) {
        this.stream = stream;
    }

    public StreamForker<T> fork(Object key, Function<Stream<T>, ?> f) {
        forks.put(key, f);
        return this;
    }

    public Results getResults() {
        ForkingStreamConsumer<T> consumer = build();
        try {
            stream.sequential().forEach(consumer);
        } finally {
            consumer.finish();
        }
        return consumer;
    }

    private ForkingStreamConsumer<T> build() {
        List<BlockingQueue<T>> queues = new ArrayList<>();

        Map<Object, Future<?>> actions =
                forks.entrySet().stream().reduce(
                        new HashMap<Object, Future<?>>(),
                        (map, e) -> {
                            map.put(e.getKey(),
                                    getOperationResult(queues, e.getValue()));
                            return map;
                        },
                        (m1, m2) -> {
                            m1.putAll(m2);
                            return m1;
                        });

        return new ForkingStreamConsumer<>(queues, actions);
    }

    private Future<?> getOperationResult(List<BlockingQueue<T>> queues, Function<Stream<T>, ?> f) {
        BlockingQueue<T> queue = new LinkedBlockingQueue<>();
        queues.add(queue);
        Spliterator<T> spliterator = new BlockingQueueSpliterator<>(queue);
        Stream<T> source = StreamSupport.stream(spliterator, false);
        return CompletableFuture.supplyAsync( () -> f.apply(source) );
    }

    public static interface Results {
        public <R> R get(Object key);
    }

    private static class ForkingStreamConsumer<T> implements Consumer<T>, Results {
        static final Object END_OF_STREAM = new Object();

        private final List<BlockingQueue<T>> queues;
        private final Map<Object, Future<?>> actions;

        ForkingStreamConsumer(List<BlockingQueue<T>> queues, Map<Object, Future<?>> actions) {
            this.queues = queues;
            this.actions = actions;
        }

        @Override
        public void accept(T t) {
            queues.forEach(q -> q.add(t));
        }

        @Override
        public <R> R get(Object key) {
            try {
                return ((Future<R>) actions.get(key)).get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        void finish() {
            accept((T) END_OF_STREAM);
        }
    }

    private static class BlockingQueueSpliterator<T> implements Spliterator<T> {
        private final BlockingQueue<T> q;

        BlockingQueueSpliterator(BlockingQueue<T> q) {
            this.q = q;
        }

        @Override
        public boolean tryAdvance(Consumer<? super T> action) {
            T t;
            while (true) {
                try {
                    t = q.take();
                    break;
                }
                catch (InterruptedException e) {
                }
            }

            if (t != ForkingStreamConsumer.END_OF_STREAM) {
                action.accept(t);
                return true;
            }

            return false;
        }

        @Override
        public Spliterator<T> trySplit() {
            return null;
        }

        @Override
        public long estimateSize() {
            return 0;
        }

        @Override
        public int characteristics() {
            return 0;
        }
    }
}
