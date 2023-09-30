# Goal
Core idea of this project is to recreate an issue with spring data. 
Somehow spring seems to have an issue with testing a query parameter for null, if it is a collection. 

# Content
To recreate the issue a simple table containing city data is created and sample data is inserted. 

## Cities table
```
create table t_cities
(
    id      integer primary key auto_increment,
    country varchar not null,
    region  varchar not null,
    city    varchar not null
);
```

## City entity
```
@Entity
@Table(name = "t_cities")
@Data
@NoArgsConstructor
public class CityEt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Country country;

    private String region;

    private String city;
}
```

## Testdata

| ID | COUNTRY            | REGION             | CITY                  |
|----|--------------------|--------------------|-----------------------|
| 1  | INDIA              | Rajasthan          | Pali                  |
| 2  | PERU               | Lima               | Mala                  |
| 3  | POLAND             | Małopolskie        | Kraków                |
| 4  | COSTA_RICA         | Heredia            | San Pablo             |
| 5  | POLAND             | Swiętokrzyskie     | Starachowice          |
| 6  | TURKEY             | Antalya            | Manavgat              |
| 7  | RUSSIAN_FEDERATION | Ulyanovsk Oblast   | Ulyanovsk             |
| 8  | IRELAND            | Ulster             | Belfast               |
| 9  | BRAZIL             | Goiás              | Águas Lindas de Goiás |
| 10 | FRANCE             | Nord-Pas-de-Calais | Arras                 |

# Issue
For each spring version starting at 3.1.0 there is a distinct branch to test how a set of enumerations is handled. 
There are 3 distinct test queries to find out if testing for null creates an issue.

## Queries
```
@Repository
public interface CityRepository extends PagingAndSortingRepository<CityEt, Integer> {

    @Query("select c from CityEt c where c.country in :countries")
    List<CityEt> findAllByCountryIn(Set<Country> countries);

    @Query("select c from CityEt c where :countries is null or c.country in :countries")
    List<CityEt> findAllByCountryInOrCountriesParameterIsNull(Set<Country> countries);

    @Query("select c from CityEt c where c.country in :countries or :countries is null")
    List<CityEt> findAllByCountryInOrCountriesParameterIsNullOrderSwitched(Set<Country> countries);
}
```

## Error
Only in spring 3.1.4 the second and third query will run as expected. 
Otherwise, the following error will be logged.

```

```