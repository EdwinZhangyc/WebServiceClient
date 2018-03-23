package com.higgin.weather.client;

import com.higgin.weather.WeatherInterface;
import com.higgin.weather.WeatherModel;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WeatherClient {

    public static void main(String[] args) {
        //jaxWsProxyFactoryBean调用WebService服务端
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean=new JaxWsProxyFactoryBean();
        //调用地址
        jaxWsProxyFactoryBean.setAddress("http://127.0.0.1:12345/weather?wsdl");
        //设置portType
        jaxWsProxyFactoryBean.setServiceClass(WeatherInterface.class);
        //创建portType
        WeatherInterface weatherInterface=(WeatherInterface) jaxWsProxyFactoryBean.create();

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