package com.eladmin.json;

import com.base.core.tools.DateTools;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

public class DateSerializer implements JsonSerializer<Date> {
	public JsonElement serialize(Date date, Type type, JsonSerializationContext context) {
		if (null == date) {
			return JsonNull.INSTANCE;
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			if ((cal.get(Calendar.YEAR) == 1970) && (cal.get(Calendar.MONTH) == 0) && (cal.get(Calendar.DATE) == 1)) {
				/** 如果只有时间内容,日期格式为默认值,那么仅输出时间 */
				return new JsonPrimitive(DateTools.format(date, DateTools.format_HHmmss));
			} else if ((cal.get(Calendar.HOUR_OF_DAY) == 0) && (cal.get(Calendar.MINUTE) == 0)
					&& (cal.get(Calendar.SECOND) == 0)) {
				/** 如果仅有日期内容,时间内容全部为0,那么仅仅输出日期 */
				return new JsonPrimitive(DateTools.format(date, DateTools.format_yyyyMMdd));
			} else {
				return new JsonPrimitive(DateTools.format(date));
			}
		}
	}
}