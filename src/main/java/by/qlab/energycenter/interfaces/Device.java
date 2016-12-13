package by.qlab.energycenter.interfaces;

public interface Device {

	public Answer read();

	public Answer write();

	public void synch();
}
