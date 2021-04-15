package activity.com.myappdata.api;

import java.util.List;

import activity.com.myappdata.mvp.base.modelmvp.entity.City;
import io.reactivex.Observable;
import retrofit2.Callback;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/TP_S/BookList")
    Observable<JsonArrayBase<Book>> queryBookList();


    @GET("/users")
    public void getUsers(Callback<List<City>> callback);

}
