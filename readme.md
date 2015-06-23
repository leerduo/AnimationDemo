#淡入淡出两个View
淡入淡出效果提供了从一个屏幕到另一个屏幕的平滑的过度，一个View的淡出同时伴随着另一个View的淡入。
##创建View对象
```xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/content"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView style="?android:textAppearanceMedium"
            android:lineSpacingMultiplier="1.2"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@string/lorem_ipsum"
            android:padding="16dp" />

    </ScrollView>

    <ProgressBar android:id="@+id/loading_spinner"
        style="?android:progressBarStyleLarge"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center" />


</FrameLayout>
```
布局为帧布局。
##建立动画
* 创建需要淡入淡出的两个View对象，以及一个时间变量
* 对于那个要淡入的View对象，开始的时候将其visibility设置为GONE。这样可以防止View对象占据Layout空间。
* 时间变量使用的是系统的值。
如下：
```java
 private View mContentView;
    private View mLoadingView;
    private int mShortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContentView = findViewById(R.id.content);
        mLoadingView = findViewById(R.id.loading_spinner);
        // Initially hide the content view.
        mContentView.setVisibility(View.GONE);
        //200
        mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
    }
```
##淡入淡出View对象
这里，我在ActionBar上添加了一个按钮，用来去响应事件。
* 对于要淡入的View，将其alpha设置为0，并将visibility设置为VISIBLE。(此时View对象可见但是完全透明的)
* 对于要淡入的View，透明度动画的变化是从0到1，与此同时，对于淡出的view，透明度动画的变化是从1到0
* 使用`AnimatorListenerAdapter()`的`onAnimationEnd(Animator animation)`方法。将淡出的View对象的visibility设置为GONE。
```java
 @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            crossfade();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void crossfade() {
        mContentView.setAlpha(0f);
        mContentView.setVisibility(View.VISIBLE);


        mContentView.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration)
                .setListener(null);

        mLoadingView.animate()
                .alpha(0f)
                .setDuration(mShortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mLoadingView.setVisibility(View.GONE);
                    }
                });

    }
```
#使用ViewPager滑动屏幕
屏幕滑动是从一个屏幕滑动到另一个屏幕的过渡，比如设置向导界面。
##创建View对象
这个布局是Fragment的布局。
```xml
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/content"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

    <TextView style="?android:textAppearanceMedium"
        android:padding="16dp"
        android:lineSpacingMultiplier="1.2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/lorem_ipsum" />
</ScrollView>
```
##创建Fragment
在这个demo中，ScreenSlidePagerActivity负责管理这样的五个Fragment。
```java
package me.chenfuduo.animationdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2015/6/23.
 */
public class ScreenSlidePageFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);
        return rootView;
    }
}
```
##添加ViewPager
```xml
<!-- activity_screen_slide.xml -->
<android.support.v4.view.ViewPager
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/pager"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```
##将Fragment添加到ViewPager
```java
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
...
public class ScreenSlidePagerActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
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
}
```
这里，我们继承的PagerAdapter是`FragmentStatePagerAdapter`，需要提供一个构造器，并重写`getItem(int)`和`getCount()`方法。
继承不同的Adapter，对后面的可能会有影响。
##使用`PageTransformer`自定义动画
定义一个类，实现`PageTransformer`接口，并重写`transformPage(View view, float position)`方法。
> 需要注意的是：它提到这样的一句话：At each point in the screen's transition, this method is called once for each visible page (generally there's only one visible page) and for adjacent pages just off the screen. For example, if page three is visible and the user drags towards page four, transformPage() is called for pages two, three, and four at each step of the gesture.
从后面的log输出中，也可以清晰的验证这句话。

