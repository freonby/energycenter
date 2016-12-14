package by.qlab.energycenter.drivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.qlab.energycenter.interfaces.Channel;
import jssc.SerialPort;
import jssc.SerialPortException;

public class SerialChannel implements Channel {
	private static final Logger logger = LoggerFactory.getLogger(SerialChannel.class);
	private SerialPort port;
	private PortSet portset;

	public SerialChannel(String portName, PortSet portset) {
		super();
		this.port = new SerialPort(portName);
		this.portset = portset;
	}

	public SerialPort getPort() {
		return port;
	}

	public void setPort(SerialPort port) {
		this.port = port;
	}

	@Override
	public void open() {
		try {
			port.openPort();
			port.setParams(portset.getBaudrate(), portset.getDatabits(), portset.getStopbits(), portset.getParity());
			port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE | SerialPort.FLOWCONTROL_NONE);
		} catch (SerialPortException e) {
			logger.error("Opening serial port - failed");

		}

	}

	@Override
	public void close() {
		try {
			if (port != null)

				port.closePort();

		} catch (SerialPortException e) {
			logger.error("Closing serial port - failed");

		}
	}

}
