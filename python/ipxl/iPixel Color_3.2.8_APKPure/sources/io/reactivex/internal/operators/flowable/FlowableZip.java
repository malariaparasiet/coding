package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.functions.Function;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* loaded from: classes3.dex */
public final class FlowableZip<T, R> extends Flowable<R> {
    final int bufferSize;
    final boolean delayError;
    final Publisher<? extends T>[] sources;
    final Iterable<? extends Publisher<? extends T>> sourcesIterable;
    final Function<? super Object[], ? extends R> zipper;

    public FlowableZip(Publisher<? extends T>[] publisherArr, Iterable<? extends Publisher<? extends T>> iterable, Function<? super Object[], ? extends R> function, int i, boolean z) {
        this.sources = publisherArr;
        this.sourcesIterable = iterable;
        this.zipper = function;
        this.bufferSize = i;
        this.delayError = z;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super R> subscriber) {
        int length;
        Publisher<? extends T>[] publisherArr = this.sources;
        if (publisherArr == null) {
            publisherArr = new Publisher[8];
            length = 0;
            for (Publisher<? extends T> publisher : this.sourcesIterable) {
                if (length == publisherArr.length) {
                    Publisher<? extends T>[] publisherArr2 = new Publisher[(length >> 2) + length];
                    System.arraycopy(publisherArr, 0, publisherArr2, 0, length);
                    publisherArr = publisherArr2;
                }
                publisherArr[length] = publisher;
                length++;
            }
        } else {
            length = publisherArr.length;
        }
        int i = length;
        if (i == 0) {
            EmptySubscription.complete(subscriber);
            return;
        }
        ZipCoordinator zipCoordinator = new ZipCoordinator(subscriber, this.zipper, i, this.bufferSize, this.delayError);
        subscriber.onSubscribe(zipCoordinator);
        zipCoordinator.subscribe(publisherArr, i);
    }

    static final class ZipCoordinator<T, R> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = -2434867452883857743L;
        volatile boolean cancelled;
        final Object[] current;
        final boolean delayErrors;
        final Subscriber<? super R> downstream;
        final AtomicThrowable errors;
        final AtomicLong requested;
        final ZipSubscriber<T, R>[] subscribers;
        final Function<? super Object[], ? extends R> zipper;

        ZipCoordinator(Subscriber<? super R> subscriber, Function<? super Object[], ? extends R> function, int i, int i2, boolean z) {
            this.downstream = subscriber;
            this.zipper = function;
            this.delayErrors = z;
            ZipSubscriber<T, R>[] zipSubscriberArr = new ZipSubscriber[i];
            for (int i3 = 0; i3 < i; i3++) {
                zipSubscriberArr[i3] = new ZipSubscriber<>(this, i2);
            }
            this.current = new Object[i];
            this.subscribers = zipSubscriberArr;
            this.requested = new AtomicLong();
            this.errors = new AtomicThrowable();
        }

