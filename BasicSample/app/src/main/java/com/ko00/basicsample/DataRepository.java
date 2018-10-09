package com.ko00.basicsample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.ko00.basicsample.db.AppDatabase;
import com.ko00.basicsample.db.entity.CommentEntity;
import com.ko00.basicsample.db.entity.ProductEntity;

import java.util.List;

public class DataRepository {
		private static DataRepository sInstance;

		private final AppDatabase mDatabase;

		private MediatorLiveData<List<ProductEntity>> mObservableProducts;

		public DataRepository(final AppDatabase database) {
				mDatabase = database;
				mObservableProducts = new MediatorLiveData<>();


				/**
				 * AIM:
				 * 管理加载产品列表，用来被其他LiveData观察
				 * */
				mObservableProducts.addSource(mDatabase.productDao().loadAllProducts(), productEntities -> {
						if (mDatabase.getDatabaseCreated().getValue() != null) {
								mObservableProducts.postValue(productEntities);
						}
				});
		}

		public static DataRepository getInstance(final AppDatabase database) {
				if (sInstance == null) {
						synchronized (DataRepository.class) {
								if (sInstance == null) {
										sInstance = new DataRepository(database);
								}
						}
				}
				return sInstance;
		}

		public LiveData<List<ProductEntity>> getProducts() {
				return mObservableProducts;
		}

		public LiveData<ProductEntity> loadProduct(int productId) {
				return mDatabase.productDao().loadProduct(productId);
		}

		public LiveData<List<CommentEntity>> loadComments(final int productId) {
				return mDatabase.commentDao().loadComments(productId);
		}

}
