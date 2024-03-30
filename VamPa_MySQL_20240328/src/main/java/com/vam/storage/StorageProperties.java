package com.vam.storage;

import com.vam.utils.Constant;
import org.springframework.stereotype.Component;

@Component
public class StorageProperties {
    private String location = Constant.BASE_FOLDER;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
