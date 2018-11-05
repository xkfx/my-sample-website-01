package org.sample.service.impl;

import org.sample.dao.ProfileDAO;
import org.sample.entity.Profile;
import org.sample.exception.DaoException;
import org.sample.service.SampleService;

import java.util.ArrayList;
import java.util.List;

public class SampleServiceImpl implements SampleService {

    public List getBrands(String color) {
        List brands = new ArrayList();
        if (color.equals("amber")) {
            brands.add("Jack Amber");
            brands.add("Red Moose");
        } else {
            brands.add("Jail Pale Ale");
            brands.add("Gout Stout");
        }
        return brands;
    }
}