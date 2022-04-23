package com.lenses.lenses_ecommerce_security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class LibraryEvent {
	private int libraryEventId;
	private ReviewRequestModel requestModel;

}
