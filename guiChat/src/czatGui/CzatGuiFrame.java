package czatGui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CzatGuiFrame extends JFrame{
	
	JMenuBar mbar;
	JMenu menu;
	JMenuItem connectItem;
	JMenuItem nameItem;
	JMenuItem exitItem;
	JTextArea textArea;
	JTextField textField;
	ActionListener connectAction;
	String host;
	JFrame owner;
	ConnectDialog connectDialog;
	NameDialog nameDialog;
	int port = 55555;
	Socket socket;
	NetworkHandle connect;
	String name;
	ReceiveThread thread;
	
	public CzatGuiFrame() {
		
		setSize(200, 300);
		owner = this;
		mbar = new JMenuBar();
		menu = new JMenu("Opcje");
		connectItem = new JMenuItem("Połącz");
		nameItem = new JMenuItem("Zmień nick");
		exitItem = new JMenuItem("Wyjście");
		menu.add(connectItem);
		menu.add(nameItem);
		menu.addSeparator();
		menu.add(exitItem);
		mbar.add(menu);
		setJMenuBar(mbar);
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		setLayout(new BorderLayout());
		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new JLabel("Wpisz tekst:"), BorderLayout.WEST);
		textField = new JTextField();
		panel.add(textField, BorderLayout.CENTER);
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(panel, BorderLayout.SOUTH);
		textArea.append("Komunikator wersja: alpha (0.2)\n");
		textField.addActionListener(event -> {
			if(connect != null) {
				try {
					connect.send(name + ": " + textField.getText());
				}catch(IOException e) {
					textArea.append(e.toString());
				}
			}
			//textArea.append(name + ": " + textField.getText() + "\n");
			textField.setText("");
		});
		ChangeNameAction changeNameAction = new ChangeNameAction();
		connectItem.addActionListener(new ConnectAction());
		nameItem.addActionListener(changeNameAction);
		nameDialog = new NameDialog();
		if(nameDialog.showDialog(this)) {
			name = nameDialog.getName();
		}
	}
	
	private class ConnectAction implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(connectDialog == null) connectDialog = new ConnectDialog();
			if(connectDialog.showDialog(CzatGuiFrame.this)) {
				String message = connectDialog.getMessage();
				try {
					socket = new Socket(message, port);
					connect = new NetworkHandle(socket);
					textArea.append("Połączono z serverem: " + message + "\n");
					new Thread(new ReceiveThread(connect, textArea)).start();
				} catch (IOException e) {
					textArea.append("Nie udało sie połączyć z serwerem\n");
					System.out.println(e);
				}
				
			}
		}
	}
	private class ChangeNameAction implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(nameDialog == null) nameDialog = new NameDialog();
			if(nameDialog.showDialog(CzatGuiFrame.this)) {
				name = nameDialog.getName();
			}
		}
	}
}

