package czatGui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ConnectDialog extends JPanel{
	
	JTextField textHost;
	JButton okButton;
	JButton cancelButton;
	JDialog dialog;
	boolean ok;
	
	public ConnectDialog() {
		
		setLayout(new BorderLayout());
		textHost = new JTextField(15);
		textHost.setText("localhost");
		add(new JLabel("Adres:"), BorderLayout.WEST);
		add(textHost, BorderLayout.CENTER);
		okButton = new JButton("ok");
		cancelButton = new JButton("Anuluj");
		JPanel panel = new JPanel();
		panel.add(okButton);
		panel.add(cancelButton);
		this.add(panel, BorderLayout.SOUTH);
		okButton.addActionListener(event -> {
			ok = true;
			dialog.setVisible(false);
		});
		
		cancelButton.addActionListener(event -> {
			dialog.setVisible(false);
		});
	}
	public boolean showDialog(Component parent) {
		ok = false;
		
		Frame owner = null;
		if(parent instanceof Frame) {
			owner = (Frame) parent;
		}else {
			owner = (Frame)SwingUtilities.getAncestorOfClass(Frame.class, parent);
		}
		if (dialog == null || dialog.getOwner() != owner) {
			dialog = new JDialog(owner, true);
			dialog.add(this);
			dialog.pack();
		}
		dialog.setVisible(true);
		return ok;
	}
	public String getMessage() {
		return textHost.getText();
	}
}
