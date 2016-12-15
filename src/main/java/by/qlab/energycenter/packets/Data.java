package by.qlab.energycenter.packets;



public class Data {
	private byte[] data;

	public Data(byte[] input) {
		super();
		this.data = trimInputToData(input);
	}
	public Data(byte input) {
		super();
		this.data = new byte[1];
		data[0]=input;
	}

	public byte[] getData() {
		return data;
	}

	private byte[] trimInputToData(byte[] input) {
		byte[] arraydata = new byte[input.length - 6];
		System.arraycopy(input, 4, arraydata, 0, input.length - 6);
		return arraydata;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
public char[] toCharSequence(){
	char[] seq=new char[data.length];
	for (int i = 0; i < seq.length; i++) {
		seq[i]=(char)data[i];
	}
	return seq;
}

@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	for (int i = 0; i < data.length; i++) {
		builder.append(data[i]);
		builder.append(" ");
	}
	return builder.toString();
}
}
