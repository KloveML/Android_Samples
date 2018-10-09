package com.ko00.basicsample.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.ko00.basicsample.BasicApp;
import com.ko00.basicsample.db.entity.ProductEntity;

import java.util.List;

public class ProductViewListModel extends AndroidViewModel{

		private final MediatorLiveData<List<ProductEntity>> mObservableProducts;

		public ProductViewListModel(Application application){
				super(application);

				mObservableProducts =  new MediatorLiveData<>();
				mObservableProducts.setValue(null);

				LiveData<List<ProductEntity>> products = ((BasicApp) application).getRepository().getProducts();

				mObservableProducts.addSource(products,mObservableProducts::setValue);
		}

		public LiveData<List<ProductEntity>> getProducts(){
				return mObservableProducts;
		}
}
