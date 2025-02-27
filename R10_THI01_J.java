/*
Rule 10. Thread APIs (THI)
THI01-J. Do not invoke ThreadGroup methods
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class R10_THI01_J {

    //Compliant Solution
    final class HandleRequest implements Runnable {
        public void run() {
            // Do something
        }
    }

    public final class NetworkHandler {
        private final ExecutorService executor;

        NetworkHandler(int poolSize) {
            this.executor = Executors.newFixedThreadPool(poolSize);
        }

        public void startThreads() {
            for (int i = 0; i < 3; i++) {
                executor.execute(new HandleRequest());
            }
        }

        public void shutdownPool() {
            executor.shutdown();
        }

        public static void main(String[] args)  {
            NetworkHandler nh = new NetworkHandler(3);
            nh.startThreads();
            nh.shutdownPool();
        }
    }
}