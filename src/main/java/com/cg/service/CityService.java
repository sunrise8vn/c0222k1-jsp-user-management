package com.cg.service;

import com.cg.model.City;

public interface CityService extends IGeneralService<City> {

    boolean existById(int cityId);
}
