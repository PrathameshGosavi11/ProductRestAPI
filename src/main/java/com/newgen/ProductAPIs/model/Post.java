package com.newgen.ProductAPIs.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Post {

    private int userId;

    private   int id;

    private  String title;

    private  String body;

}
