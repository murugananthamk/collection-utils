package com.example.collection.utils;

public class Item {

	public Item() {

	}

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

	public Item(String skuNo, String categroy) {
		super();
		this.skuNo = skuNo;
		this.categroy = categroy;
	}

	public String getCategroy() {
		return categroy;
	}

	public void setCategroy(String categroy) {
		this.categroy = categroy;
	}

	private String skuNo;
	
	private String categroy;
}