        void subscribe(Publisher<? extends T>[] publisherArr, int i) {
            ZipSubscriber<T, R>[] zipSubscriberArr = this.subscribers;
            for (int i2 = 0; i2 < i && !this.cancelled; i2++) {
                if (!this.delayErrors && this.errors.get() != null) {
                    return;
                }
                publisherArr[i2].subscribe(zipSubscriberArr[i2]);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                drain();
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            cancelAll();
        }

        void error(ZipSubscriber<T, R> zipSubscriber, Throwable th) {
            if (this.errors.addThrowable(th)) {
                zipSubscriber.done = true;
                drain();
            } else {
                RxJavaPlugins.onError(th);
            }
        }

        void cancelAll() {
            for (ZipSubscriber<T, R> zipSubscriber : this.subscribers) {
                zipSubscriber.cancel();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:100:0x0148, code lost:
        
            if (r20.delayErrors == false) goto L109;
         */
        /* JADX WARN: Code restructure failed: missing block: B:102:0x014a, code lost:
        
            cancelAll();
            r2.onError(r20.errors.terminate());
         */
        /* JADX WARN: Code restructure failed: missing block: B:103:0x0156, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:108:?, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:110:0x015c, code lost:
        
            if (r12 == r17) goto L98;
         */
        /* JADX WARN: Code restructure failed: missing block: B:111:0x015e, code lost:
        
            r0 = r3.length;
            r6 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:112:0x0161, code lost:
        
            if (r6 >= r0) goto L126;
         */
        /* JADX WARN: Code restructure failed: missing block: B:113:0x0163, code lost:
        
            r3[r6].request(r12);
            r6 = r6 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:116:0x0172, code lost:
        
            if (r8 == Long.MAX_VALUE) goto L98;
         */
        /* JADX WARN: Code restructure failed: missing block: B:117:0x0174, code lost:
        
            r20.requested.addAndGet(-r12);
         */
        /* JADX WARN: Code restructure failed: missing block: B:118:0x017a, code lost:
        
            r7 = addAndGet(-r7);
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x00dc, code lost:
        
            if (r8 != r12) goto L90;
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x00e0, code lost:
        
            if (r20.cancelled == false) goto L58;
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x00e6, code lost:
        
            if (r20.delayErrors != false) goto L64;
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x00ee, code lost:
        
            if (r20.errors.get() == null) goto L64;
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x00f0, code lost:
        
            cancelAll();
            r2.onError(r20.errors.terminate());
         */
        /* JADX WARN: Code restructure failed: missing block: B:71:0x00fc, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:72:0x00fd, code lost:
        
            r6 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:73:0x00ff, code lost:
        
            if (r6 >= r4) goto L121;
         */
        /* JADX WARN: Code restructure failed: missing block: B:74:0x0101, code lost:
        
            r0 = r3[r6];
         */
        /* JADX WARN: Code restructure failed: missing block: B:75:0x0105, code lost:
        
            if (r5[r6] != null) goto L122;
         */
        /* JADX WARN: Code restructure failed: missing block: B:77:0x0107, code lost:
        
            r10 = r0.done;
            r0 = r0.queue;
         */
        /* JADX WARN: Code restructure failed: missing block: B:78:0x010b, code lost:
        
            if (r0 == null) goto L71;
         */
        /* JADX WARN: Code restructure failed: missing block: B:79:0x010d, code lost:
        
            r0 = r0.poll();
         */
        /* JADX WARN: Code restructure failed: missing block: B:80:0x0113, code lost:
        
            if (r0 != null) goto L74;
         */
        /* JADX WARN: Code restructure failed: missing block: B:81:0x0115, code lost:
        
            r11 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:82:0x0119, code lost:
        
            if (r10 == false) goto L81;
         */
        /* JADX WARN: Code restructure failed: missing block: B:83:0x011b, code lost:
        
            if (r11 == false) goto L81;
         */
        /* JADX WARN: Code restructure failed: missing block: B:85:0x011d, code lost:
        
            cancelAll();
         */
        /* JADX WARN: Code restructure failed: missing block: B:86:0x0128, code lost:
        
            if (r20.errors.get() == null) goto L80;
         */
        /* JADX WARN: Code restructure failed: missing block: B:87:0x012a, code lost:
        
            r2.onError(r20.errors.terminate());
         */
        /* JADX WARN: Code restructure failed: missing block: B:88:?, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:89:0x0134, code lost:
        
            r2.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:90:?, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:91:0x0138, code lost:
        
            if (r11 != false) goto L123;
         */
        /* JADX WARN: Code restructure failed: missing block: B:92:0x013a, code lost:
        
            r5[r6] = r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:94:0x0157, code lost:
        
            r6 = r6 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:96:0x0117, code lost:
        
            r11 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:97:0x0112, code lost:
        
            r0 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:98:0x013d, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:99:0x013e, code lost:
        
            io.reactivex.exceptions.Exceptions.throwIfFatal(r0);
            r20.errors.addThrowable(r0);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void drain() {
            /*
                Method dump skipped, instructions count: 386
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableZip.ZipCoordinator.drain():void");
        }
    }

    static final class ZipSubscriber<T, R> extends AtomicReference<Subscription> implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -4627193790118206028L;
        volatile boolean done;
        final int limit;
        final ZipCoordinator<T, R> parent;
        final int prefetch;
        long produced;
        SimpleQueue<T> queue;
        int sourceMode;

        ZipSubscriber(ZipCoordinator<T, R> zipCoordinator, int i) {
            this.parent = zipCoordinator;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this, subscription)) {
                if (subscription instanceof QueueSubscription) {
                    QueueSubscription queueSubscription = (QueueSubscription) subscription;
                    int requestFusion = queueSubscription.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        this.done = true;
                        this.parent.drain();
                        return;
                    }
                    if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        subscription.request(this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                subscription.request(this.prefetch);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.sourceMode != 2) {
                this.queue.offer(t);
            }
            this.parent.drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.parent.error(this, th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            SubscriptionHelper.cancel(this);
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (this.sourceMode != 1) {
                long j2 = this.produced + j;
                if (j2 >= this.limit) {
                    this.produced = 0L;
                    get().request(j2);
                } else {
                    this.produced = j2;
                }
            }
        }
    }
}
