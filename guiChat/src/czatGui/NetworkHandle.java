package czatGui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.Socket;

public class NetworkHandle {
	Socket socket;
	String socketAdress;
	int port;
	//DataOutputStream outs;
	//DataInputStream ins;
	StringReader reader;
	InputStreamReader ins;
	OutputStreamWriter outs;
	
	
	public NetworkHandle(Socket socket) throws IOException{
		this.socket = socket;
		outs = new OutputStreamWriter(socket.getOutputStream(), "UTF-16");
		ins = new InputStreamReader(socket.getInputStream(), "UTF-16");
	}
	public void send(String m) throws IOException{
		outs.write(m.length());
		outs.write(m);
		outs.flush();
	}
	public String receive() throws IOException{
		char [] m = "".toCharArray();
		int length = ins.read();
		if(length > 0) {
			m = new char[length];
			ins.read(m);
		}
		return new String(m) + "\n";
	}
}
