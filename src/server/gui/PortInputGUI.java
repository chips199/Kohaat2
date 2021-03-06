package server.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import server.main.Controller;

import java.awt.GridBagLayout;
import javax.swing.JSpinner;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PortInputGUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Controller controller;
	private JSpinner spinnerPort;

	/**
	 * Create the dialog.
	 */
	public PortInputGUI(Controller controller) {
		this.controller = controller;
		
		setResizable(false);
		setAlwaysOnTop(true);
		setTitle("Kohaat - Port Eingabe");
		setBounds(100, 100, 185, 148);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblPort = new JLabel("Geben Sie einen Port an:");
			GridBagConstraints gbc_lblPort = new GridBagConstraints();
			gbc_lblPort.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblPort.insets = new Insets(0, 0, 5, 5);
			gbc_lblPort.gridx = 0;
			gbc_lblPort.gridy = 0;
			contentPanel.add(lblPort, gbc_lblPort);
		}
		{
			spinnerPort = new JSpinner();
			spinnerPort.setModel(new SpinnerNumberModel(new Integer((int) (Math.random() * 1000 + 1000)), null, null, new Integer(1)));
			GridBagConstraints gbc_spinnerPort = new GridBagConstraints();
			gbc_spinnerPort.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinnerPort.insets = new Insets(0, 0, 0, 5);
			gbc_spinnerPort.gridx = 0;
			gbc_spinnerPort.gridy = 1;
			contentPanel.add(spinnerPort, gbc_spinnerPort);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						controller.setPort((int) spinnerPort.getValue());
						controller.startServer();
						dispose();
						JOptionPane.showMessageDialog(null, "Der Server l�uft jetzt unter Port " + spinnerPort.getValue() + ".", "Information", JOptionPane.INFORMATION_MESSAGE); 
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

}
