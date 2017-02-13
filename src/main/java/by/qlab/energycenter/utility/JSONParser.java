package by.qlab.energycenter.utility;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import by.qlab.energycenter.databuffer.IntervalStrings;
import by.qlab.energycenter.databuffer.Register;
import by.qlab.energycenter.databuffer.TimeZone;
import by.qlab.energycenter.model.Item30;

public class JSONParser {

	public static String to48Json(List<Register> listRegisters, List<TimeZone> timeZone, List<IntervalStrings> stringsList) {
		if (listRegisters == null) {
			return "";
		}
		ArrayList<Item30> list = new ArrayList<Item30>();
		Item30 item = null;
		for (Register reg : listRegisters) {
			int intervalNumber = reg.getIntervalNumber();
			Object[] strings = IntervalStrings.findByInterval(stringsList, intervalNumber);
			String intervalName = (String) strings[0];
			String color = TimeZone.findByCode(timeZone, (Integer) strings[1]);
			item = new Item30(intervalName, reg.getConsumption(), color);
			list.add(item);
		}
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;

	}

}
