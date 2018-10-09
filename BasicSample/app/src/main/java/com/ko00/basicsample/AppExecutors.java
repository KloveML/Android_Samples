package com.ko00.basicsample;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {

		private final Executor mDiskIO;

		private final Executor mNetworkIO;

		private final Executor mMainThread;

		public AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {
				mDiskIO = diskIO;
				mNetworkIO = networkIO;
				mMainThread = mainThread;
		}

		public AppExecutors() {
				this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3), new MainThreadExecutor());
		}

		/**
		 * AIM:
		 * 用于子线程去更新UI线程
		 */
		private static class MainThreadExecutor implements Executor {
				private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

				@Override
				public void execute(@NonNull Runnable command) {
						mainThreadHandler.post(command);
				}
		}

		public Executor getmDiskIO() {
				return mDiskIO;
		}

		public Executor getmNetworkIO() {
				return mNetworkIO;
		}

		public Executor getmMainThread() {
				return mMainThread;
		}


}
