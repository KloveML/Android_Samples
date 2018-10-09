package com.ko00.basicsample.ui;

import android.databinding.BindingAdapter;
import android.view.View;

public class BindingAdapters {
		@BindingAdapter("visibleGone")
		public static void showHide(View view, boolean show) {
				view.setVisibility(show ? view.VISIBLE : view.GONE);
		}
}
