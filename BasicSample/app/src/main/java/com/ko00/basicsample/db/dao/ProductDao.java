package com.ko00.basicsample.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ko00.basicsample.db.entity.ProductEntity;
import com.ko00.basicsample.model.Product;

import java.util.List;

@Dao
public interface ProductDao {
		@Insert(onConflict = OnConflictStrategy.REPLACE)
		void insertAll(List<ProductEntity> products);

		@Query("SELECT * FROM products")
		LiveData<List<ProductEntity>> loadAllProducts();

		@Query("SELECT * FROM products WHERE id = :productId")
		LiveData<ProductEntity> loadProduct(int productId);

		@Query("SELECT * FROM products WHERE id = :productId")
		ProductEntity loadProductSync(int productId);
}
