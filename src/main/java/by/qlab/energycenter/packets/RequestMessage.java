package by.qlab.energycenter.packets;

import java.nio.ByteBuffer;

public class RequestMessage {
	private Offset offset;
	private Tarif tarif;
	private Refine refine;

	public RequestMessage(int offset, int tarif, int refine) {
		super();
		this.offset = new Offset(offset);
		this.tarif = new Tarif(tarif);
		this.refine = new Refine(refine);
	}
	public RequestMessage() {
		super();
		this.offset = new Offset(0);
		this.tarif = new Tarif(0);
		this.refine = new Refine(0);
	}
	public byte[] byteArray() {
		ByteBuffer buf = ByteBuffer.allocate(3);
		buf.put(this.offset.getCodeOffset());
		buf.put(this.tarif.getCodeTarif());
		buf.put(this.refine.getCodeRefine());
		return buf.array();

	}
	public Offset getOffset() {
		return offset;
	}
	public Tarif getTarif() {
		return tarif;
	}
	public Refine getRefine() {
		return refine;
	}
	public void setOffset(int offset) {
		this.offset = new Offset(offset);
	}
	public void setTarif(int tarif) {
		this.tarif = new Tarif(tarif);
	}
	public void setRefine(int refine) {
		this.refine = new Refine(refine);
	}
}
