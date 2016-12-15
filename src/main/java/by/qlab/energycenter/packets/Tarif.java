package by.qlab.energycenter.packets;

public class Tarif {
	private Byte codeTarif;

	public Tarif(int codeTarif) {
		super();
		this.codeTarif = new Byte((byte)codeTarif);
	}

	public Byte getCodeTarif() {
		return codeTarif;
	}

	public void setCodeTarif(Byte codeTarif) {
		this.codeTarif = codeTarif;
	}

}
