package com.eladmin.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.lang.reflect.Type;

public class IntegerDeserializer implements JsonDeserializer<Integer> {

	@Override
	public Integer deserialize(JsonElement json, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		Number num = NumberUtils.createNumber(StringUtils.trimToNull(json.getAsString()));
		return (num == null) ? null : num.intValue();
	}

}