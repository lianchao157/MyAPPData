package activity.com.myappdata.bean.cash;

import java.io.Serializable;
import java.util.List;

/***
 *
 * reterfit测试实体类
 */
public class VideoEntity implements Serializable {


    private List<List<String>> result;

    public void setResult(List<List<String>> result) {
        this.result = result;
    }

    public List<List<String>> getResult() {
        return result;
    }

}
