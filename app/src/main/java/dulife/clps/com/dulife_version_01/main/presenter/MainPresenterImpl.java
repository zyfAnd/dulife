package dulife.clps.com.dulife_version_01.main.presenter;

import dulife.clps.com.dulife_version_01.R;
import dulife.clps.com.dulife_version_01.main.view.MainView;

/**
 * Created by ${zhangyanfu} on 2016/9/13.
 * Email : zhangyanfu66@gmail.com
 */
public class MainPresenterImpl implements MainPresenter {
    private MainView mMainView;

    public MainPresenterImpl(MainView mMainView) {
        this.mMainView = mMainView;
    }

    @Override
    public void switchNavigation(int id) {
        switch (id)
        {
            case R.id.nav_news:
                mMainView.switchNews();
                break;
            case R.id.nav_pic:
                mMainView.switchImage();
                break;
            case R.id.nav_about:
                mMainView.switchAbout();
                break;
        }

    }
}
