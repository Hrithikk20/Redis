package com.redis.guide.springbootredis.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class Coder implements Serializable {
    private Integer id;

    private String company;

    private String name;
}
