package czatGui;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class CzatGuiStart {
	
	
	public static void main(String[] args) {
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				JFrame frame = new CzatGuiFrame();
				frame.setTitle("Chat");
				
				frame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent event) {
						System.out.println();
					}
				});
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
