package com.example.foodprint;

public class Unit_Converter {
    public float to_meter(float height){
        float m_height = height * 0.3048f;
        return m_height;
    }

    public float to_kg(float weight){
        float kg_weight = weight*0.453592f;
        return kg_weight;
    }
    public float to_km(float distance){
        float km_distance = distance *1.60934f;
        return km_distance;
    }

    public float to_mile(float distance){
        float mile_distance = distance*0.621371f;
        return mile_distance;
    }

    public float to_lbs(float weight){
        float lbs_weight = weight * 2.20462f;
        return lbs_weight;
    }
}
