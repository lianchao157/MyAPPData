package activity.com.myappdata.entity.typeallkindentity;

import java.io.Serializable;
//            type=new Type(i, typename+i, "");


//分类的实体类代码

public class Type implements Serializable {
    public Type(String typename) {
        this.typename = typename;
    }

    public Type() {
    }

    public Type(int typeid, String typename, String tyoeimage) {
        this.typename = typename;
        this.typeid = typeid;
        this.tyoeimage = tyoeimage;
    }

    private String typename;
    private int typeid;
    private String tyoeimage;

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getTyoeimage() {
        return tyoeimage;
    }

    public void setTyoeimage(String tyoeimage) {
        this.tyoeimage = tyoeimage;
    }

    public String getTypename() {
        return typename;
    }

    @Override
    public String toString() {
        return "Type{" +
                "typename='" + typename + '\'' +
                '}';
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}
