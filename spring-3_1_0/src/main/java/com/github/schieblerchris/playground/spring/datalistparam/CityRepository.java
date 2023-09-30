package com.github.schieblerchris.playground.spring.datalistparam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CityRepository extends PagingAndSortingRepository<CityEt, Integer> {

    @Query("select c from CityEt c where c.country in :countries")
    List<CityEt> findAllByCountryIn(Set<Country> countries);

    @Query("select c from CityEt c where :countries is null or c.country in :countries")
    List<CityEt> findAllByCountryInOrCountriesParameterIsNull(Set<Country> countries);


    @Query("select c from CityEt c where c.country in :countries or :countries is null")
    List<CityEt> findAllByCountryInOrCountriesParameterIsNullOrderSwitched(Set<Country> countries);


}
