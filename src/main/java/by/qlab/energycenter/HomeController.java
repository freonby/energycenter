package by.qlab.energycenter;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import by.qlab.energycenter.dao.DAO;
import by.qlab.energycenter.model.DeviceRequest;
import by.qlab.energycenter.model.Interval30;

@Controller
public class HomeController {
	@Inject
	private DAO hibdao;
	// private static final Logger logger =
	// LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
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

}