##缩小页面转换
```java
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
```
效果图如下:
![1](http://1.infotravel.sinaapp.com/pic/test.gif)
##深度页面转换
```java
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
```
执行后的动画的效果：
![2](http://1.infotravel.sinaapp.com/pic/5.gif)
> 这里提到的两个动画还是很不错的，以后要用到这个。

#卡片翻转动画
整体的效果如下图：
![3](http://1.infotravel.sinaapp.com/pic/6.gif)
##创建Animators
我们一共需要四个Animators，上面的卡片从左边进来和出去两个，下面的卡片从右边进来和出去两个。
card_flip_left_in.xml
```xml
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- Before rotating, immediately set the alpha to 0. -->
    <objectAnimator
        android:valueFrom="1.0"
        android:valueTo="0.0"
        android:propertyName="alpha"
        android:duration="0" />

    <!-- Rotate. -->
    <objectAnimator
        android:valueFrom="-180"
        android:valueTo="0"
        android:propertyName="rotationY"
        android:interpolator="@android:interpolator/accelerate_decelerate"
        android:duration="@integer/card_flip_time_full" />

    <!-- Half-way through the rotation (see startOffset), set the alpha to 1. -->
    <objectAnimator
        android:valueFrom="0.0"
        android:valueTo="1.0"
        android:propertyName="alpha"
        android:startOffset="@integer/card_flip_time_half"
        android:duration="1" />
</set>
```
card_flip_left_out.xml
```xml
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- Rotate. -->
    <objectAnimator
        android:valueFrom="0"
        android:valueTo="180"
        android:propertyName="rotationY"
        android:interpolator="@android:interpolator/accelerate_decelerate"
        android:duration="@integer/card_flip_time_full" />

    <!-- Half-way through the rotation (see startOffset), set the alpha to 0. -->
    <objectAnimator
        android:valueFrom="1.0"
        android:valueTo="0.0"
        android:propertyName="alpha"
        android:startOffset="@integer/card_flip_time_half"
        android:duration="1" />
</set>
```
card_flip_right_in.xml
```xml
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- Before rotating, immediately set the alpha to 0. -->
    <objectAnimator
        android:valueFrom="1.0"
        android:valueTo="0.0"
        android:propertyName="alpha"
        android:duration="0" />

    <!-- Rotate. -->
    <objectAnimator
        android:valueFrom="180"
        android:valueTo="0"
        android:propertyName="rotationY"
        android:interpolator="@android:interpolator/accelerate_decelerate"
        android:duration="@integer/card_flip_time_full" />

    <!-- Half-way through the rotation (see startOffset), set the alpha to 1. -->
    <objectAnimator
        android:valueFrom="0.0"
        android:valueTo="1.0"
        android:propertyName="alpha"
        android:startOffset="@integer/card_flip_time_half"
        android:duration="1" />
</set>
```
card_flip_right_out.xml
```xml
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- Rotate. -->
    <objectAnimator
        android:valueFrom="0"
        android:valueTo="-180"
        android:propertyName="rotationY"
        android:interpolator="@android:interpolator/accelerate_decelerate"
        android:duration="@integer/card_flip_time_full" />

    <!-- Half-way through the rotation (see startOffset), set the alpha to 0. -->
    <objectAnimator
        android:valueFrom="1.0"
        android:valueTo="0.0"
        android:propertyName="alpha"
        android:startOffset="@integer/card_flip_time_half"
        android:duration="1" />
</set>
```
##创建View对象
分为上面的卡片的部分和下面的卡片的部分
上面的：
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:background="#a6c"
    android:padding="16dp"
    android:gravity="bottom">

    <TextView android:id="@android:id/text1"
        style="?android:textAppearanceLarge"
        android:textStyle="bold"
        android:textColor="#fff"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/card_back_title" />

    <TextView style="?android:textAppearanceSmall"
        android:textAllCaps="true"
        android:textColor="#80ffffff"
        android:textStyle="bold"
        android:lineSpacingMultiplier="1.2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/card_back_description" />

</LinearLayout>
```
下面的：
```xml
<ImageView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:src="@drawable/image1"
    android:scaleType="centerCrop"
    android:contentDescription="@string/description_image_1" />
```
##创建Fragment
分别创建上面的和下面的Fragment。注意这里选择v4的包。我的代码里`getFragmentManager()`替换为`getSupportFragmentManager()`。这样会引发下面的错误。见下文。
```java
 /**
     * A fragment representing the front of the card.
     */
    public class CardFrontFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_card_front, container, false);
        }
    }

    /**
     * A fragment representing the back of the card.
     */
    public class CardBackFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_card_back, container, false);
        }
    }
