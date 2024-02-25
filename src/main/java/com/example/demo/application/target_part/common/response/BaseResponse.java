package com.example.demo.application.target_part.common.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<D> {
    private List<D> items;
    private long total;
}
