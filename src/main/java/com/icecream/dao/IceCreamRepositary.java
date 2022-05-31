package com.icecream.dao;

import com.icecream.models.IceCream;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IceCreamRepositary extends JpaRepository<IceCream, Integer> {


}
