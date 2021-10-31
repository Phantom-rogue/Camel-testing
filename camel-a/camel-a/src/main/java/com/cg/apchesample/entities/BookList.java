package com.cg.apchesample.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookList {

	private String status;
	private int code;
	private int total;
	private List<Book> data;
	
	
}
