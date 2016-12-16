package by.qlab.energycenter;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import by.qlab.energycenter.model.DeviceRequest;
import jssc.SerialPortList;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		logger.error("Welcome home! The client locale is {}.", locale);
		ModelAndView mav = new ModelAndView();
		ResourceBundle rb = ResourceBundle.getBundle("messages");
		String[] portlist = SerialPortList.getPortNames();
		ArrayList<String> portList = new ArrayList<String>();
		for (int i = 0; i < portlist.length; i++) {
			portList.add(portlist[i]);

		}
		mav.setViewName("home");
		mav.addObject("device", new DeviceRequest());
		mav.addObject("portList", portList);
		return mav;
	}

	@RequestMapping(value = "/check-form", method = RequestMethod.GET)
	public ModelAndView goRead(@ModelAttribute("device") DeviceRequest dev) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		mav.addObject("addr", dev.getNetAddress());
		mav.addObject("port", dev.getPortName());

		return mav;
	}

}
