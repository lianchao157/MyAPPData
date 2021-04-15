package activity.com.myappdata.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//JsonArrayBase.java，解析数组类型
public class JsonArrayBase<T> extends JsonBase {
    @SerializedName("data")
    List<T> data;
    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< data.size(); i++) {
            sb.append(data.get(i).toString());
        }
        return sb.toString();
    }
}
