package com.higgin.weather.client;

import com.higgin.weather.WeatherInterface;
import com.higgin.weather.WeatherModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ClientTest {

    private ApplicationContext applicationContext;

    @Before
    public void before() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:context/applicationContext.xml");
    }

    @Test
    public void textCxfSpringClient() {
        //从spring容器中取出portType
        WeatherInterface weatherInterface = (WeatherInterface)applicationContext.getBean("weatherClient");

        //调用portType方法
        List<WeatherModel> list=weatherInterface.queryWeather("杭州");

        for(WeatherModel weatherModel:list){
            System.out.println(weatherModel.getDetail());
            Date date=weatherModel.getDate().toGregorianCalendar().getTime();
            System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date));
            System.out.println(weatherModel.getTemperatureMax());
            System.out.println(weatherModel.getTemperatureMin());
        }

    }
}