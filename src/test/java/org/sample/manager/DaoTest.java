package org.sample.manager;

import org.junit.Test;
import org.sample.dao.ProfileDAO;
import org.sample.dao.impl.ProfileDAOImpl;
import org.sample.entity.Profile;
import org.sample.exception.DaoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class DaoTest {

    private static final Logger LOGGER = Logger.getLogger(DaoTest.class.getName());

    private static final String ORIGIN_STRING = "hello";
    private static String RandomString() {
        return Math.random() + ORIGIN_STRING + Math.random();
    }
    private static Profile RandomProfile() {
        Profile profile = new Profile(RandomString(), ORIGIN_STRING, RandomString());
        return profile;
    }

    private static final ProfileDAO PROFILE_DAO = ProfileDAOImpl.INSTANCE;

    private class Worker implements Runnable {
        private final Profile profile = RandomProfile();

        @Override
        public void run() {
            LOGGER.info(Thread.currentThread().getName() + " has started his work");
            try {
                LocalConnectionProxy.setAutoCommit(false);
                PROFILE_DAO.saveProfile(profile);
                LocalConnectionProxy.commit();
            } catch (DaoException e) {
                e.printStackTrace();
            } finally {
                try {
                    LocalConnectionProxy.close();
                } catch (DaoException e) {
                    e.printStackTrace();
                }
            }
            LOGGER.info(Thread.currentThread().getName() + " has finished his work");
        }
    }

    private static final int numTasks = 102;

    @Test
    public void test() throws Exception {
        List<Runnable> workers = new LinkedList<>();
        for(int i = 0; i != numTasks; ++i) {
            workers.add(new Worker());
        }
        assertConcurrent("Dao test ", workers, Integer.MAX_VALUE);
    }

    public static void assertConcurrent(final String message, final List<? extends Runnable> runnables, final int maxTimeoutSeconds) throws InterruptedException {
        final int numThreads = runnables.size();
        final List<Throwable> exceptions = Collections.synchronizedList(new ArrayList<Throwable>());
        final ExecutorService threadPool = Executors.newFixedThreadPool(numThreads);
        try {
            final CountDownLatch allExecutorThreadsReady = new CountDownLatch(numThreads);
            final CountDownLatch afterInitBlocker = new CountDownLatch(1);
            final CountDownLatch allDone = new CountDownLatch(numThreads);
            for (final Runnable submittedTestRunnable : runnables) {
                threadPool.submit(new Runnable() {
                    public void run() {
                        allExecutorThreadsReady.countDown();
                        try {
                            afterInitBlocker.await();
                            submittedTestRunnable.run();
                        } catch (final Throwable e) {
                            exceptions.add(e);
                        } finally {
                            allDone.countDown();
                        }
                    }
                });
            }
            // wait until all threads are ready
            assertTrue("Timeout initializing threads! Perform long lasting initializations before passing runnables to assertConcurrent", allExecutorThreadsReady.await(runnables.size() * 10, TimeUnit.MILLISECONDS));
            // start all test runners
            afterInitBlocker.countDown();
            assertTrue(message +" timeout! More than" + maxTimeoutSeconds + "seconds", allDone.await(maxTimeoutSeconds, TimeUnit.SECONDS));
        } finally {
            threadPool.shutdownNow();
        }
        assertTrue(message + "failed with exception(s)" + exceptions, exceptions.isEmpty());
    }
}
