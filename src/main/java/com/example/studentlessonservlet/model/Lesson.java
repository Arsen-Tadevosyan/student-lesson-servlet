package com.example.studentlessonservlet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lesson {
    private int id;
    private String name;
    private String duration;
    private String lecturerName;
    private double price;
}
