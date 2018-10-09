package com.ko00.basicsample;

import android.app.Application;

import com.ko00.basicsample.db.AppDatabase;

public class BasicApp extends Application {
		private AppExecutors mAppExecutors;

		@Override
		public void onCreate() {
				super.onCreate();

				/**
				 * DES:
				 * 获取线程池实例
				 * */
				mAppExecutors = new AppExecutors();
		}

		/**
		 * AIM:
		 * 获取数据库实例
		 */
		public AppDatabase getDatabase() {
				return AppDatabase.getInstance(this, mAppExecutors);
		}

		public DataRepository getRepository(){
				return DataRepository.getInstance(getDatabase());
		}

}
