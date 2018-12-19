package org.sample.webapp.dao;

import org.junit.Test;
import org.sample.webapp.db.connmanager.ConnectionProxy;
import org.sample.webapp.dao.impl.ProfileDAOImpl;
import org.sample.webapp.entity.Profile;
import org.sample.webapp.exception.DaoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.sample.webapp.dao.ProfileDAOTest.RandomProfile;

public class ConcurrentTest {

    private final ProfileDAO profileDAO = ProfileDAOImpl.INSTANCE;

    private class Worker implements Runnable {
        private final Profile profile = RandomProfile();

        @Override
        public void run() {
            // LOG.info(Thread.currentThread().getName() + " has started his work");
            try {
                // ConnectionProxy.setAutoCommit(false);
                profileDAO.saveProfile(profile);
                // ConnectionProxy.commit();
            } catch (DaoException e) {
                e.printStackTrace();
            } finally {
                try {
                    ConnectionProxy.close();
                } catch (DaoException e) {
                    e.printStackTrace();
                }
            }
            // LOG.info(Thread.currentThread().getName() + " has finished his work");
        }
    }

    /**
     * numTasks指并发线程数。
     * -- 不用连接池：
     * numTasks<=100正常运行
     * numTasks>100报错“too many connections”，偶尔不报错，这是来自mysql数据库本身的限制
     * -- 采用连接池
     * numTasks>10000仍正常运行（池大小是10）
     */
    private static final int NUM_TASKS = 2000;

    @Test
    public void test() throws Exception {
        List<Runnable> workers = new LinkedList<>();
        for(int i = 0; i != NUM_TASKS; ++i) {
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
