package com.bzdepot.special.bo;

import com.bzdepot.special.model.TechnologyClass;

import java.util.Arrays;

public class TechnologyPost {

    private TechnologyClass[] classes;

    public TechnologyClass[] getClasses() {
        return classes;
    }

    public void setClasses(TechnologyClass[] classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "TechnologyPost{" +
                "classes=" + Arrays.toString(classes) +
                '}';
    }
}
