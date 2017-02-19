package by.qlab.energycenter;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import by.qlab.energycenter.dao.DAO;
import by.qlab.energycenter.databuffer.IntervalStrings;
import by.qlab.energycenter.databuffer.Register;
import by.qlab.energycenter.databuffer.TimeZone;
import by.qlab.energycenter.datamodel.Customer;
import by.qlab.energycenter.datamodel.Fider;
import by.qlab.energycenter.model.DeviceRequest;
import by.qlab.energycenter.utility.DateParser;
import by.qlab.energycenter.utility.JSONParser;

@Controller
public class HomeController {
	@Inject
	private DAO hibdao;
	// private static final Logger logger =
	// LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session) {

		ModelAndView mav = new ModelAndView();
		// Test.initCustomerBase(hibdao);
		// Customer customer = hibdao.getCustomer("ДюнаЭнерго");
		Customer customer = hibdao.getCustomer("Предприятие");
		// Date from = DateParser.convertDateFromString("01.02.2017");
		// Date to = DateParser.convertDateFromString("01.02.2017");
		Date last = hibdao.findLastDate();
		List<Register> allData = hibdao.getAllRegisterList(last, last);
		List<TimeZone> timeZone = hibdao.getZone();
		List<IntervalStrings> stringsList = hibdao.getIntervals();
		List<Fider> fList = customer.allFiders();
		Fider first = null;
		if (fList != null)
			first = fList.get(0);
		List<Register> list = Register.filterRegistersByEnergyTypeAndMeterNumber(allData, first.getEnergyMeter().getNumber(), 1);
		session.setAttribute("emNumber", first.getEnergyMeter().getNumber());
		session.setAttribute("firstName", first.getName());
		session.setAttribute("energyType", 1);
		session.setAttribute("customer", customer);
		session.setAttribute("allData", allData);
		session.setAttribute("timeZone", timeZone);
		session.setAttribute("stringsList", stringsList);
		session.setAttribute("graphdata", list);
		session.setAttribute("intervalType", 30);
		mav.setViewName("index");
		mav.addObject("customer", customer);
		mav.addObject("deviceDate", DateParser.convertDateTo(last));

		return mav;
	}
	/*
	 * public ModelAndView home() { ModelAndView mav = new ModelAndView();
	 * ResourceBundle rb = ResourceBundle.getBundle("messages"); String[]
	 * portlist = SerialPortList.getPortNames(); ArrayList<String> portList =
	 * new ArrayList<String>(); for (int i = 0; i < portlist.length; i++) {
	 * portList.add(portlist[i]); } mav.setViewName("home");
	 * mav.addObject("device", new DeviceRequest()); mav.addObject("portList",
	 * portList); return mav; }
	 */

	@RequestMapping(value = "/check-form", method = RequestMethod.GET)
	public ModelAndView goRead(@ModelAttribute("device") DeviceRequest dev) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		mav.addObject("addr", dev.getNetAddress());
		mav.addObject("port", dev.getPortName());

		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/gson", method = RequestMethod.GET)
	@ResponseBody
	public String goJson(HttpSession session) {
		List<TimeZone> timeZone = (List<TimeZone>) session.getAttribute("timeZone");
		List<IntervalStrings> stringsList = (List<IntervalStrings>) session.getAttribute("stringsList");
		List<Register> graphdata = (List<Register>) session.getAttribute("graphdata");
		int intervalType = (Integer) session.getAttribute("intervalType");
		switch (intervalType) {
		case 30:
			String json30 = JSONParser.to48Json(graphdata, timeZone, stringsList);
			return json30;
		case 60:
			String json60 = JSONParser.to24Json(graphdata, timeZone, stringsList);
			return json60;

		}

		return "";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/info", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String info(@RequestParam String param, HttpSession session) {
		Customer c = (Customer) session.getAttribute("customer");
		if (param.equals("first")) {
			param = (String) session.getAttribute("firstName");
		}
		Fider f = c.findFiderByName(param);
		if (f == null) {
			return "";
		}
		String emNumber = f.getEnergyMeter().getNumber();
		List<Register> allData = (List<Register>) session.getAttribute("allData");
		int energyType = (Integer) session.getAttribute("energyType");
		List<Register> graphdata = Register.filterRegistersByEnergyTypeAndMeterNumber(allData, emNumber, energyType);
		session.setAttribute("emNumber", emNumber);
		session.setAttribute("graphdata", graphdata);
		return f.toJson();
	}

	@SuppressWarnings({ "deprecation" })
	@RequestMapping(value = "/date", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String date(@RequestParam String startDate, HttpSession session) {

		Date d = new Date(startDate);
		int energyType = (Integer) session.getAttribute("energyType");
		String emNumber = (String) session.getAttribute("emNumber");
		List<Register> allData = hibdao.getAllRegisterList(d, d);
		session.setAttribute("allData", allData);
		List<Register> graphdata = Register.filterRegistersByEnergyTypeAndMeterNumber(allData, emNumber, energyType);
		session.setAttribute("graphdata", graphdata);
		Gson gson = new Gson();
		String json = gson.toJson(DateParser.convertDateTo(d));
		return json;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/dispatch", method = RequestMethod.GET)
	@ResponseBody
	public String dispatch(@RequestParam int paramEnergy, HttpSession session) {
		/******************* codes *************************/
		/** Types **********************************/
		/*
		 * 1:A+; 2:A-; 3:R+; 4:R-; 5:max value for graph view;
		 */
		List<Register> allData = (List<Register>) session.getAttribute("allData");
		String emNumber = (String) session.getAttribute("emNumber");

		switch (paramEnergy) {
		case 1:
		case 2:
		case 3:
		case 4:
			session.setAttribute("energyType", paramEnergy);
			List<Register> graphdata = Register.filterRegistersByEnergyTypeAndMeterNumber(allData, emNumber, paramEnergy);
			session.setAttribute("graphdata", graphdata);
			break;
		case 30:
			session.setAttribute("intervalType", paramEnergy);
			break;
		case 60:
			session.setAttribute("intervalType", paramEnergy);
			break;
		case 5:

		default:

		}

		return "";
	}

}
