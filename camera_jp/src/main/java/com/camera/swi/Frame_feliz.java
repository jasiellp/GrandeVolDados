package com.camera.swi;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.com.icones.Icone_Feliz;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamImageTransformer;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

public class Frame_feliz extends JFrame
{
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5353029960072011955L;
	private JPanel	          contentPane;
	
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
					new Frame_feliz().setVisible(true);
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
	public Frame_feliz()
	{
		this.setTitle("Camera Feliz");
		
		try
		{
			UIManager
			        .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InstantiationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (UnsupportedLookAndFeelException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setIconImage(new ImageIcon(Icone_Feliz.class.getResource("Nikon.JPG"))
		        .getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Camera", TitledBorder.LEADING,
		        TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(
		        Alignment.LEADING).addGroup(
		        gl_contentPane
		                .createSequentialGroup()
		                .addContainerGap()
		                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 404,
		                        Short.MAX_VALUE).addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
		        Alignment.LEADING).addGroup(
		        gl_contentPane
		                .createSequentialGroup()
		                .addContainerGap()
		                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 230,
		                        Short.MAX_VALUE).addContainerGap()));
		webcam = Webcam.getDefault();
		final WebcamPanel panel_1 = new WebcamPanel(webcam, false);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		        TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		        TitledBorder.TOP, null, null));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
		        .createParallelGroup(Alignment.LEADING)
		        .addGroup(
		                Alignment.TRAILING,
		                gl_panel.createSequentialGroup()
		                        .addContainerGap()
		                        .addGroup(
		                                gl_panel.createParallelGroup(
		                                        Alignment.TRAILING)
		                                        .addComponent(
		                                                panel_1,
		                                                Alignment.LEADING,
		                                                GroupLayout.DEFAULT_SIZE,
		                                                384, Short.MAX_VALUE)
		                                        .addComponent(
		                                                panel_2,
		                                                Alignment.LEADING,
		                                                GroupLayout.DEFAULT_SIZE,
		                                                384, Short.MAX_VALUE))
		                        .addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(
		        Alignment.LEADING).addGroup(
		        Alignment.TRAILING,
		        gl_panel.createSequentialGroup()
		                .addContainerGap()
		                .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 262,
		                        Short.MAX_VALUE)
		                .addPreferredGap(ComponentPlacement.RELATED)
		                .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 49,
		                        GroupLayout.PREFERRED_SIZE).addContainerGap()));
		
		btnDigaX = new JButton("diga x");
		btnDigaX.setEnabled(false);
		
		// capture()
		
		btnDigaX.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (btnDigaX.isEnabled()) capture();
			}
		});
		
		btnSair = new JButton("Sair");
		
		btnLigar = new JButton("ligar");
		
		btnLigar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				
				if (btnDigaX.isEnabled())
				{
					btnDigaX.setEnabled(false);
					btnLigar.setText("ligar");
					
					if (webcam.isOpen()) webcam.close();
				}
				else
				{
					btnDigaX.setEnabled(true);
					btnLigar.setText("Desligar");
					
					webcam.setViewSize(size);
					
					// panel_cam = new WebcamPanel(webcam, false);
					panel_1.setFPSDisplayed(true);
					// panel_1.add(panel_cam);
					
					if (webcam.isOpen()) webcam.close();
					
					int i = 0;
					do
					{
						if (webcam.getLock().isLocked())
						{
							System.out
							        .println("Waiting for lock to be released "
							                + i);
							try
							{
								Thread.sleep(100);
							}
							catch (InterruptedException e1)
							{
								return;
							}
						}
						else
						{
							break;
						}
					}
					while (i++ < 3);
					
					webcam.open();
					panel_1.start();
				}
				
			}
		});
		
		btnSair.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(30);
			}
		});
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(
		        Alignment.LEADING).addGroup(
		        Alignment.TRAILING,
		        gl_panel_2.createSequentialGroup()
		                .addContainerGap(157, Short.MAX_VALUE)
		                .addComponent(btnLigar)
		                .addPreferredGap(ComponentPlacement.UNRELATED)
		                .addComponent(btnDigaX)
		                .addPreferredGap(ComponentPlacement.RELATED)
		                .addComponent(btnSair).addContainerGap()));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(
		        Alignment.LEADING)
		        .addGroup(
		                gl_panel_2
		                        .createSequentialGroup()
		                        .addContainerGap()
		                        .addGroup(
		                                gl_panel_2
		                                        .createParallelGroup(
		                                                Alignment.BASELINE)
		                                        .addComponent(btnSair)
		                                        .addComponent(btnDigaX)
		                                        .addComponent(btnLigar))
		                        .addContainerGap(15, Short.MAX_VALUE)));
		panel_2.setLayout(gl_panel_2);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(
		        Alignment.LEADING).addGap(0, 384, Short.MAX_VALUE));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(
		        Alignment.LEADING).addGap(0, 262, Short.MAX_VALUE));
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void capture()
	{
		
		WebcamImageTransformer trans =	webcam.getImageTransformer();
		ImageIO.read(Icone_Feliz.class.getResourceAsStream("/" + image));
		trans.transform(null);
		
		webcam.setImageTransformer(trans);
		
		
		BufferedImage bi = webcam.getImage();
		int dialogResult =
		
		JOptionPane.showConfirmDialog(null, null, "Gostou deseja salvar ?",
		        JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION,
		        new ImageIcon(bi));
		
		if (dialogResult == JOptionPane.YES_OPTION)
		{
			
			JFileChooser chooser = new JFileChooser();
			
			int retrival = chooser.showSaveDialog(null);
			
			if (retrival == JFileChooser.APPROVE_OPTION)
			{
				try
				{
					ImageIO.write(bi, "PNG", new File(chooser.getSelectedFile()
					        .toString().concat(".png")));
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
	}
	
	protected Dimension	  size	      = WebcamResolution.QVGA.getSize();
	protected Webcam	  webcam	  = null;
	protected WebcamPanel	panel_cam	= null;
	
	protected JButton	  btnDigaX;
	protected JButton	  btnSair;
	protected JButton	  btnLigar;
}
