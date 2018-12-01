package com.bat.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MiaoShaUser {
    private Long id;
    private String name;
    private String password;
    private String salt;
    private String registerDate;
    private String lastLoginDate;
}
