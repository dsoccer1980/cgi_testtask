package com.dsoccer1980.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserNumbersDto {

    private String userName;
    private int userId;
    private List<Integer> numbers;

}
