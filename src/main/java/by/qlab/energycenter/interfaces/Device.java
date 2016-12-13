package by.qlab.energycenter.interfaces;

import java.util.LinkedList;

public interface Device {

	public Answer read(LinkedList<Packet> list);

	public Answer write(LinkedList<Packet> list);

	public boolean synch(LinkedList<Packet> list);

}
