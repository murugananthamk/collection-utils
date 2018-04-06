package com.example.collection.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

	private String skuNo;
	
	private String categroy;
	
	private int price;

	public Item(String skuNo, String categroy, int price) {
		super();
		this.skuNo = skuNo;
		this.categroy = categroy;
		this.price = price;
	}

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

	public String getCategroy() {
		return categroy;
	}

	public void setCategroy(String categroy) {
		this.categroy = categroy;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
