package com.cst;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAdminServer
public class App {


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
//        String[] beanDefinitionNames = run.getBeanDefinitionNames();
//        for (String a:beanDefinitionNames){
//            System.err.println(a);
//        }
    }

//    public App(CityMapper cityMapper) {
//        this.cityMapper = cityMapper;
//    }

//    private final CityMapper cityMapper;


//    @Bean
//    CommandLineRunner sampleCommandLineRunner() {
//        return args -> {
//            City city = new City();
//            city.setName("San Francisco");
//            city.setState("CA");
//            city.setCountry("US");
//            cityMapper.insert(city);
//            System.out.println(this.cityMapper.findById(city.getId()));
//        };
//    }
}
