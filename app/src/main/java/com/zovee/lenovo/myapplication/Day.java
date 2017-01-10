package com.zovee.lenovo.myapplication;

import java.util.Date;

/**
 * 实体类
 * Created by lenovo on 2017/1/3.
 */
public class Day {
    //日期
    private String date;
    //白天天气现象文字
    private String text_day;
    //白天天气现象代码
    private String code_day;
    //晚间天气现象文字
    private String text_night;
    //晚间天气现象代码
    private String code_night;
    //当天最高温度
    private String high;
    //当天最低温度
    private String low;
    //降水概率，范围0~100，单位百分比
    private String precip;
    //风向文字
    private String wind_direction;
    //风向角度，范围0~360
    private String wind_driection_degree;
    //风速，单位km/h(当unit=c时)
    private String wind_speed;
    //风力等级
    private String wind_scale;

    public Day(){

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText_day() {
        return text_day;
    }

    public void setText_day(String text_day) {
        this.text_day = text_day;
    }

    public String getCode_day() {
        return code_day;
    }

    public void setCode_day(String code_day) {
        this.code_day = code_day;
    }

    public String getText_night() {
        return text_night;
    }

    public void setText_night(String text_night) {
        this.text_night = text_night;
    }

    public String getCode_night() {
        return code_night;
    }

    public void setCode_night(String code_night) {
        this.code_night = code_night;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getPrecip() {
        return precip;
    }

    public void setPrecip(String precip) {
        this.precip = precip;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getWind_driection_degree() {
        return wind_driection_degree;
    }

    public void setWind_driection_degree(String wind_driection_degree) {
        this.wind_driection_degree = wind_driection_degree;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getWind_scale() {
        return wind_scale;
    }

    public void setWind_scale(String wind_scale) {
        this.wind_scale = wind_scale;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
