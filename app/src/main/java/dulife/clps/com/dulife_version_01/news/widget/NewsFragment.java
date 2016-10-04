package dulife.clps.com.dulife_version_01.news.widget;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dulife.clps.com.dulife_version_01.R;
import dulife.clps.com.dulife_version_01.bean.NewsBean;
import dulife.clps.com.dulife_version_01.news.presenter.NewsPresenter;

public class NewsFragment extends Fragment {

    private List<NewsBean> mData;
    public static final int NEWS_TYPE_TOP = 0;
    public static final int NEWS_TYPE_NBA = 1;
    public static final int NEWS_TYPE_CARS = 2;
    public static final int NEWS_TYPE_JOKES = 3;
    private NewsPresenter presenter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);

        setupViewPager();

        mTabLayout.addTab(mTabLayout.newTab().setText("头条"));
        mTabLayout.addTab(mTabLayout.newTab().setText("NBA"));
        mTabLayout.addTab(mTabLayout.newTab().setText("汽车"));
        mTabLayout.addTab(mTabLayout.newTab().setText("笑话"));
        mTabLayout.setupWithViewPager(mViewPager);
        return view;
    }


    private void setupViewPager() {
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_TOP),"头条");
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_NBA),"NBA");
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_CARS),"汽车");
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_JOKES),"笑话");
        mViewPager.setAdapter(adapter);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

}
