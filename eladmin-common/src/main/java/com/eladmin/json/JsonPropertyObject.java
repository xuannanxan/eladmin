package com.eladmin.json;

public abstract class JsonPropertyObject  {

    public String toString() {
        try {
            return JsonBuilder.toJson(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
