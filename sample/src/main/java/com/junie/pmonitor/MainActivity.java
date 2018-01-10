  package com.junie.pmonitor;

  import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.junie.monitorlib.Pmonitor;

  public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMonitor();
        initView();
    }

      private void initView() {
          findViewById(R.id.test_lag_btn).setOnClickListener(this);
          findViewById(R.id.test_crash_btn).setOnClickListener(this);
      }

      private void initMonitor() {
          Pmonitor.startMonitor();
      }



      @Override
      protected void onDestroy() {
          super.onDestroy();
          Pmonitor.stopMonitor();
      }

      @Override
      public void onClick(View view) {
          switch (view.getId()) {
              case R.id.test_lag_btn:
                  Pmonitor.testLag();
                  break;
              case R.id.test_crash_btn:
                  Pmonitor.testCrash();
                  break;
          }
      }
  }
