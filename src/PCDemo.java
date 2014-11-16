import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;

import static akka.actor.Actors.*;

/*
 * Demo only - better to have the actor classes (and any message classes)
 * in their own source files. But this is more readable as a demo.
 */

public class PCDemo {
    /*
     * Two producers; one consumer
     */
    static private ActorRef[] producer = new ActorRef[2];
    static private ActorRef consumer;

    /*
     * Producer class.
     */
    static class Producer extends UntypedActor {
        final private String name;

        public Producer(String name) {
            this.name = name;
        }

        public void onReceive(Object message) {
            String s = (String) message;

            /*
             * Send the first version of the message to the consumer.
             */
            String msg = s + "-1";
            System.out.println("Producer " + name + " receives and passes " + msg);

            consumer.tell(msg);

            /*
             * We call yield just to see if it works.
             * It is also possible to call sleep as long
             * as the duration is not too long.
             */
            try {
                Thread.yield();
            } catch (Exception e) {
            }
            ;

            /*
             * Now send the second version of the message to the consumer.
             */
            msg = s + "-2";
            System.out.println("Producer " + name + " repasses " + msg);
            consumer.tell(msg);
        }
    }

    /*
     * Simple consumer class - just echos the strings it receives.
     */
    static class Consumer extends UntypedActor {
        public void onReceive(Object message) {
            String s = (String) message;
            System.out.println("  Consumer receives " + s);
        }
    }

    /*
     * Driver main function.
     *  Create 2 producers and 1 consumer
     *  Start the agents.
     *  Fire off messages to alternate producers.
     *  Wait a bit for everything to flush through.
     *  Kill the agents.
     */
    public static void main(String[] args) {
        producer[0] = actorOf(
                new UntypedActorFactory() {
                    public UntypedActor create() {
                        return new Producer("Pete");
                    }
                });

        producer[1] = actorOf(
                new UntypedActorFactory() {
                    public UntypedActor create() {
                        return new Producer("Mike");
                    }
                });

        consumer = actorOf(Consumer.class);

        producer[0].start();
        producer[1].start();
        consumer.start();

        for (int i = 1; i < 10; i++) {
            producer[(i % 2)].tell("Message #" + i);
        }

        try {
            Thread.currentThread().sleep(1000);
        } catch (Exception e) {
        }
        ;

        producer[0].stop();
        producer[1].stop();
        consumer.stop();
    }
}
