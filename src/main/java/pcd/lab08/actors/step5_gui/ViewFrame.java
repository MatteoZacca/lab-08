package pcd.lab08.actors.step5_gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import akka.actor.ActorRef;

public class ViewFrame extends JFrame {

	public ViewFrame(ActorRef actorView) {
		super("Test Swing | Actors interaction");
		log("[" + Thread.currentThread().getName() + "]: entered in ViewFrame's constructor");
		setSize(400, 70);
		JButton button = new JButton("Press me");
		button.addActionListener((ActionEvent ev) -> {
			log("[" + Thread.currentThread().getName() + "]: pressed button");
			actorView.tell(new PressedMsg(), ActorRef.noSender());
		});
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(button);
		getContentPane().add(panel);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(-1);
			}
		});
	}
	
	public void display() {
		SwingUtilities.invokeLater(() -> {
			this.setVisible(true);
		});
	}

	private static void log (String msg) {
		System.out.println("Debug in [ViewFrame] ---> " + msg);
	}
}