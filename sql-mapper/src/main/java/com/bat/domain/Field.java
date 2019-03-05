package com.bat.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: caoke
 * @Date: 2019/3/5 15:02
 * @Version 1.0
 */
@Setter
@Getter
public class Field implements Serializable {
    private String name;
    private String type;

}
