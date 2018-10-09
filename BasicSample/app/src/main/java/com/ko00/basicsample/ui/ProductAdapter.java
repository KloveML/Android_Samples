package com.ko00.basicsample.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ko00.basicsample.R;
import com.ko00.basicsample.databinding.ProductItemBinding;
import com.ko00.basicsample.db.entity.ProductEntity;
import com.ko00.basicsample.model.Product;

import java.util.List;

import static android.databinding.DataBindingUtil.inflate;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
		List<? extends Product> mProductList;

		@NonNull
		@Override
		public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
				ProductItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.product_item, parent, false);
				return new ProductViewHolder(binding);
		}

		public void setProductList(final List<? extends Product> productList) {
				if (mProductList == null) {
						mProductList = productList;
						notifyItemRangeInserted(0,productList.size());
				}
		}

		@Override
		public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
				holder.binding.setProduct(mProductList.get(position));
				holder.binding.executePendingBindings();
		}

		@Override
		public int getItemCount() {
				return mProductList == null ? 0 : mProductList.size();
		}

		static class ProductViewHolder extends RecyclerView.ViewHolder {
				final ProductItemBinding binding;

				public ProductViewHolder(ProductItemBinding binding) {
						super(binding.getRoot());
						this.binding = binding;
				}
		}
}
