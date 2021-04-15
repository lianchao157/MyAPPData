package activity.com.myappdata.mvp.base.modelmvp.mvploginentity.mvpuserinfolitit;

import java.util.List;

public class MVPUserinfoData {

        private boolean flag;
        private String msg;
        private List<UserinfoBywebData> data;
        private int count;
        public void setFlag(boolean flag) {
            this.flag = flag;
        }
        public boolean getFlag() {
            return flag;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
        public String getMsg() {
            return msg;
        }

        public void setData(List<UserinfoBywebData> data) {
            this.data = data;
        }
        public List<UserinfoBywebData> getData() {
            return data;
        }

        public void setCount(int count) {
            this.count = count;
        }
        public int getCount() {
            return count;
        }
}
