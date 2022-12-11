package com.nativegame.nattyengine.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.fragment.app.Fragment;

/**
 * Created by Oscar Liang on 2022/12/11
 */

public class GameFragment extends Fragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Init the layout listener
        final ViewTreeObserver observer = view.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public synchronized void onGlobalLayout() {
                ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                    onLayoutCreated();
                }
            }
        });
    }

    protected void onLayoutCreated() {
    }

    public boolean onBackPressed() {
        return false;
    }

    public GameActivity getGameActivity() {
        return (GameActivity) getActivity();
    }

    public void showDialog(GameDialog newDialog) {
        getGameActivity().showDialog(newDialog);
    }

}
