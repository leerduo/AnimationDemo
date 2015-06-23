package me.chenfuduo.animationdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2015/6/23.
 */
public class ScreenSlidePagerActivity extends FragmentActivity {

    private static final int NUM_PAGES = 5;

    private ViewPager mPager;

    private PagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);
        mPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        mPager.setPageTransformer(true,new DepthPageTransformer());
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0){
            super.onBackPressed();
        }else{
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new ScreenSlidePageFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
public class ZoomOutPageTransFormer implements ViewPager.PageTransformer{

    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage(View view, float position) {
        Log.e("Test","view:" + view.toString() + " position:" + position);
        //log日志分析
        //=========================================================================================
        //开始运行程序：
        /*
        view:android.widget.ScrollView@535953b8 position:0.0
        view:android.widget.ScrollView@53596ad0 position:1.0
        */
        //=========================================================================================



        //=========================================================================================
        //向右拖动页面(0--->1)[535953b8--->53596ad0]
        //=========================================================================================



        /*
        06-23 07:07:24.556  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.008333334
        06-23 07:07:24.556  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.9916667
        06-23 07:07:24.592  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.07083333
        06-23 07:07:24.592  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.9291667
        06-23 07:07:24.608  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.09583333
        06-23 07:07:24.608  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.90416664
        06-23 07:07:24.624  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.125
        06-23 07:07:24.628  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.875
        06-23 07:07:24.644  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.14791666
        06-23 07:07:24.644  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.8520833
        06-23 07:07:24.660  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.17291667
        06-23 07:07:24.660  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.82708335
        06-23 07:07:24.676  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.20416667
        06-23 07:07:24.676  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.79583335
        06-23 07:07:24.692  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.24375
        06-23 07:07:24.692  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.75625
        06-23 07:07:24.708  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.28958333
        06-23 07:07:24.708  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.7104167
        06-23 07:07:24.724  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.33125
        06-23 07:07:24.724  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.66875
        06-23 07:07:24.748  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.375
        06-23 07:07:24.748  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.625
        06-23 07:07:24.760  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.42291668
        06-23 07:07:24.760  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.57708335
        06-23 07:07:24.780  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.45625
        06-23 07:07:24.780  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.54375
        06-23 07:07:24.792  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.48541668
        06-23 07:07:24.792  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.51458335
        06-23 07:07:24.808  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.51666665
        06-23 07:07:24.808  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.48333332
        06-23 07:07:24.828  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.5520833
        06-23 07:07:24.828  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.44791666
        06-23 07:07:24.848  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.57916665
        06-23 07:07:24.848  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.42083332
        06-23 07:07:24.860  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.60833335
        06-23 07:07:24.860  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.39166668
        06-23 07:07:24.876  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.6479167
        06-23 07:07:24.876  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.35208333
        06-23 07:07:24.892  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.69166666
        06-23 07:07:24.892  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.30833334
        06-23 07:07:24.908  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.72291666
        06-23 07:07:24.908  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.27708334
        06-23 07:07:24.912  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.725
        06-23 07:07:24.912  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.275
        06-23 07:07:24.912  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.7270833
        06-23 07:07:24.912  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.27291667
        06-23 07:07:24.924  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.75625
        06-23 07:07:24.924  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.24375
        06-23 07:07:24.928  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.7583333
        06-23 07:07:24.928  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.24166666
        06-23 07:07:24.944  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.7895833
        06-23 07:07:24.944  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.21041666
        06-23 07:07:24.960  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.81875
        06-23 07:07:24.960  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.18125
        06-23 07:07:24.976  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.84583336
        06-23 07:07:24.976  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.15416667
        06-23 07:07:24.992  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.86875
        06-23 07:07:24.992  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.13125
        06-23 07:07:25.008  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.8875
        06-23 07:07:25.008  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.1125
        06-23 07:07:25.012  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.88958335
        06-23 07:07:25.012  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.110416666
        06-23 07:07:25.024  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.90416664
        06-23 07:07:25.024  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.09583333
        06-23 07:07:25.044  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.92083335
        06-23 07:07:25.044  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.079166666
        06-23 07:07:25.048  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.92291665
        06-23 07:07:25.048  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.077083334
        06-23 07:07:25.060  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.93333334
        06-23 07:07:25.060  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.06666667
        06-23 07:07:25.076  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.9458333
        06-23 07:07:25.076  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.054166667
        06-23 07:07:25.092  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.95416665
        06-23 07:07:25.092  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.045833334
        06-23 07:07:25.108  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.9625
        06-23 07:07:25.108  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.0375
        06-23 07:07:25.128  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.97083336
        06-23 07:07:25.128  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.029166667
        06-23 07:07:25.140  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.975
        06-23 07:07:25.140  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.025
        06-23 07:07:25.144  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.9770833
        06-23 07:07:25.144  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.022916667
        06-23 07:07:25.164  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.98125
        06-23 07:07:25.164  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.01875
        06-23 07:07:25.176  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.98541665
        06-23 07:07:25.176  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.014583333
        06-23 07:07:25.192  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.9875
        06-23 07:07:25.192  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.0125
        06-23 07:07:25.192  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.9895833
        06-23 07:07:25.192  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.010416667
        06-23 07:07:25.208  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.9916667
        06-23 07:07:25.208  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.008333334
        06-23 07:07:25.228  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.99375
        06-23 07:07:25.228  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.00625
        06-23 07:07:25.260  12854-12857/me.chenfuduo.animationdemo D/dalvikvm﹕ GC_CONCURRENT freed 259K, 9% free 8097K/8839K, paused 11ms+0ms, total 15ms
        06-23 07:07:25.260  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.99583334
        06-23 07:07:25.260  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.004166667
        06-23 07:07:25.276  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-0.99791664
        06-23 07:07:25.276  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.0020833334
        06-23 07:07:25.328  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.0
        06-23 07:07:25.328  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:0.0
        */

        //==========================================================================

        //我们看到，第一个page的position逐渐变小，直到为-1，第二个page的position也是逐渐变小，直到为0
        //再次向右滑动(1--->2)[53596ad0--->5359cb04]

        //==========================================================================

        /*
        06-23 07:11:55.160  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.01875
        06-23 07:11:55.160  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.01875
        06-23 07:11:55.160  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.98125
        06-23 07:11:55.192  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.0875
        06-23 07:11:55.192  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.0875
        06-23 07:11:55.192  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.9125
        06-23 07:11:55.208  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.11875
        06-23 07:11:55.208  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.11875
        06-23 07:11:55.208  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.88125
        06-23 07:11:55.224  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.15
        06-23 07:11:55.224  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.15
        06-23 07:11:55.224  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.85
        06-23 07:11:55.240  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.18125
        06-23 07:11:55.240  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.18125
        06-23 07:11:55.244  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.81875
        06-23 07:11:55.260  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.2083334
        06-23 07:11:55.260  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.20833333
        06-23 07:11:55.260  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.7916667
        06-23 07:11:55.276  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.2354167
        06-23 07:11:55.276  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.23541667
        06-23 07:11:55.276  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.76458335
        06-23 07:11:55.292  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.2708334
        06-23 07:11:55.292  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.27083334
        06-23 07:11:55.292  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.7291667
        06-23 07:11:55.308  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.2958333
        06-23 07:11:55.308  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.29583332
        06-23 07:11:55.308  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.70416665
        06-23 07:11:55.324  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.325
        06-23 07:11:55.328  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.325
        06-23 07:11:55.328  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.675
        06-23 07:11:55.344  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.3583333
        06-23 07:11:55.344  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.35833332
        06-23 07:11:55.344  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.64166665
        06-23 07:11:55.360  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.4020833
        06-23 07:11:55.360  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.40208334
        06-23 07:11:55.360  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.59791666
        06-23 07:11:55.376  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.4291667
        06-23 07:11:55.376  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.42916667
        06-23 07:11:55.376  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.5708333
        06-23 07:11:55.396  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.4520833
        06-23 07:11:55.396  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.45208332
        06-23 07:11:55.396  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.54791665
        06-23 07:11:55.408  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.48125
        06-23 07:11:55.408  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.48125
        06-23 07:11:55.408  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.51875
        06-23 07:11:55.424  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.5104166
        06-23 07:11:55.424  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.5104167
        06-23 07:11:55.424  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.48958334
        06-23 07:11:55.444  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.53125
        06-23 07:11:55.444  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.53125
        06-23 07:11:55.444  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.46875
        06-23 07:11:55.464  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.5541667
        06-23 07:11:55.464  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.5541667
        06-23 07:11:55.464  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.44583333
        06-23 07:11:55.476  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.575
        06-23 07:11:55.476  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.575
        06-23 07:11:55.480  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.425
        06-23 07:11:55.492  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.5958333
        06-23 07:11:55.492  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.59583336
        06-23 07:11:55.496  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.40416667
        06-23 07:11:55.508  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.6166667
        06-23 07:11:55.508  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.6166667
        06-23 07:11:55.508  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.38333333
        06-23 07:11:55.524  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.6395833
        06-23 07:11:55.524  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.63958335
        06-23 07:11:55.524  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.36041668
        06-23 07:11:55.540  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.6604167
        06-23 07:11:55.540  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.66041666
        06-23 07:11:55.544  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.33958334
        06-23 07:11:55.560  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.675
        06-23 07:11:55.560  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.675
        06-23 07:11:55.560  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.325
        06-23 07:11:55.576  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.6895833
        06-23 07:11:55.576  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.68958336
        06-23 07:11:55.576  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.31041667
        06-23 07:11:55.592  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.70625
        06-23 07:11:55.592  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.70625
        06-23 07:11:55.592  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.29375
        06-23 07:11:55.608  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.7229167
        06-23 07:11:55.608  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.72291666
        06-23 07:11:55.612  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.27708334
        06-23 07:11:55.624  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.7416667
        06-23 07:11:55.628  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.7416667
        06-23 07:11:55.628  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.25833333
        06-23 07:11:55.640  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.7541667
        06-23 07:11:55.640  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.75416666
        06-23 07:11:55.644  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.24583334
        06-23 07:11:55.660  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.7708334
        06-23 07:11:55.660  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.7708333
        06-23 07:11:55.660  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.22916667
        06-23 07:11:55.676  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.7958333
        06-23 07:11:55.676  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.79583335
        06-23 07:11:55.676  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.20416667
        06-23 07:11:55.692  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.825
        06-23 07:11:55.692  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.825
        06-23 07:11:55.692  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.175
        06-23 07:11:55.708  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.8520833
        06-23 07:11:55.708  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.8520833
        06-23 07:11:55.708  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.14791666
        06-23 07:11:55.724  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.8666667
        06-23 07:11:55.724  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.8666667
        06-23 07:11:55.724  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.13333334
        06-23 07:11:55.728  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.86875
        06-23 07:11:55.732  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.86875
        06-23 07:11:55.732  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.13125
        06-23 07:11:55.732  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.8708333
        06-23 07:11:55.732  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.87083334
        06-23 07:11:55.732  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.12916666
        06-23 07:11:55.740  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.8833333
        06-23 07:11:55.740  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.8833333
        06-23 07:11:55.744  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.11666667
        06-23 07:11:55.744  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.8875
        06-23 07:11:55.748  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.8875
        06-23 07:11:55.748  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.1125
        06-23 07:11:55.760  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.9020833
        06-23 07:11:55.760  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.90208334
        06-23 07:11:55.760  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.09791667
        06-23 07:11:55.776  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.9125
        06-23 07:11:55.776  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.9125
        06-23 07:11:55.776  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.0875
        06-23 07:11:55.776  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.9145833
        06-23 07:11:55.776  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.9145833
        06-23 07:11:55.776  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.08541667
        06-23 07:11:55.792  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.9270834
        06-23 07:11:55.796  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.9270833
        06-23 07:11:55.796  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.072916664
        06-23 07:11:55.808  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.9375
        06-23 07:11:55.808  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.9375
        06-23 07:11:55.812  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.0625
        06-23 07:11:55.832  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.9479166
        06-23 07:11:55.832  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.9479167
        06-23 07:11:55.832  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.052083332
        06-23 07:11:55.832  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.95
        06-23 07:11:55.832  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.95
        06-23 07:11:55.832  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.05
        06-23 07:11:55.844  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.9541667
        06-23 07:11:55.844  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.95416665
        06-23 07:11:55.844  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.045833334
        06-23 07:11:55.860  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.9625
        06-23 07:11:55.860  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.9625
        06-23 07:11:55.860  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.0375
        06-23 07:11:55.864  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.9645833
        06-23 07:11:55.864  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.96458334
        06-23 07:11:55.864  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.035416666
        06-23 07:11:55.876  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.96875
        06-23 07:11:55.876  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.96875
        06-23 07:11:55.876  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.03125
        06-23 07:11:55.892  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.9729167
        06-23 07:11:55.892  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.97291666
        06-23 07:11:55.892  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.027083334
        06-23 07:11:55.908  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.9770833
        06-23 07:11:55.908  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.9770833
        06-23 07:11:55.908  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.022916667
        06-23 07:11:55.912  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.9791666
        06-23 07:11:55.912  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.9791667
        06-23 07:11:55.912  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.020833334
        06-23 07:11:55.924  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.9833333
        06-23 07:11:55.924  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.98333335
        06-23 07:11:55.928  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.016666668
        06-23 07:11:55.944  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.9854167
        06-23 07:11:55.944  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.98541665
        06-23 07:11:55.944  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.014583333
        06-23 07:11:55.960  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.9895834
        06-23 07:11:55.960  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.9895833
        06-23 07:11:55.960  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.010416667
        06-23 07:11:55.976  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.9916667
        06-23 07:11:55.976  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.9916667
        06-23 07:11:55.976  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.008333334
        06-23 07:11:55.992  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.99375
        06-23 07:11:55.992  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.99375
        06-23 07:11:55.992  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.00625
        06-23 07:11:56.024  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.9958333
        06-23 07:11:56.024  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.99583334
        06-23 07:11:56.024  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.004166667
        06-23 07:11:56.044  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-1.9979167
        06-23 07:11:56.044  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-0.99791664
        06-23 07:11:56.044  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.0020833334
        06-23 07:11:56.108  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535953b8 position:-2.0
        06-23 07:11:56.108  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.0
        06-23 07:11:56.108  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:0.0
        */



        //=========================================================================================
        //我们看到第一个page的position变为-2，第二个page的position变为-1，第三个page的position变为0
        //再次向右滑动[2--->3](5359cb04--->535a4b88)
        //=========================================================================================

        /*
        06-23 07:16:40.824  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.0208334
06-23 07:16:40.824  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.020833334
06-23 07:16:40.824  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.9791667
06-23 07:16:40.840  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.0520834
06-23 07:16:40.840  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.052083332
06-23 07:16:40.840  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.9479167
06-23 07:16:40.860  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.0875
06-23 07:16:40.860  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.0875
06-23 07:16:40.864  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.9125
06-23 07:16:40.872  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.125
06-23 07:16:40.872  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.125
06-23 07:16:40.876  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.875
06-23 07:16:40.892  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.1666666
06-23 07:16:40.892  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.16666667
06-23 07:16:40.892  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.8333333
06-23 07:16:40.908  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.2166667
06-23 07:16:40.908  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.21666667
06-23 07:16:40.908  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.78333336
06-23 07:16:40.924  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.2541667
06-23 07:16:40.924  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.25416666
06-23 07:16:40.924  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.74583334
06-23 07:16:40.940  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.2916666
06-23 07:16:40.940  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.29166666
06-23 07:16:40.940  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.7083333
06-23 07:16:40.956  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.3333334
06-23 07:16:40.956  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.33333334
06-23 07:16:40.956  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.6666667
06-23 07:16:40.972  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.3625
06-23 07:16:40.972  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.3625
06-23 07:16:40.972  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.6375
06-23 07:16:40.992  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.3979167
06-23 07:16:40.992  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.39791667
06-23 07:16:40.992  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.6020833
06-23 07:16:41.008  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.4291667
06-23 07:16:41.008  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.42916667
06-23 07:16:41.008  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.5708333
06-23 07:16:41.040  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.4604167
06-23 07:16:41.040  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.46041667
06-23 07:16:41.040  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.5395833
06-23 07:16:41.056  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.5208334
06-23 07:16:41.056  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.5208333
06-23 07:16:41.056  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.47916666
06-23 07:16:41.072  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.55
06-23 07:16:41.072  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.55
06-23 07:16:41.076  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.45
06-23 07:16:41.092  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.5770833
06-23 07:16:41.092  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.57708335
06-23 07:16:41.092  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.42291668
06-23 07:16:41.108  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.5979167
06-23 07:16:41.108  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.59791666
06-23 07:16:41.108  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.40208334
06-23 07:16:41.124  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.6125
06-23 07:16:41.124  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.6125
06-23 07:16:41.132  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.3875
06-23 07:16:41.140  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.6270833
06-23 07:16:41.140  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.62708336
06-23 07:16:41.140  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.37291667
06-23 07:16:41.156  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.64375
06-23 07:16:41.156  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.64375
06-23 07:16:41.156  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.35625
06-23 07:16:41.172  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.6666666
06-23 07:16:41.172  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.6666667
06-23 07:16:41.176  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.33333334
06-23 07:16:41.192  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.6875
06-23 07:16:41.192  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.6875
06-23 07:16:41.192  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.3125
06-23 07:16:41.208  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.7104167
06-23 07:16:41.208  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.7104167
06-23 07:16:41.208  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.28958333
06-23 07:16:41.224  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.7270833
06-23 07:16:41.224  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.7270833
06-23 07:16:41.224  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.27291667
06-23 07:16:41.240  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.74375
06-23 07:16:41.240  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.74375
06-23 07:16:41.240  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.25625
06-23 07:16:41.256  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.75625
06-23 07:16:41.256  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.75625
06-23 07:16:41.256  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.24375
06-23 07:16:41.276  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.76875
06-23 07:16:41.276  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.76875
06-23 07:16:41.276  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.23125
06-23 07:16:41.292  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.7833333
06-23 07:16:41.292  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.78333336
06-23 07:16:41.292  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.21666667
06-23 07:16:41.308  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.7979167
06-23 07:16:41.308  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.79791665
06-23 07:16:41.308  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.20208333
06-23 07:16:41.324  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.8125
06-23 07:16:41.324  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.8125
06-23 07:16:41.324  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.1875
06-23 07:16:41.344  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.8354167
06-23 07:16:41.344  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.8354167
06-23 07:16:41.344  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.16458334
06-23 07:16:41.360  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.8645834
06-23 07:16:41.360  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.8645833
06-23 07:16:41.360  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.13541667
06-23 07:16:41.376  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.8916667
06-23 07:16:41.376  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.89166665
06-23 07:16:41.376  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.108333334
06-23 07:16:41.392  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.90625
06-23 07:16:41.392  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.90625
06-23 07:16:41.392  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.09375
06-23 07:16:41.408  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.91875
06-23 07:16:41.408  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.91875
06-23 07:16:41.408  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.08125
06-23 07:16:41.428  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.93125
06-23 07:16:41.428  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.93125
06-23 07:16:41.428  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.06875
06-23 07:16:41.428  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.9333333
06-23 07:16:41.428  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.93333334
06-23 07:16:41.428  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.06666667
06-23 07:16:41.440  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.9395833
06-23 07:16:41.440  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.93958336
06-23 07:16:41.444  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.060416665
06-23 07:16:41.444  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.9416667
06-23 07:16:41.448  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.94166666
06-23 07:16:41.448  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.058333334
06-23 07:16:41.460  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.95
06-23 07:16:41.460  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.95
06-23 07:16:41.464  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.05
06-23 07:16:41.476  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.95625
06-23 07:16:41.476  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.95625
06-23 07:16:41.476  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.04375
06-23 07:16:41.492  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.9625
06-23 07:16:41.492  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.9625
06-23 07:16:41.492  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.0375
06-23 07:16:41.508  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.96875
06-23 07:16:41.508  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.96875
06-23 07:16:41.508  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.03125
06-23 07:16:41.524  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.975
06-23 07:16:41.524  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.975
06-23 07:16:41.528  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.025
06-23 07:16:41.540  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.9791666
06-23 07:16:41.540  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.9791667
06-23 07:16:41.540  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.020833334
06-23 07:16:41.556  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.9833333
06-23 07:16:41.556  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.98333335
06-23 07:16:41.560  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.016666668
06-23 07:16:41.576  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.9854167
06-23 07:16:41.576  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.98541665
06-23 07:16:41.576  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.014583333
06-23 07:16:41.592  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.9875
06-23 07:16:41.592  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.9875
06-23 07:16:41.592  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.0125
06-23 07:16:41.608  12854-12857/me.chenfuduo.animationdemo D/dalvikvm﹕ GC_CONCURRENT freed 382K, 11% free 8106K/9031K, paused 11ms+0ms, total 14ms
06-23 07:16:41.608  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.9895834
06-23 07:16:41.608  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.9895833
06-23 07:16:41.608  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.010416667
06-23 07:16:41.616  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.9916667
06-23 07:16:41.616  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.9916667
06-23 07:16:41.616  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.008333334
06-23 07:16:41.624  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.99375
06-23 07:16:41.624  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.99375
06-23 07:16:41.628  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.00625
06-23 07:16:41.660  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.9958333
06-23 07:16:41.660  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.99583334
06-23 07:16:41.660  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.004166667
06-23 07:16:41.692  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-1.9979167
06-23 07:16:41.696  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-0.99791664
06-23 07:16:41.696  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.0020833334
06-23 07:16:41.744  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@53596ad0 position:-2.0
06-23 07:16:41.744  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.0
06-23 07:16:41.744  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:0.0

         */


        //=========================================================================================
        //第一个消失了，第二个变为-2，第三个变为-1，第四个变为0
        //再次向右拖动[3--->4](535a4b88--->5358da20)
        //=========================================================================================
        /*


        06-23 07:20:12.992  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.0145833
06-23 07:20:12.992  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.014583333
06-23 07:20:12.992  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.98541665
06-23 07:20:13.008  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.05625
06-23 07:20:13.008  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.05625
06-23 07:20:13.008  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.94375
06-23 07:20:13.024  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.1
06-23 07:20:13.024  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.1
06-23 07:20:13.028  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.9
06-23 07:20:13.068  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.125
06-23 07:20:13.068  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.125
06-23 07:20:13.068  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.875
06-23 07:20:13.092  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.1833333
06-23 07:20:13.092  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.18333334
06-23 07:20:13.092  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.81666666
06-23 07:20:13.108  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.2104167
06-23 07:20:13.108  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.21041666
06-23 07:20:13.108  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.7895833
06-23 07:20:13.124  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.25625
06-23 07:20:13.124  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.25625
06-23 07:20:13.128  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.74375
06-23 07:20:13.140  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.3
06-23 07:20:13.140  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.3
06-23 07:20:13.140  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.7
06-23 07:20:13.156  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.3416667
06-23 07:20:13.156  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.34166667
06-23 07:20:13.156  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.65833336
06-23 07:20:13.176  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.375
06-23 07:20:13.176  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.375
06-23 07:20:13.176  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.625
06-23 07:20:13.192  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.3979167
06-23 07:20:13.192  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.39791667
06-23 07:20:13.192  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.6020833
06-23 07:20:13.208  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.4208333
06-23 07:20:13.208  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.42083332
06-23 07:20:13.208  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.57916665
06-23 07:20:13.224  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.4479166
06-23 07:20:13.224  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.44791666
06-23 07:20:13.224  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.5520833
06-23 07:20:13.240  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.475
06-23 07:20:13.240  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.475
06-23 07:20:13.240  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.525
06-23 07:20:13.260  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.5041667
06-23 07:20:13.260  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.50416666
06-23 07:20:13.260  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.49583334
06-23 07:20:13.272  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.5291667
06-23 07:20:13.272  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.52916664
06-23 07:20:13.272  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.47083333
06-23 07:20:13.292  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.5625
06-23 07:20:13.292  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.5625
06-23 07:20:13.292  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.4375
06-23 07:20:13.308  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.5854167
06-23 07:20:13.308  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.5854167
06-23 07:20:13.308  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.41458333
06-23 07:20:13.324  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.6145834
06-23 07:20:13.324  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.6145833
06-23 07:20:13.324  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.38541666
06-23 07:20:13.340  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.6354166
06-23 07:20:13.340  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.6354167
06-23 07:20:13.340  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.36458334
06-23 07:20:13.356  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.65625
06-23 07:20:13.356  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.65625
06-23 07:20:13.356  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.34375
06-23 07:20:13.372  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.6708333
06-23 07:20:13.372  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.67083335
06-23 07:20:13.376  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.32916668
06-23 07:20:13.392  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.6875
06-23 07:20:13.392  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.6875
06-23 07:20:13.396  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.3125
06-23 07:20:13.408  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.7020833
06-23 07:20:13.408  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.70208335
06-23 07:20:13.412  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.29791668
06-23 07:20:13.424  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.7166667
06-23 07:20:13.424  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.71666664
06-23 07:20:13.424  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.28333333
06-23 07:20:13.440  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.73125
06-23 07:20:13.440  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.73125
06-23 07:20:13.440  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.26875
06-23 07:20:13.460  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.7520833
06-23 07:20:13.460  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.75208336
06-23 07:20:13.460  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.24791667
06-23 07:20:13.476  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.7708334
06-23 07:20:13.476  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.7708333
06-23 07:20:13.476  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.22916667
06-23 07:20:13.492  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.79375
06-23 07:20:13.492  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.79375
06-23 07:20:13.492  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.20625
06-23 07:20:13.508  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.81875
06-23 07:20:13.508  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.81875
06-23 07:20:13.508  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.18125
06-23 07:20:13.524  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.8458333
06-23 07:20:13.524  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.84583336
06-23 07:20:13.524  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.15416667
06-23 07:20:13.540  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.8666667
06-23 07:20:13.540  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.8666667
06-23 07:20:13.540  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.13333334
06-23 07:20:13.556  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.8854166
06-23 07:20:13.556  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.8854167
06-23 07:20:13.556  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.114583336
06-23 07:20:13.572  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.8958334
06-23 07:20:13.572  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.8958333
06-23 07:20:13.572  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.104166664
06-23 07:20:13.596  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.9104167
06-23 07:20:13.596  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.91041666
06-23 07:20:13.596  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.08958333
06-23 07:20:13.612  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.925
06-23 07:20:13.612  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.925
06-23 07:20:13.612  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.075
06-23 07:20:13.624  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.9333333
06-23 07:20:13.624  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.93333334
06-23 07:20:13.628  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.06666667
06-23 07:20:13.632  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.9354167
06-23 07:20:13.632  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.93541664
06-23 07:20:13.632  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.06458333
06-23 07:20:13.644  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.94375
06-23 07:20:13.644  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.94375
06-23 07:20:13.644  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.05625
06-23 07:20:13.660  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.95
06-23 07:20:13.660  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.95
06-23 07:20:13.660  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.05
06-23 07:20:13.676  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.9583334
06-23 07:20:13.676  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.9583333
06-23 07:20:13.676  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.041666668
06-23 07:20:13.676  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.9604167
06-23 07:20:13.680  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.9604167
06-23 07:20:13.680  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.039583333
06-23 07:20:13.692  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.9645833
06-23 07:20:13.692  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.96458334
06-23 07:20:13.692  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.035416666
06-23 07:20:13.712  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.9708333
06-23 07:20:13.712  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.97083336
06-23 07:20:13.712  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.029166667
06-23 07:20:13.724  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.975
06-23 07:20:13.728  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.975
06-23 07:20:13.728  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.025
06-23 07:20:13.740  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.9791666
06-23 07:20:13.740  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.9791667
06-23 07:20:13.744  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.020833334
06-23 07:20:13.760  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.9833333
06-23 07:20:13.760  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.98333335
06-23 07:20:13.760  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.016666668
06-23 07:20:13.776  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.9854167
06-23 07:20:13.776  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.98541665
06-23 07:20:13.776  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.014583333
06-23 07:20:13.792  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.9895834
06-23 07:20:13.792  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.9895833
06-23 07:20:13.792  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.010416667
06-23 07:20:13.808  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.9916667
06-23 07:20:13.808  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.9916667
06-23 07:20:13.812  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.008333334
06-23 07:20:13.828  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.99375
06-23 07:20:13.828  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.99375
06-23 07:20:13.828  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.00625
06-23 07:20:13.860  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.9958333
06-23 07:20:13.860  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.99583334
06-23 07:20:13.860  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.004166667
06-23 07:20:13.892  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-1.9979167
06-23 07:20:13.892  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-0.99791664
06-23 07:20:13.892  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.0020833334
06-23 07:20:13.940  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5359cb04 position:-2.0
06-23 07:20:13.940  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@535a4b88 position:-1.0
06-23 07:20:13.940  12854-12854/me.chenfuduo.animationdemo E/Test﹕ view:android.widget.ScrollView@5358da20 position:0.0


         */

        //=====================================================================================================
        //第三个变为-2.0，第五个变为0
        //向左滑动的变化是一样的，只不过是逆向的，为正，最大是2.
        //=====================================================================================================

        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -2){
            view.setAlpha(0);//刚才的分析
        }else if (position <= 2){//[-2,2]
            float scaleFactor = Math.max(MIN_SCALE,1-Math.abs(position));
            float vertMargin = pageHeight * (1-scaleFactor) / 2;
            float horzMargin = pageWidth * (1-scaleFactor) / 2;
            if (position < 0){
                view.setTranslationX(horzMargin - vertMargin / 2);
            }else{
                view.setTranslationX(-horzMargin + vertMargin / 2);
            }
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
            view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1-MIN_SCALE) * (1-MIN_ALPHA));
        }else{
            view.setAlpha(0);
        }
    }
}
    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
}
