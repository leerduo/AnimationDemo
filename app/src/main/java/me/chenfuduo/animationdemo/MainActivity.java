package me.chenfuduo.animationdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
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

}
