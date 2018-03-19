package com.panku.shopping.activity;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.chaychan.library.BottomBarItem;
import com.chaychan.library.BottomBarLayout;
import com.panku.shopping.R;
import com.panku.shopping.fragment.HomeFragment;
import com.panku.shopping.fragment.MineFragment;
import com.panku.shopping.fragment.OtherFragment;
import com.panku.shopping.fragment.ShoppingFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private ViewPager viewPager;
    private BottomBarLayout bottomBarLayout;
    private RotateAnimation mRotateAnimation;
    private List<Fragment> fragments = new ArrayList<>();
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDate();
    }

    private void initView() {
        viewPager = findViewById(R.id.vp);
        bottomBarLayout = findViewById(R.id.bbl);
    }

    private void initDate() {
        fragments.add(new HomeFragment());
        fragments.add(new ShoppingFragment());
        fragments.add(new OtherFragment());
        fragments.add(new MineFragment());
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        bottomBarLayout.setViewPager(viewPager);
        bottomBarLayout.setSmoothScroll(true);
        bottomBarLayout.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final BottomBarItem bottomBarItem, int i, final int i1) {
                if (i1 == 0) {
                    //如果是第一个，即首页
                    if (i == i1) {
                        //如果是在原来位置上点击,更换首页图标并播放旋转动画
                        bottomBarItem.setIconSelectedResourceId(R.mipmap.tab_loading);//更换成加载图标
                        bottomBarItem.setStatus(true);

                        //播放旋转动画
                        if (mRotateAnimation == null) {
                            mRotateAnimation = new RotateAnimation(0, 360,
                                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                                    0.5f);
                            mRotateAnimation.setDuration(800);
                            mRotateAnimation.setRepeatCount(-1);
                        }
                        ImageView bottomImageView = bottomBarItem.getImageView();
                        bottomImageView.setAnimation(mRotateAnimation);
                        bottomImageView.startAnimation(mRotateAnimation);//播放旋转动画

                        //模拟数据刷新完毕
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                boolean tabNotChanged = bottomBarLayout.getCurrentItem() == i1; //是否还停留在当前页签
                                bottomBarItem.setIconSelectedResourceId(R.mipmap.tab_home_selected);//更换成首页原来选中图标
                                bottomBarItem.setStatus(tabNotChanged);//刷新图标
                                cancelTabLoading(bottomBarItem);
                            }
                        }, 3000);
                        return;
                    }
                }
                //如果点击了其他条目
                BottomBarItem bottomItem = bottomBarLayout.getBottomItem(0);
                bottomItem.setIconSelectedResourceId(R.mipmap.tab_home_selected);//更换为原来的图标
                cancelTabLoading(bottomItem);//停止旋转动画
            }
        });
//        bottomBarLayout.setUnread(0, 20);//设置第一个页签的未读数为20
//        bottomBarLayout.setUnread(1, 1);//设置第二个页签的未读数
//        bottomBarLayout.showNotify(2);//设置第三个页签显示提示的小红点
//        bottomBarLayout.setMsg(3, "NEW");//设置第四个页签显示NEW提示文字
    }

    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


    /**
     * 停止首页页签的旋转动画
     */
    private void cancelTabLoading(BottomBarItem bottomItem) {
        Animation animation = bottomItem.getImageView().getAnimation();
        if (animation != null) {
            animation.cancel();
        }
    }
}
