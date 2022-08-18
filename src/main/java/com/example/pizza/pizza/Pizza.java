package com.example.pizza.pizza;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Entity
@Getter
@Setter
@Transactional
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    @Fetch(FetchMode.JOIN)
    public byte[] image;
    String doughType;
    String size;
    Integer price;
    @Enumerated(EnumType.STRING)
    Category category;
    Integer popularity;

    public byte[] setImage(Path path) throws IOException {
        byte[] arr = Files.readAllBytes(path);
        this.image = arr;
        return arr;
    }
    public void setSize(Integer[] size){
        Gson gson = new Gson();
        this.size = gson.toJson(size);
    }
}

