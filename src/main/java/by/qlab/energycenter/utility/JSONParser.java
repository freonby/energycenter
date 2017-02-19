package by.qlab.energycenter.utility;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import by.qlab.energycenter.databuffer.IntervalStrings;
import by.qlab.energycenter.databuffer.ItemConsumption;
import by.qlab.energycenter.databuffer.Register;
import by.qlab.energycenter.databuffer.TimeZone;

public class JSONParser {

	public static String to48Json(List<Register> listRegisters, List<TimeZone> timeZone, List<IntervalStrings> stringsList) {
		if (listRegisters.size() == 0) {
			return "";
		}
		ArrayList<ItemConsumption> list = new ArrayList<ItemConsumption>();
		ItemConsumption item = null;
		for (Register reg : listRegisters) {
			int intervalNumber = reg.getIntervalNumber();
			double readout = reg.getReadout();
			Object[] strings = IntervalStrings.findByInterval(stringsList, intervalNumber);
			String intervalName = (String) strings[0];
			String color = TimeZone.findByCode(timeZone, (Integer) strings[1]);
			item = new ItemConsumption(intervalName, (float) reg.getConsumption(), color, readout);
			list.add(item);
		}
		int correctSize = 48;

		if (list.size() < correctSize) {

			for (int i = list.size() + 1; i <= correctSize - 1; i++) {
				Object[] strings = IntervalStrings.findByInterval(stringsList, i);
				String intervalName = (String) strings[0];
				String color = TimeZone.findByCode(timeZone, (Integer) strings[1]);
				item = new ItemConsumption(intervalName, 0f, color, 0d);
				list.add(item);
			}
		}
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;

	}

	public static String to24Json(List<Register> listRegisters, List<TimeZone> timeZone, List<IntervalStrings> stringsList) {
		if (listRegisters.size() == 0) {
			return "";
		}
		ArrayList<ItemConsumption> list = new ArrayList<ItemConsumption>();
		ItemConsumption item = null;
		for (int i = 0; i < 48; i += 2) {
			float consumption1 = (float) listRegisters.get(i).getConsumption();
			float consumption2 = (float) listRegisters.get(i + 1).getConsumption();
			float sum = consumption1 + consumption2;
			int intervalNumber = listRegisters.get(i + 1).getIntervalNumber();
			double readout = listRegisters.get(i + 1).getReadout();
			Object[] strings = IntervalStrings.findByInterval(stringsList, intervalNumber);
			String intervalName = (String) strings[0];
			String color = TimeZone.findByCode(timeZone, (Integer) strings[1]);
			item = new ItemConsumption(intervalName, sum, color, readout);
			list.add(item);

		}

		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;

	}

}
