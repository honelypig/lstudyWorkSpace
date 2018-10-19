package org.rpc.remoting.model;

public class ByteHolder {
	private transient byte[] bytes;

	public byte[] bytes() {
		return bytes;
	}

	public void bytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public int getSize() {
		return bytes==null?0:bytes.length;
	}
}
