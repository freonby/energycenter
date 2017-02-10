package by.qlab.energycenter;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import by.qlab.energycenter.dao.DAO;
import by.qlab.energycenter.datamodel.Test;
import by.qlab.energycenter.model.DeviceRequest;
import by.qlab.energycenter.model.Interval30;

@Controller
public class HomeController {
	@Inject
	private DAO hibdao;
	// private static final Logger logger =
	// LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();

		/*
		 * Customer customer = new Customer(); customer.setCompanyName("ЖМС");
		 * hibdao.addCustomer(customer); Customer customer1 =
		 * hibdao.getCustomer("ЖМС"); TransformerStation ts = new
		 * TransformerStation(); ts.setName("ПС 110кВ ФИМ"); ts.setId(1);
		 * ts.setCustomer(customer1); Set<TransformerStation> tset = new
		 * HashSet<TransformerStation>(); tset.add(ts);
		 * customer1.setListStations(tset); hibdao.updateCustomer(customer1);
		 */
		mav.setViewName("index");
		Interval30 data = hibdao.getInterval30Day(3128814);
		mav.addObject("deviceType", "CC301");
		mav.addObject("deviceNumber", data.getDevice_id());
		mav.addObject("fider", data.getFiderName());
		mav.addObject("deviceTime", data.getTime());

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

	@RequestMapping(value = "/gson", method = RequestMethod.GET)
	@ResponseBody
	public String goJson() {
		Interval30 data = hibdao.getInterval30Day(3128814);
		return data.toJson();
	}

	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	@ResponseBody
	public String addUser(@RequestParam String param) {

		// Test.initUser(hibdao, param);
		Test.initCustomerBase(hibdao);
		// Test.getCustomer(hibdao, param);
		// Test.initCustomer(hibdao, param);
		// Test.addTransStation(hibdao, param);
		// Test.changeUser(hibdao, "Альф3333");
		// Test.addElectricalBus(hibdao, param);
		// Test.delCustomer(hibdao, param);
		return "";
	}

}
