package com.ko00.basicsample.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ko00.basicsample.db.entity.CommentEntity;

import java.util.List;

@Dao
public interface CommentDao {
		@Insert(onConflict = OnConflictStrategy.REPLACE)
		void insertAll(List<CommentEntity> comments);

		@Query("SELECT * FROM comments WHERE productId = :productId")
		LiveData<List<CommentEntity>> loadComments(int productId);

		@Query("SELECT * FROM comments WHERE productId = :productId")
		List<CommentEntity> loadCommentsSync(int productId);
}
