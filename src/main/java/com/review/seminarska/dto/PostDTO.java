package com.review.seminarska.dto;

import lombok.Data;

@Data
public class PostDTO {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Long categoryId;

    private String imageName;
}
