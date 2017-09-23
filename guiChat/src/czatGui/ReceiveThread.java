package czatGui;

import java.io.IOException;

import javax.swing.JTextArea;

public class ReceiveThread implements Runnable{
	NetworkHandle nh;
	JTextArea ta;
	public ReceiveThread(NetworkHandle nh, JTextArea ta) {
		this.nh = nh;
		this.ta = ta;
	}
	public void run() {
		while(true) {
			try {
				// TODO invokeLater
				ta.append(nh.receive());
			}catch(IOException e) {
				break;
			}
		}
	}
}
