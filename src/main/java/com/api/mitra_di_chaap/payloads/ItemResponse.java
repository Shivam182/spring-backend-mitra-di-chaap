package com.api.mitra_di_chaap.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {
	private List<ItemDto> content;
	private int pageNumber;
	private int pageSize;
	
	
	private long totalElements;
	private int totalPages;
	
	private boolean lastPage;
	
}
