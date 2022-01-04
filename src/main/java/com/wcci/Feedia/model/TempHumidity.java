package com.wcci.Feedia.model;

public class TempHumidity {

    private float temp;
    private float humidity;

    public TempHumidity(float temp, float humidity) {
        this.temp = temp;
        this.humidity = humidity;
    }

    public TempHumidity() {
    }

    public float getTemp() {
        return temp;
    }

    public float getHumidity() {
        return humidity;
    }
}
