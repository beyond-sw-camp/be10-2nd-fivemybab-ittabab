package com.fivemybab.ittabab.store.command.dto;

import com.fivemybab.ittabab.store.command.entity.MenuCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MenuCategoryDTO {

    private int MenuCategoryId;
    private String MenuCategoryName;

}
