package com.processo.leao.verde;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.border.TitledBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frame extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5894810798778795207L;
	
	private JPanel contentPane, 
				   panel,
				   panel_1, 
				   panel_2;
	
	private GroupLayout gl_contentPane, 
						gl_panel, 
						gl_panel_2, 
						gl_panel_1;
	private JMenuBar menuBar;
	private JMenu mnArquivo;
	private JCheckBoxMenuItem chckbxmntmItem, chckbxmntmItem_1;
 
	  
	private int DEFAULT_SIZE =  GroupLayout.DEFAULT_SIZE;
	private int PREFERRED_SIZE = GroupLayout.PREFERRED_SIZE;
	private String VAZIO = "";
	private String CAMINHO = "/com/processo/leao/verde/icon";
	private JButton button_relatorio,
					button, 
					button_categoria, 
					btnCadastroProcesso, 
					button_Iniciar, 
					button_config, 
					button_5, 
					button_6,
					button_7, 
					button_8, 
					button_9, 
					button_10, 
					button_11, 
					button_12, 
					button_13, 
					button_14;

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
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public Frame() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException 
	{
		this.setTitle("Desktop");
		
		
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		 
	this.setIconImage(Toolkit.getDefaultToolkit().getImage(Frame.class.getResource(CAMINHO.concat("/v.png"))));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 561, 494);
		
		this.menuBar = new JMenuBar();
		
		this.setJMenuBar(this.menuBar);
		
		this.mnArquivo = new JMenu("Arquivo");
		this.menuBar.add(this.mnArquivo);
		
		this.chckbxmntmItem = new JCheckBoxMenuItem("item 1");
		this.mnArquivo.add(this.chckbxmntmItem);
		this.chckbxmntmItem_1 = new JCheckBoxMenuItem("item 2");
		this.mnArquivo.add(this.chckbxmntmItem_1);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		
		this.panel = new JPanel();
		this.panel.setBorder(new TitledBorder(null, this.VAZIO, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.gl_contentPane = new GroupLayout(this.contentPane);
		this.gl_contentPane.setHorizontalGroup(
				this.gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(this.gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.panel,this.DEFAULT_SIZE, 492, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		this.gl_contentPane.setVerticalGroup(
				this.gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(this.gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.panel,this.DEFAULT_SIZE, 424, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		this.panel_1 = new JPanel();
		this.panel_1.setBorder(new TitledBorder(null, this.VAZIO, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		this.panel_2 = new JPanel();
		this.panel_2.setBorder(new TitledBorder(null, this.VAZIO, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 
		this.gl_panel = new GroupLayout(this.panel);
		this.gl_panel.setHorizontalGroup(
				this.gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(this.panel_1, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
				.addComponent(this.panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		
		this.gl_panel.setVerticalGroup(
				this.gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(this.gl_panel.createSequentialGroup()
					.addComponent(this.panel_2, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(this.panel_1, this.PREFERRED_SIZE, 68, this.PREFERRED_SIZE))
		);
		 
		this.button_relatorio = new JButton(this.VAZIO);
		this.button_relatorio.setIcon(new ImageIcon(Frame.class.getResource(CAMINHO.concat("/relatorio1.png"))));
		
		this.button_categoria = new JButton(this.VAZIO);
		this.button_categoria.setIcon(new ImageIcon(Frame.class.getResource(CAMINHO.concat("/categoria.png"))));
		
		this.btnCadastroProcesso = new JButton("CAD Process");
		
		this.button_Iniciar = new JButton(this.VAZIO);
		this.button_Iniciar.setIcon(new ImageIcon(Frame.class.getResource(CAMINHO.concat("/iniciar.png"))));
		
		this.button_config = new JButton(this.VAZIO);
		this.button_config.setIcon(new ImageIcon(Frame.class.getResource(CAMINHO.concat("/config.png"))));
		
		this.button_5 = new JButton("   ");
		
		this.button_6 = new JButton("   ");
		
		this.button_7 = new JButton("   ");
		
		this.button_8 = new JButton("   ");
		
		this.button_9 = new JButton("   ");
		
		this.button_10 = new JButton("   ");
		
		this.button_11 = new JButton("   ");
		
		this.button_12 = new JButton("   ");
		
		this.button_13 = new JButton("   ");
		
		this.button_14 = new JButton("   ");
		this.gl_panel_2 = new GroupLayout(this.panel_2);
		this.gl_panel_2.setHorizontalGroup(
				this.gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(this.gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(this.gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addGroup(this.gl_panel_2.createSequentialGroup()
							.addComponent(this.button_relatorio, 	this.PREFERRED_SIZE, 90, this.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.button_categoria, 	this.PREFERRED_SIZE, 90, this.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(this.btnCadastroProcesso, this.PREFERRED_SIZE, 90, this.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(this.button_Iniciar, 		this.PREFERRED_SIZE, 90, this.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(this.button_config, 		this.PREFERRED_SIZE, 83, this.PREFERRED_SIZE)
							.addGap(18))
						.addGroup(this.gl_panel_2.createSequentialGroup()
							.addComponent(this.button_10, this.PREFERRED_SIZE, 90, this.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(this.button_11, this.PREFERRED_SIZE, 90, this.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(this.button_12, this.PREFERRED_SIZE, 90, this.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(this.button_13, this.PREFERRED_SIZE, 90, this.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(this.button_14, this.PREFERRED_SIZE, 90, this.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(this.gl_panel_2.createSequentialGroup()
							.addComponent(this.button_5, this.PREFERRED_SIZE, 90, this.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.button_6, this.PREFERRED_SIZE, 90, this.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(this.button_7, this.PREFERRED_SIZE, 90, this.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(this.button_8, this.PREFERRED_SIZE, 90, this.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
							.addComponent(this.button_9, this.PREFERRED_SIZE, 90, this.PREFERRED_SIZE)
							.addContainerGap())))
		);
		
		this.gl_panel_2.setVerticalGroup(
				this.gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(this.gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(this.gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(this.button_Iniciar, this.PREFERRED_SIZE, 80, this.PREFERRED_SIZE)
						.addComponent(this.btnCadastroProcesso, this.PREFERRED_SIZE, 80, this.PREFERRED_SIZE)
						.addComponent(this.button_categoria, this.PREFERRED_SIZE, 80, this.PREFERRED_SIZE)
						.addComponent(this.button_relatorio, this.PREFERRED_SIZE, 80, this.PREFERRED_SIZE)
						.addComponent(this.button_config, this.PREFERRED_SIZE, 80, this.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(this.gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(this.button_10, this.PREFERRED_SIZE, 80, this.PREFERRED_SIZE)
						.addComponent(this.button_11, this.PREFERRED_SIZE, 80, this.PREFERRED_SIZE)
						.addComponent(this.button_12, this.PREFERRED_SIZE, 80, this.PREFERRED_SIZE)
						.addComponent(this.button_13, this.PREFERRED_SIZE, 80, this.PREFERRED_SIZE)
						.addComponent(this.button_14, this.PREFERRED_SIZE, 80, this.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(this.gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.button_5, this.PREFERRED_SIZE, 80, this.PREFERRED_SIZE)
						.addComponent(this.button_6, this.PREFERRED_SIZE, 80, this.PREFERRED_SIZE)
						.addComponent(this.button_7, this.PREFERRED_SIZE, 80, this.PREFERRED_SIZE)
						.addComponent(this.button_8, this.PREFERRED_SIZE, 80, this.PREFERRED_SIZE)
						.addComponent(this.button_9, this.PREFERRED_SIZE, 80, this.PREFERRED_SIZE))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		
		this.panel_2.setLayout(this.gl_panel_2);
		
		this.button = new JButton(this.VAZIO);
		this.button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				System.exit(1);
			}
		});
		
		this.button.setIcon(new ImageIcon(Frame.class.getResource(CAMINHO.concat("/sair.png"))));
		this.gl_panel_1 = new GroupLayout(this.panel_1);
		this.gl_panel_1.setHorizontalGroup(
				this.gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, this.gl_panel_1.createSequentialGroup()
					.addContainerGap(435, Short.MAX_VALUE)
					.addComponent(this.button, this.PREFERRED_SIZE, 39, this.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		this.gl_panel_1.setVerticalGroup(
				this.gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(this.gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.button, this.PREFERRED_SIZE, 28, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		this.panel_1.setLayout(this.gl_panel_1);
		
		this.panel.setLayout(this.gl_panel);
		this.contentPane.setLayout(this.gl_contentPane);
	}
}