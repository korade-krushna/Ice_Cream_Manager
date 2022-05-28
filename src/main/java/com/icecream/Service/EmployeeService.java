package com.icecream.Service;

import com.icecream.dao.IceCreamRepositary;
import com.icecream.models.IceCream;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeService implements EmployeeServiceInterface {
    @Autowired
    IceCreamRepositary iceCreamRepositary;
    @Override
    public List<IceCream> getAllIceCreams() {
        return iceCreamRepositary.findAll();
    }
}
