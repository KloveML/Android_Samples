package com.ko00.basicsample.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ko00.basicsample.R;

public class MainActivity extends AppCompatActivity {

		@Override
		protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_main);
				if (savedInstanceState == null) {
						ProductListFragment fragment = new ProductListFragment();
						getSupportFragmentManager().beginTransaction()
										.add(R.id.fragment_container, fragment, ProductListFragment.TAG).commit();
				}
		}
}
