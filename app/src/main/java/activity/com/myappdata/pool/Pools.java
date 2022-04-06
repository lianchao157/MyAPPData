package activity.com.myappdata.pool;

import android.support.annotation.Nullable;

import io.reactivex.annotations.NonNull;

/***
 * 022.3.28
 * 创建线程池
 */
public final class Pools {

    public interface Pool<T> {

        /**
         * 获取对象
         */
        @Nullable
        T acquire();

        /**
         *释放对象
         */
        boolean release(@NonNull T instance);
    }

    private Pools() {
        /* do nothing - hiding constructor */
    }

    /**
     * 非同步对象池.
     */
    public static class SimplePool<T> implements Pool<T> {
        private final Object[] mPool;

        private int mPoolSize;

        /**
         * 创建一个对象池，并指定最大存入对象数量
         */
        public SimplePool(int maxPoolSize) {
            if (maxPoolSize <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }
            mPool = new Object[maxPoolSize];
        }

        @Override
        @SuppressWarnings("unchecked")
        public T acquire() {
            if (mPoolSize > 0) {
                final int lastPooledIndex = mPoolSize - 1;
                T instance = (T) mPool[lastPooledIndex];
                mPool[lastPooledIndex] = null;
                mPoolSize--;
                return instance;
            }
            return null;
        }

        @Override
        public boolean release(@NonNull T instance) {
            if (isInPool(instance)) {
                throw new IllegalStateException("Already in the pool!");
            }
            if (mPoolSize < mPool.length) {
                mPool[mPoolSize] = instance;
                mPoolSize++;
                return true;
            }
            return false;
        }

        private boolean isInPool(@NonNull T instance) {
            for (int i = 0; i < mPoolSize; i++) {
                if (mPool[i] == instance) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 同步对象池
     */
    public static class SynchronizedPool<T> extends SimplePool<T> {
        private final Object mLock = new Object();
        public SynchronizedPool(int maxPoolSize) {
            super(maxPoolSize);
        }

        @Override
        public T acquire() {
            //通过synchronized实现同步
            synchronized (mLock) {
                return super.acquire();
            }
        }

        @Override
        public boolean release(@NonNull T element) {
            synchronized (mLock) {
                return super.release(element);
            }
        }
    }
}
