package com.github.schieblerchris.playground.spring.datalistparam;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@Log4j2
class CityRepositoryTest {

    @Autowired
    CityRepository cut;

    @Test
    void findAllByCountries_EmptyCountry() {
        var found = cut.findAllByCountryIn(Set.of());
        assertThat(found).isEmpty();
    }

    @Test
    void findAllByCountries_SingleCountry() {
        var found = cut.findAllByCountryIn(Set.of(Country.GERMANY));
        assertThat(found).isNotEmpty();
        assertThat(found).map(CityEt::getCountry).containsOnly(Country.GERMANY);
    }

    @Test
    void findAllByCountries_MultipleCountry() {
        var found = cut.findAllByCountryIn(Set.of(Country.GERMANY, Country.SOUTH_AFRICA));
        assertThat(found).isNotEmpty();
        assertThat(found).map(CityEt::getCountry).containsOnly(Country.GERMANY, Country.SOUTH_AFRICA);
    }

    @Test
    void findAllByCountryInOrCountriesIsNull_NullParameter() {
        var found = cut.findAllByCountryInOrCountriesParameterIsNull(null);
        assertThat(found).isNotEmpty();
    }

    @Test
    void findAllByCountryInOrCountriesIsNull_EmptyCountry() {
        var found = cut.findAllByCountryInOrCountriesParameterIsNull(Set.of());
        assertThat(found).isEmpty();
    }

    @Test
    void findAllByCountryInOrCountriesIsNull_SingleCountry() {
        var found = cut.findAllByCountryInOrCountriesParameterIsNull(Set.of(Country.GERMANY));
        assertThat(found).isNotEmpty();
        assertThat(found).map(CityEt::getCountry).containsOnly(Country.GERMANY);
    }

    @Test
    void findAllByCountryInOrCountriesIsNull_MultipleCountry() {
        var found = cut.findAllByCountryInOrCountriesParameterIsNull(Set.of(Country.GERMANY, Country.SOUTH_AFRICA));
        assertThat(found).isNotEmpty();
        assertThat(found).map(CityEt::getCountry).containsOnly(Country.GERMANY, Country.SOUTH_AFRICA);
    }

    @Test
    void findAllByCountryInOrCountriesIsNullOrderSwitched_NullParameter() {
        var found = cut.findAllByCountryInOrCountriesParameterIsNullOrderSwitched(null);
        assertThat(found).isNotEmpty();
    }

    @Test
    void findAllByCountryInOrCountriesIsNullOrderSwitched_EmptyCountry() {
        var found = cut.findAllByCountryInOrCountriesParameterIsNullOrderSwitched(Set.of());
        assertThat(found).isEmpty();
    }

    @Test
    void findAllByCountryInOrCountriesIsNullOrderSwitched_SingleCountry() {
        var found = cut.findAllByCountryInOrCountriesParameterIsNullOrderSwitched(Set.of(Country.GERMANY));
        assertThat(found).isNotEmpty();
        assertThat(found).map(CityEt::getCountry).containsOnly(Country.GERMANY);
    }

    @Test
    void findAllByCountryInOrCountriesIsNullOrderSwitched_MultipleCountry() {
        var found = cut.findAllByCountryInOrCountriesParameterIsNullOrderSwitched(Set.of(Country.GERMANY, Country.SOUTH_AFRICA));
        assertThat(found).isNotEmpty();
        assertThat(found).map(CityEt::getCountry).containsOnly(Country.GERMANY, Country.SOUTH_AFRICA);
    }
}