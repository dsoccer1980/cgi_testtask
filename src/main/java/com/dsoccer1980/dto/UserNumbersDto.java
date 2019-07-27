package com.dsoccer1980.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class UserNumbersDto {

    private String userName;
    private int userId;
    private List<Integer> numbers;

}
