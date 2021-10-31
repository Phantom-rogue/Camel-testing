package com.cg.apchesample.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Book {
@NotNull
@Size(min=3,max=20)
private String title;
@NotNull
@Size(min=3,max=20)
private String author;
@NotNull
@Size(min=3,max=20)
private String genre;
@NotNull
@Size(min=5,max=3500)
private String description;
@NotNull
private String isbn;
@NotNull
private String image;
@NotNull
@Size(min=3,max=10)
private String published;
@NotNull
@Size(min=3,max=20)
private String publisher;

}
