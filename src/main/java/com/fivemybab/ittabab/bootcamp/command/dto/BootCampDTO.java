package com.fivemybab.ittabab.bootcamp.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class BootCampDTO {

    private Long bootId;
    private String bootName;
    private String bootLocation;
    // Getters and Setters

    public long getbootId() {
        return bootId;
    }

    public void setbootId(long bootId) {
        this.bootId = bootId;
    }

    public String getbootName() {
        return bootName;
    }

    public void setbootName(String bootName) {
        this.bootName = bootName;
    }

    public String getbootLocation() {
        return bootLocation;
    }

    public void setbootLocation(String bootLocation) {
        this.bootLocation = bootLocation;
    }
}


