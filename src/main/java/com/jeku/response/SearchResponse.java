package com.jeku.response;

import lombok.Data;

@Data
public class SearchResponse {
	private String name;
	private String Email;
	private Long mobile;
	private Character gender;
	private Long ssn;
	private String planName;
	private String planStatus;
}
