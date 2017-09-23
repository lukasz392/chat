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

public class NameDialog extends JPanel{
	JDialog dialog;
	JTextField textField;
	JButton okButton;
	JButton cancelButton;
	boolean ok;
	
	public NameDialog() {
		setLayout(new BorderLayout());
		JPanel textPanel = new JPanel();
		
		textPanel.add(new JLabel("Nick:"));
		textField = new JTextField(15);
		textPanel.add(textField);
		add(textPanel, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		okButton = new JButton("ok");
		cancelButton = new JButton("Anuluj");
		panel.add(okButton);
		okButton.addActionListener(event -> {
			ok = true;
			dialog.setVisible(false);
		});
		panel.add(cancelButton);
		cancelButton.addActionListener(event -> {
			dialog.setVisible(false);
		});
		add(panel, BorderLayout.SOUTH);
	}
	public boolean showDialog(Component parent) {
		ok = false;
		
		Frame owner = null;
		if(parent instanceof Frame) {
			owner = (Frame) parent;
		}else {
			owner = (Frame)SwingUtilities.getAncestorOfClass(Frame.class, parent);
		}
		if(dialog == null || dialog.getOwner() != owner) {
			dialog = new JDialog(owner, true);
			dialog.add(this);
			dialog.pack();
		}
		dialog.setVisible(true);
		return ok;
	}
	public String getName() {
		return textField.getText();
	}
}
