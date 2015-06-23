package me.chenfuduo.animationdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Administrator on 2015/6/23.
 */
public class CardFlipActivity extends AppCompatActivity {

    private boolean mShowingBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_card_flip);
        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardFrontFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            flipCard();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void flipCard() {
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            mShowingBack = false;
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

}
