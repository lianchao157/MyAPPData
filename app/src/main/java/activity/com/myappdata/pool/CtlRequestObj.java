package activity.com.myappdata.pool;

class CtlRequestObj {
    private int cmd;
    private int param;
    private int state;

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getParam() {
        return param;
    }

    public void setParam(int param) {
        this.param = param;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    private CtlRequestObj() {
    }

    /**
     * 初始化对象状态
     */
    private void releaseConfig() {
        cmd = 0;
        param = 0;
        state = 0;
    }

    /**
     * 回收对象：初始化对象-->存入对象池
     */
//    public void recycle() {
//        // Clear state if needed.
//        this.releaseConfig();
//        sPool.release(this);
//    }

    //初始化线程池
//    private static final Pools.SimplePool<CtlRequestObj> sPool =
//            new Pools.SimplePool<CtlRequestObj>(Constants.CTR_REQUEST_BEANS_SPOOL_SIZE);

    /**
     * 获取（创建对象）
     * 默认从对象池中获取，拿不到就new
     *
     * @return
     */
    public static CtlRequestObj obtain() {
//        CtlRequestObj instance = sPool.acquire();
//        return (instance != null) ? instance : new CtlRequestObj();
        return null;
    }

    /**
     * 通过Builder模式创建
     */
    public static class Builder {
        private int cmd;
        private int param;
        private int state;

        public Builder() {
        }

        public CtlRequestObj.Builder setCmd(int cmd) {
            this.cmd = cmd;
            return this;
        }

        public CtlRequestObj.Builder setState(int state) {
            this.state = state;
            return this;
        }

        public CtlRequestObj.Builder setParam(int param) {
            this.param = param;
            return this;
        }

        private void applyConfig(CtlRequestObj config) {
            config.cmd = this.cmd;
            config.param = this.param;
            config.state = this.state;
        }

        public CtlRequestObj builder() {
            CtlRequestObj obtain = CtlRequestObj.obtain();
            applyConfig(obtain);
            return obtain;
        }
    }
}
