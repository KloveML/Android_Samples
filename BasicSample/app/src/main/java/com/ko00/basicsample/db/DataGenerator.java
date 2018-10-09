package com.ko00.basicsample.db;


import com.ko00.basicsample.db.entity.CommentEntity;
import com.ko00.basicsample.db.entity.ProductEntity;
import com.ko00.basicsample.model.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * AIM:
 * 产生静态数据
 */
public class DataGenerator {
		private static final String[] FIRST = new String[]{
						"F1", "F2", "F3", "F4", "F5"};
		private static final String[] SECOND = new String[]{
						"S1", "S2", "S3", "S4"};
		private static final String[] DESCRIPTION = new String[]{
						"D1", "D2", "D3", "D4", "D5", "D6"};
		private static final String[] COMMENTS = new String[]{
						"C1", "C2", "C3", "C4", "C5", "C6"};

		public static List<ProductEntity> generateProducts() {
				List<ProductEntity> products = new ArrayList<>(FIRST.length * SECOND.length);
				Random rnd = new Random();

				for (int i = 0; i < FIRST.length; i++) {
						for (int j = 0; j < SECOND.length; j++) {
								ProductEntity product = new ProductEntity();
								product.setId(FIRST.length * i + j + 1);
								product.setName(FIRST[i] + " " + SECOND[j]);
								product.setDescription(product.getName() + " " + DESCRIPTION[j]);
								product.setPrice(rnd.nextInt(240));
								products.add(product);
						}
				}
				return products;
		}

		public static List<CommentEntity> generateComments(final List<ProductEntity> products) {
				List<CommentEntity> comments = new ArrayList<>();
				Random rnd = new Random();

				for (Product product : products) {
						int commentsNumber = rnd.nextInt(5) + 1;
						for (int i = 0; i < commentsNumber; i++) {
								CommentEntity comment = new CommentEntity();
								comment.setProductId(product.getId());
								comment.setText(COMMENTS[i] + " for " + product.getName());
								comment.setPostedAt(new Date());
								comments.add(comment);
						}
				}
				return comments;
		}
}
