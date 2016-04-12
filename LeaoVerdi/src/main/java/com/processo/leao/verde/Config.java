package com.processo.leao.verde;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.processo.leao.verde.config.ConfigDAO;

public class Config extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -184435028709307833L;
	private JPanel contentPane,panel,panel_1,panel_2;
	private JTable table;
	private GroupLayout gl_contentPane,gl_panel,gl_panel_1,gl_panel_2;
	private JButton btnSair,btnSalvar , button ;
	private JScrollPane scrollPane ;
	private String VAZIO = "";
	
	
	 
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	@SuppressWarnings("serial")
	public Config(JFrame f) throws Exception
	{
		super(f, "", true);
		
		this.setTitle("Config");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(Config.class.getResource("/com/processo/leao/verde/icon/config.png")));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 596, 503);
		
		
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		
		this.panel = new JPanel();
		this.panel_1 = new JPanel();
		this.panel_2 = new JPanel();
		
		this.panel.setBorder(new TitledBorder(null, VAZIO, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panel_1.setBorder(new TitledBorder(null, VAZIO, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panel_2.setBorder(new TitledBorder(null, VAZIO, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		
		
		
		this.gl_contentPane = new GroupLayout(this.contentPane);
		this.gl_contentPane.setHorizontalGroup(
				this.gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(this.gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.panel, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		this.gl_contentPane.setVerticalGroup(
				this.gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, this.gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.panel, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		this.gl_panel = new GroupLayout(this.panel);
		this.gl_panel.setHorizontalGroup(
				this.gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, this.gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(this.gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(this.panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
						.addComponent(this.panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		this.gl_panel.setVerticalGroup(
				this.gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(this.gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.panel_1, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.panel_2, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		this.scrollPane = new JScrollPane();
		this.gl_panel_1 = new GroupLayout(this.panel_1);
		this.gl_panel_1.setHorizontalGroup(
				this.gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(this.scrollPane, GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
		);
		
		this.gl_panel_1.setVerticalGroup(
				this.gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(this.scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
		);
		
		this.table = new JTable();
		this.table.setModel(new DefaultTableModel(
				new ConfigDAO().getConfig(),
				new String[] {
					"id","Nome", "Categoria", "URL","Status"
				}
             ) {
                 @SuppressWarnings("rawtypes")
				Class[] types = new Class [] {
                    Integer.class,String.class,String.class,String.class, Boolean.class
                 };
                 boolean[] canEdit = new boolean [] {
                     false, false, false, false,true
                 };
                 @Override  
                 public boolean isCellEditable(int rowIndex, int columnIndex){  
                     return canEdit [columnIndex];  
                 }  
                 @SuppressWarnings({
						"unchecked", "rawtypes"
				})
				public Class getColumnClass(int columnIndex) {
                     return types [columnIndex];
                 }
			
		});
		
		
		
		this.scrollPane.setViewportView(this.table);
		this.panel_1.setLayout(this.gl_panel_1);
		
		this.btnSair = new JButton("Sair");
		  this.btnSair.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		dispose();
		  	}
		  });
		
		  this.btnSalvar = new JButton("Salvar");
		  this.btnSalvar.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		
		  	}
		  });
		  
		  this.button = new JButton("+");
		  this.button.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		new CadastroConfig(null).setVisible(true);
		  	}
		  });

		this.gl_panel_2 = new GroupLayout(this.panel_2);
		this.gl_panel_2.setHorizontalGroup(
				this.gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, this.gl_panel_2.createSequentialGroup()
					.addContainerGap(280, Short.MAX_VALUE)
					.addComponent(this.button)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(this.btnSalvar)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(this.btnSair)
					.addGap(19))
		);
		
		this.gl_panel_2.setVerticalGroup(
				this.gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(this.gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(this.gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.btnSair)
						.addComponent(this.btnSalvar)
						.addComponent(this.button))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		
		this.panel_2.setLayout(this.gl_panel_2);
		this.panel.setLayout(this.gl_panel);
		this.contentPane.setLayout(this.gl_contentPane);
	}
}
