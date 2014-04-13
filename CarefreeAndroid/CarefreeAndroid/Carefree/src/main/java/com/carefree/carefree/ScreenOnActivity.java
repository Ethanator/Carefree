package com.carefree.carefree;

/**
 * Created by Normn on 4/12/2014.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

public class ScreenOnActivity extends Activity {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            this.finish();

        }

}
