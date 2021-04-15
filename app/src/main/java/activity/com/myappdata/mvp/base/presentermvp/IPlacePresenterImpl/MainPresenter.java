package activity.com.myappdata.mvp.base.presentermvp.IPlacePresenterImpl;

import java.util.List;

import activity.com.myappdata.mvp.base.mvp.interactor.FindItemsInteractor;
import activity.com.myappdata.mvp.base.view.MainView;

public class MainPresenter {
    private static final String TAG = "MainPresenter";
    private MainView mainView;
    private FindItemsInteractor findItemsInteractor;

    public MainPresenter(MainView mainView, FindItemsInteractor findItemsInteractor) {
        this.mainView = mainView;
        this.findItemsInteractor = findItemsInteractor;
    }

    public void onResume() {
        if (mainView != null) {
            mainView.showProgress();
        }

        findItemsInteractor.findItems(this::onFinished);
    }

    public void onItemClicked(String item) {
        if (mainView != null) {
            mainView.showMessage(String.format("%s clicked", item));
        }
    }

    public void onDestroy() {
        mainView = null;
    }

    public void onFinished(List<String> items) {
        if (mainView != null) {
            mainView.setItems(items);
            mainView.hideProgress();
        }
    }

    public MainView getMainView() {
        return mainView;
    }
}
