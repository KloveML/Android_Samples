package com.ko00.basicsample.db;

import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.ko00.basicsample.AppExecutors;
import com.ko00.basicsample.db.converter.DateConverter;
import com.ko00.basicsample.db.dao.CommentDao;
import com.ko00.basicsample.db.dao.ProductDao;
import com.ko00.basicsample.db.entity.CommentEntity;
import com.ko00.basicsample.db.entity.ProductEntity;

import java.util.List;

/**
 * AIM:
 * 进行数据库的初始化
 */

@Database(entities = {ProductEntity.class, CommentEntity.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
		/**
		 * Dao
		 */
		public abstract ProductDao productDao();

		public abstract CommentDao commentDao();

		/**
		 * Database
		 */
		@VisibleForTesting
		public static final String DATABASE_NAME = "basic-sample-db";

		private static AppDatabase sInstance;

		private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

		public MutableLiveData<Boolean> getDatabaseCreated() {
				return mIsDatabaseCreated;
		}

		private void setDatabaseCreated() {
				mIsDatabaseCreated.postValue(true);
		}

		private void updateDatabaseCreated(final Context context) {
				if (context.getDatabasePath(DATABASE_NAME).exists()) {
						setDatabaseCreated();
				}
		}

		/**
		 * 获取数据库实例
		 */
		public static AppDatabase getInstance(final Context context, final AppExecutors executors) {
				if (sInstance == null) {
						synchronized (AppDatabase.class) {
								if (sInstance == null) {
										sInstance = buildDatabase(context.getApplicationContext(), executors);
										sInstance.updateDatabaseCreated(context.getApplicationContext());
								}
						}
				}
				return sInstance;
		}

		private static AppDatabase buildDatabase(final Context appContext, final AppExecutors executors) {
				return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME).
								addCallback(new Callback() {
										@Override
										public void onCreate(@NonNull SupportSQLiteDatabase db) {
												super.onCreate(db);
												executors.getmDiskIO().execute(() -> {
														addDelay();
														AppDatabase database = AppDatabase.getInstance(appContext, executors);
														List<ProductEntity> products = DataGenerator.generateProducts();
														List<CommentEntity> comments = DataGenerator.generateComments(products);
														insertData(database, products, comments);
														database.setDatabaseCreated();
												});
										}
								}).build();
		}


		public static void insertData(AppDatabase database, final List<ProductEntity> products, final List<CommentEntity> comments) {
				database.runInTransaction(() -> {
						database.productDao().insertAll(products);
						database.commentDao().insertAll(comments);
				});
		}


		// util
		private static void addDelay() {
				try {
						Thread.sleep(4000);
				} catch (InterruptedException ignored) {

				}
		}

}
