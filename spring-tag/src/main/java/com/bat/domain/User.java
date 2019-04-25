package com.bat.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: caoke
 * @Date: 2019/4/25 15:21
 * @Version 1.0
 */
@Getter
@Setter
public class User implements Serializable {
    private String id;
    private String name;
    private String description;
}
