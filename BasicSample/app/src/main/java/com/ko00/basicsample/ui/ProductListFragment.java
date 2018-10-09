package com.ko00.basicsample.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ko00.basicsample.R;
import com.ko00.basicsample.databinding.ListFragmentBinding;
import com.ko00.basicsample.db.entity.ProductEntity;
import com.ko00.basicsample.viewmodel.ProductViewListModel;

import java.util.List;

public class ProductListFragment extends Fragment {

		public static final String TAG = "ProductListViewModel";

		private ProductAdapter mProductAdapter;

		private ListFragmentBinding mBinding;

		@Nullable
		@Override
		public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
				mBinding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false);

				mProductAdapter = new ProductAdapter();
				mBinding.productList.setAdapter(mProductAdapter);
				return mBinding.getRoot();
		}

		@Override
		public void onActivityCreated(@Nullable Bundle savedInstanceState) {
				super.onActivityCreated(savedInstanceState);
				final ProductViewListModel viewModel = ViewModelProviders.of(this).get(ProductViewListModel.class);
				subscribeUi(viewModel);
		}

		private void subscribeUi(ProductViewListModel viewModel) {
				viewModel.getProducts().observe(this, new Observer<List<ProductEntity>>() {
						@Override
						public void onChanged(@Nullable List<ProductEntity> productEntities) {
								if (productEntities != null){
										mBinding.setIsLoading(false);
										mProductAdapter.setProductList(productEntities);
								}else{
										mBinding.setIsLoading(true);
								}
								mBinding.executePendingBindings();
						}
				});
		}
}