```
##翻转卡片
首先添加Activity的布局：
```xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/container"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```
先是上面的卡片：
```java
if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardFrontFragment())
                    .commit();
        }
```
我在ActionBar上面添加一个按钮，响应翻转的事件。
```java
private void flipCard() {
    if (mShowingBack) {
        getFragmentManager().popBackStack();
        return;
    }

    // Flip to the back.

    mShowingBack = true;

    // Create and commit a new fragment transaction that adds the fragment for the back of
    // the card, uses custom animations, and is part of the fragment manager's back stack.

    getFragmentManager()
            .beginTransaction()

            // Replace the default fragment animations with animator resources representing
            // rotations when switching to the back of the card, as well as animator
            // resources representing rotations when flipping back to the front (e.g. when
            // the system Back button is pressed).
            .setCustomAnimations(
                    R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                    R.animator.card_flip_left_in, R.animator.card_flip_left_out)

            // Replace any fragments currently in the container view with a fragment
            // representing the next page (indicated by the just-incremented currentPage
            // variable).
            .replace(R.id.container, new CardBackFragment())

            // Add this transaction to the back stack, allowing users to press Back
            // to get to the front of the card.
            .addToBackStack(null)

            // Commit the transaction.
            .commit();
}
```
此时运行程序，出现下面的错误：
```xml
06-23 08:31:28.649  20948-20948/me.chenfuduo.animationdemo D/AndroidRuntime﹕ Shutting down VM
06-23 08:31:28.649  20948-20948/me.chenfuduo.animationdemo W/dalvikvm﹕ threadid=1: thread exiting with uncaught exception (group=0xa62c4288)
06-23 08:31:28.649  20948-20948/me.chenfuduo.animationdemo E/AndroidRuntime﹕ FATAL EXCEPTION: main
    java.lang.RuntimeException: Unknown animation name: objectAnimator
            at android.view.animation.AnimationUtils.createAnimationFromXml(AnimationUtils.java:124)
            at android.view.animation.AnimationUtils.createAnimationFromXml(AnimationUtils.java:114)
            at android.view.animation.AnimationUtils.createAnimationFromXml(AnimationUtils.java:91)
            at android.view.animation.AnimationUtils.loadAnimation(AnimationUtils.java:72)
            at android.support.v4.app.FragmentManagerImpl.loadAnimation(FragmentManager.java:788)
            at android.support.v4.app.FragmentManagerImpl.moveToState(FragmentManager.java:1032)
            at android.support.v4.app.FragmentManagerImpl.removeFragment(FragmentManager.java:1235)
            at android.support.v4.app.BackStackRecord.run(BackStackRecord.java:697)
            at android.support.v4.app.FragmentManagerImpl.execPendingActions(FragmentManager.java:1501)
            at android.support.v4.app.FragmentManagerImpl$1.run(FragmentManager.java:458)
            at android.os.Handler.handleCallback(Handler.java:615)
            at android.os.Handler.dispatchMessage(Handler.java:92)
            at android.os.Looper.loop(Looper.java:137)
            at android.app.ActivityThread.main(ActivityThread.java:4745)
            at java.lang.reflect.Method.invokeNative(Native Method)
            at java.lang.reflect.Method.invoke(Method.java:511)
            at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:786)
            at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:553)
            at dalvik.system.NativeStart.main(Native Method)
```
总结了原因：
* 使用了objectAnimator这个动画标签
* Fragment导入的是v4下面的包
> 原因就在于v4包中的Fragment对于动画的支持不完全。具体可查看源码。

