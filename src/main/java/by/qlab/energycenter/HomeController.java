package by.qlab.energycenter;

import java.util.Date;
import java.util.LinkedList;
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
		Customer customer = hibdao.getCustomer(" ТП 10/0.4 (технический учет)");
		Date from = DateParser.convertDateFromString("01.02.2017");
		Date to = DateParser.convertDateFromString("01.02.2017");
		List<Register> allData = hibdao.getAllRegisterList(from, to);
		List<TimeZone> timeZone = hibdao.getZone();
		List<IntervalStrings> stringsList = hibdao.getIntervals();
		// Customer customer = hibdao.getCustomer("ЧСУП ЖМС (коммерческий
		// учет)");
		List<Fider> fList = customer.allFiders();
		Fider first = null;
		if (fList != null)
			first = fList.get(1);
		session.setAttribute("customer", customer);
		session.setAttribute("allData", allData);
		session.setAttribute("timeZone", timeZone);
		session.setAttribute("stringsList", stringsList);
		session.setAttribute("emNumber", first.getEnergyMeter().getNumber());
		mav.setViewName("index");
		mav.addObject("customer", customer);

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
		List<Register> allData = (List<Register>) session.getAttribute("allData");
		int energyType = 1;
		String emeter = (String) session.getAttribute("emNumber");
		List<TimeZone> timeZone = (List<TimeZone>) session.getAttribute("timeZone");
		List<IntervalStrings> stringsList = (List<IntervalStrings>) session.getAttribute("stringsList");
		List<Register> list = Register.filterRegistersByEnergyTypeAndMeterNumber(allData, emeter, energyType);
		LinkedList<Register> graphdata = new LinkedList<Register>(list);
		// Register sum = graphdata.removeLast();
		// double sumConsumption = sum.getConsumption();
		// Register max = graphdata.removeLast();
		// double maxConsumption = max.getConsumption();
		// session.setAttribute("sum", sumConsumption);
		// session.setAttribute("max", maxConsumption);
		String json = JSONParser.to48Json(graphdata, timeZone, stringsList);
		return json;
	}

	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	@ResponseBody
	public String addUser(@RequestParam String param, HttpSession session) {

		// Customer c = AlfaCenterConverter.createCustomerById(100141);
		// hibdao.addCustomer(c);

		System.out.println("ПРОВЕРКА ============================================================");
		// List<Register> list = AlfaCenterConverter.createCustomerById(100141);
		// for (Register register : list) {
		// hibdao.addRegister(register);
		// }
		// list = AlfaCenterConverter.createCustomerById(100142);
		// for (Register register : list) {
		// hibdao.addRegister(register);
		// }

		System.out.println("ПРОВЕРКА =========================КОНЕЦ");

		return "";

	}

	@RequestMapping(value = "/info", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String info(@RequestParam String param, HttpSession session) {
		Customer c = (Customer) session.getAttribute("customer");
		Fider f = c.findFiderByName(param);
		if (f == null) {
			return "";
		}
		String emNumber = f.getEnergyMeter().getNumber();
		session.setAttribute("emNumber", emNumber);
		return f.toJson();
	}

	// @RequestMapping(value = "/max", method = RequestMethod.GET, produces =
	// "text/html;charset=utf-8")
	// @ResponseBody
	// public String max(HttpSession session) {
	// Customer c = (Customer) session.getAttribute("customer");
	// //Fider f = c.findFiderByName(param);
	// if (f == null) {
	// return "";
	// }
	// String emNumber = f.getEnergyMeter().getNumber();
	// session.setAttribute("emNumber", emNumber);
	// return f.toJson();
	// }

}
