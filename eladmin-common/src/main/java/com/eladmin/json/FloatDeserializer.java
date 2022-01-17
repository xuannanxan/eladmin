package com.eladmin.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;

public class FloatDeserializer implements JsonDeserializer<Float> {

	@Override
	public Float deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		/*Number num = NumberUtils.createNumber(StringUtils.trimToNull(json.getAsString()));
		return (num == null) ? null : num.floatValue();*/
		if(StringUtils.isBlank(json.getAsString())){
			return null;
		}else{
			return Float.parseFloat(StringUtils.trim(json.getAsString()));
		}
	}

}
