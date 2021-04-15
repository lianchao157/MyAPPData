package activity.com.myappdata.mvp.base.modelmvp.entity;

//import dagger.Module;
//import dagger.Provides;

//@Module
public class CityModel {


    public CityModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private int id;
    private String name;

//    @Provides
    String provideName(){
        return name;
    }

//    @Provides
    int provideid(){
        return id;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
