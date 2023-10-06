package com.zyolv.cloud.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String name;
    private String avatar;
    private List<String> roles;
    private List<String> buttons;
    private List<String> routes;
}
