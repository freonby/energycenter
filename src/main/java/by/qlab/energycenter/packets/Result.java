package by.qlab.energycenter.packets;

public class Result {
	private Byte resultCode;
	private String resultMessage;

	public Result(int resultCode) {
		super();
		this.resultCode = new Byte((byte) resultCode);
		this.resultMessage=getResultMsg();
	}

	public Result(byte resultCode) {
		super();
		this.resultCode = new Byte(resultCode);
		this.resultMessage=getResultMsg();
	}

	public Byte getResultCode() {
		return resultCode;
	}

	public void setResultCode(Byte resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		String msg = null;
		switch (resultCode) {
		case 0:
			msg = "��";
			break;
		case 1:
			msg = "����������� �������";
			break;
		case 2:
			msg = "����������� ��������";
			break;
		case 3:
			msg = "��������� ��������";
			break;
		case 4:
			msg = "������������������� ������. ��������� ���������� ������";
			break;
		case 5:
			msg = "���� ���������";
			break;
		case 6:
			msg = "������ ������";
			break;
		case 7:
			msg = "������� �����";
			break;

		default:
			msg = "��������� ������ �� ���������";
		}
		return msg;
	}

	public String getResultMessage() {
		return resultMessage;
	}
}
