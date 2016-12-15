package by.qlab.energycenter.interfaces;

public interface Channel {
	public void open();

	public void close();

	public Answer send(Packet packet);
}