所以，在使用V4包中Fragment的时候，使用的切换动画效果，其动画文件中不能包含objectAnimator，Animator这类标签。如果需要使用，将v4中的Fragment的相关类，替换成Fragment中的相关类。
#放大、缩小View
实现后的效果图如下：
![1](http://1.infotravel.sinaapp.com/pic/7.gif)
点击缩略图可以看到全尺寸的图像。
##创建View对象
```xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/container"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <LinearLayout android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp">

        <ImageButton
            android:id="@+id/thumb_button_1"
            android:layout_width="100dp"
            android:layout_height="75dp"
            android:layout_marginRight="1dp"
            android:src="@drawable/thumb1"
            android:scaleType="centerCrop"
            android:contentDescription="@string/description_image_1" />

    </LinearLayout>

    <!-- This initially-hidden ImageView will hold the expanded/zoomed version of
         the images above. Without transformations applied, it takes up the entire
         screen. To achieve the "zoom" animation, this view's bounds are animated
         from the bounds of the thumbnail button above, to its final laid-out
         bounds.
         -->

    <ImageView
        android:id="@+id/expanded_image"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:visibility="invisible"
        android:contentDescription="@string/description_zoom_touch_close" />

</FrameLayout>
```
其中放大后的效果的ImageView设置了属性`android:visibility="invisible"`。整个布局的id为container。
##建立方法的动画
```java
public class ZoomActivity extends FragmentActivity {
    // Hold a reference to the current animator,
    // so that it can be canceled mid-way.
    private Animator mCurrentAnimator;

    // The system "short" animation time duration, in milliseconds. This
    // duration is ideal for subtle animations or animations that occur
    // very frequently.
    private int mShortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);

        // Hook up clicks on the thumbnail views.

        final View thumb1View = findViewById(R.id.thumb_button_1);
        thumb1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(thumb1View, R.drawable.image1);
            }
        });

        // Retrieve and cache the system's default "short" animation time.
        mShortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);
    }
    ...
}
```
其中缩略图为ImageButton，它执行方法的动画。
##放大的动画
* 将高分辨率的图片赋给ImageView。下面的例子直接在UI线程上加载大图片了，实际上应该在单独的线程上执行。
* 计算ImageView的开始和结尾的边界。
* 四个定点和放大的值同时进行动画(Animate each of the four positioning and sizing properties `X, Y, (SCALE_X, and SCALE_Y)` simultaneously, from the starting bounds to the ending bounds. These four animations are added to an AnimatorSet so that they can be started at the same time. )
```java
private void zoomImageFromThumb(final View thumbView, int imageResId) {
    // If there's an animation in progress, cancel it
    // immediately and proceed with this one.
    if (mCurrentAnimator != null) {
        mCurrentAnimator.cancel();
    }

    // Load the high-resolution "zoomed-in" image.
    final ImageView expandedImageView = (ImageView) findViewById(
            R.id.expanded_image);
    expandedImageView.setImageResource(imageResId);

    // Calculate the starting and ending bounds for the zoomed-in image.
    // This step involves lots of math. Yay, math.
    final Rect startBounds = new Rect();
    final Rect finalBounds = new Rect();
    final Point globalOffset = new Point();

    // The start bounds are the global visible rectangle of the thumbnail,
    // and the final bounds are the global visible rectangle of the container
    // view. Also set the container view's offset as the origin for the
    // bounds, since that's the origin for the positioning animation
    // properties (X, Y).
    thumbView.getGlobalVisibleRect(startBounds);
    findViewById(R.id.container)
            .getGlobalVisibleRect(finalBounds, globalOffset);
    startBounds.offset(-globalOffset.x, -globalOffset.y);
    finalBounds.offset(-globalOffset.x, -globalOffset.y);

    // Adjust the start bounds to be the same aspect ratio as the final
    // bounds using the "center crop" technique. This prevents undesirable
    // stretching during the animation. Also calculate the start scaling
    // factor (the end scaling factor is always 1.0).
    float startScale;
    if ((float) finalBounds.width() / finalBounds.height()
            > (float) startBounds.width() / startBounds.height()) {
        // Extend start bounds horizontally
        startScale = (float) startBounds.height() / finalBounds.height();
        float startWidth = startScale * finalBounds.width();
        float deltaWidth = (startWidth - startBounds.width()) / 2;
        startBounds.left -= deltaWidth;
        startBounds.right += deltaWidth;
    } else {
        // Extend start bounds vertically
        startScale = (float) startBounds.width() / finalBounds.width();
        float startHeight = startScale * finalBounds.height();
        float deltaHeight = (startHeight - startBounds.height()) / 2;
        startBounds.top -= deltaHeight;
        startBounds.bottom += deltaHeight;
    }

    // Hide the thumbnail and show the zoomed-in view. When the animation
    // begins, it will position the zoomed-in view in the place of the
    // thumbnail.
    thumbView.setAlpha(0f);
    expandedImageView.setVisibility(View.VISIBLE);

    // Set the pivot point for SCALE_X and SCALE_Y transformations
    // to the top-left corner of the zoomed-in view (the default
    // is the center of the view).
    expandedImageView.setPivotX(0f);
    expandedImageView.setPivotY(0f);

    // Construct and run the parallel animation of the four translation and
    // scale properties (X, Y, SCALE_X, and SCALE_Y).
    AnimatorSet set = new AnimatorSet();
    set
            .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                    startBounds.left, finalBounds.left))
            .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                    startBounds.top, finalBounds.top))
            .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
            startScale, 1f)).with(ObjectAnimator.ofFloat(expandedImageView,
                    View.SCALE_Y, startScale, 1f));
    set.setDuration(mShortAnimationDuration);
    set.setInterpolator(new DecelerateInterpolator());
    set.addListener(new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            mCurrentAnimator = null;
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            mCurrentAnimator = null;
        }
    });
    set.start();
    mCurrentAnimator = set;

    // Upon clicking the zoomed-in image, it should zoom back down
    // to the original bounds and show the thumbnail instead of
    // the expanded image.
    final float startScaleFinal = startScale;
    expandedImageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mCurrentAnimator != null) {
                mCurrentAnimator.cancel();
            }

            // Animate the four positioning/sizing properties in parallel,
            // back to their original values.
            AnimatorSet set = new AnimatorSet();
            set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y,startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
            set.setDuration(mShortAnimationDuration);
            set.setInterpolator(new DecelerateInterpolator());
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    thumbView.setAlpha(1f);
                    expandedImageView.setVisibility(View.GONE);
                    mCurrentAnimator = null;
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    thumbView.setAlpha(1f);
                    expandedImageView.setVisibility(View.GONE);
                    mCurrentAnimator = null;
                }
            });
            set.start();
            mCurrentAnimator = set;
        }
    });
}
```
#布局变化动画
这个相对简单，实现后的效果图如下：
![1](http://1.infotravel.sinaapp.com/pic/8.gif)
##创建布局
```xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- A vertical LinearLayout in a ScrollView. This emulates a ListView (and is lighter weight
         than a ListView when there aren't many rows). -->
    <ScrollView android:layout_width="match_parent"
        android:layout_height="match_parent">

        <!-- Note that this LinearLayout has the "animateLayoutChanges" property set to true.
             This tells the framework to automatically animate child views (in this case, rows)
             as they are added to and removed from the LinearLayout. -->
        <LinearLayout android:id="@+id/container"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical"
            android:showDividers="middle"
            android:divider="?android:dividerHorizontal"
            android:animateLayoutChanges="true"
            android:paddingLeft="16dp"
            android:paddingRight="16dp" />

    </ScrollView>

    <!-- The "empty" view to show when there are no items in the "list" view defined above. -->
    <TextView android:id="@android:id/empty"
        style="?android:textAppearanceSmall"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:padding="32dp"
        android:text="message_empty_layout_changes"
        android:textColor="?android:textColorSecondary" />

</FrameLayout>
```
当没有任何item的时候，添加一个空的view进行提示。
item:
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="?android:listPreferredItemHeightSmall"
    android:orientation="horizontal"
    android:showDividers="middle"
    android:divider="?android:dividerVertical"
    android:dividerPadding="8dp"
    android:gravity="center">

    <!-- Dummy text view that will display the name of a random country. -->
    <TextView android:id="@android:id/text1"
        style="?android:textAppearanceMedium"
        android:layout_width="0dp"
        android:layout_weight="1"
        android:layout_height="wrap_content"
        android:paddingLeft="?android:listPreferredItemPaddingLeft" />

    <!-- A button that, when pressed, will delete this list item row from its container. -->
    <ImageButton android:id="@+id/delete_button"
        android:layout_width="48dp"
        android:layout_height="match_parent"
        android:src="@drawable/ic_list_remove"
        android:background="?android:selectableItemBackground"
        android:contentDescription="Remove item" />

</LinearLayout>
```
##添加、移除item
```java
package me.chenfuduo.animationdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/6/23.
 */
public class LayoutChangeActivity extends AppCompatActivity {

    private ViewGroup mContainerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_change);
        mContainerView = (ViewGroup) findViewById(R.id.container);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // Hide the "empty" view since there is now at least one item in the list.
            findViewById(android.R.id.empty).setVisibility(View.GONE);
            addItem();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addItem() {
        // Instantiate a new "row" view.
        final ViewGroup newView = (ViewGroup) LayoutInflater.from(this).inflate(
                R.layout.list_item_example, mContainerView, false);

        // Set the text in the new row to a random country.
        ((TextView) newView.findViewById(android.R.id.text1)).setText(
                COUNTRIES[(int) (Math.random() * COUNTRIES.length)]);

        // Set a click listener for the "X" button in the row that will remove the row.
        newView.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remove the row from its parent (the container view).
                // Because mContainerView has android:animateLayoutChanges set to true,
                // this removal is automatically animated.
                mContainerView.removeView(newView);

                // If there are no rows remaining, show the empty view.
                if (mContainerView.getChildCount() == 0) {
                    findViewById(android.R.id.empty).setVisibility(View.VISIBLE);
                }
            }
        });

        // Because mContainerView has android:animateLayoutChanges set to true,
        // adding this view is automatically animated.
        mContainerView.addView(newView, 0);
    }

    /**
     * A static list of country names.
     */
    private static final String[] COUNTRIES = new String[]{
            "Belgium", "France", "Italy", "Germany", "Spain",
            "Austria", "Russia", "Poland", "Croatia", "Greece",
            "Ukraine",
    };
}
```
#源码
[自己写的demo](https://github.com/leerduo/AnimationDemo)
[官方的demo](https://github.com/leerduo/AnimationsDemo)



































