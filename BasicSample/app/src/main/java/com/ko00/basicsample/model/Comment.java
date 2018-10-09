package com.ko00.basicsample.model;

import java.util.Date;

public interface Comment {
		int getId();
		int getProductId();
		String getText();
		Date getPostedAt();
}
