package com.grande.volume.dados.olx;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class Frame extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5819173940742831670L;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private GroupLayout gl_contentPane;
	private GroupLayout gl_panel;
	private GroupLayout gl_panel_1;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					new Frame().setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame()
	{
		this.setTitle("Grande Volume de Dados");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(Frame.class.getResource("/com/grande/volume/dados/olx/icon/v.png")));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 667, 485);
	
		this.contentPane = new JPanel();


		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		this.setContentPane(contentPane);
		
		this.panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
					.addContainerGap())
		);
		
	    panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(352, Short.MAX_VALUE)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
	
		gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 601, Short.MAX_VALUE)
		);
		
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 52, Short.MAX_VALUE)
		);
		
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
