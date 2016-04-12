package com.processo.leao.verde;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import com.processo.leao.verde.util.DBLeao;

public class CadastroConfig extends JDialog
{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel,panel,panel_1; 
	private GroupLayout gl_contentPanel,gl_panel_1,gl_panel;
	private JTextField textField_URL,textField_Categoria,textField_Nome;
	private JButton okButton, cancelButton;
	private JLabel lblUrl ,lblCategoria ,lblNome;
	private JCheckBox chckbxAtivar ,chckbxDesativar;
	  
	
	 
	/**
	 * Create the dialog.
	 */
	public CadastroConfig(JFrame f)
	{
		super(f, "Cadastro Config", true);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(CadastroConfig.class.getResource("/com/processo/leao/verde/icon/config.png")));
		this.setBounds(100, 100, 452, 214);
		this.getContentPane().setLayout(new BorderLayout());
		
		this.contentPanel = new JPanel();
		
		this.contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		
		
		this.panel = new JPanel();
		this.panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		 
		
		this.panel_1 = new JPanel();
		this.panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		  
		this.gl_contentPanel = new GroupLayout(this.contentPanel);
		this.gl_contentPanel.setHorizontalGroup(
				this.gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, this.gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(this.gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(this.panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
						.addComponent(this.panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		this.gl_contentPanel.setVerticalGroup(
				this.gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(this.gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.panel, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		{
			this.cancelButton = new JButton("Cancel");
			this.cancelButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					 dispose();
				}
			});
			this.cancelButton.setActionCommand("Cancel");
		}
		{
			this.okButton = new JButton("OK");
			this.okButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					
					StringBuffer sb = new StringBuffer();
				
					sb.append("INSERT INTO configure_frame ");
					sb.append(" nome, 		 ").append(" \n");   		 
					sb.append(" categoria, 	 ").append(" \n");    
					sb.append(" url, 		 ").append(" \n");   		 
					sb.append(" status ").append(" \n");
				 	  
					sb.append(" VALUES ('").append(textField_Nome.getText()).append("' ,");
							 sb.append("'").append(textField_Categoria.getText()).append("' ,");
							 sb.append("'").append(textField_URL.getText()).append("', ");
							 sb.append("").append(chckbxAtivar.isSelected()?1:0).append(")");
							 try
							{
								DBLeao.Insert(sb);
							}
							catch (Exception e1)
							{ 
							}
							 
				}
			});
			
			
			this.okButton.setActionCommand("OK");
			this.getRootPane().setDefaultButton(this.okButton);
		}
		

		this.chckbxAtivar = new JCheckBox("Ativar");
		this.chckbxAtivar.setSelected(true);
		
		this.chckbxAtivar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				chckbxDesativar.setSelected(!chckbxAtivar.isSelected());
			}
		});
		
		  this.chckbxDesativar = new JCheckBox("Desativar");
		  this.chckbxDesativar .addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					chckbxAtivar.setSelected(!chckbxDesativar.isSelected());
				}
			});
		
		this.gl_panel_1 = new GroupLayout(this.panel_1);
		this.gl_panel_1.setHorizontalGroup(
				this.gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(this.gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.chckbxDesativar)
					.addGap(18)
					.addComponent(this.chckbxAtivar)
					.addPreferredGap(ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
					.addComponent(this.okButton)
					.addGap(5)
					.addComponent(this.cancelButton)
					.addContainerGap())
		);
		
		this.gl_panel_1.setVerticalGroup(
				this.gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(this.gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(this.gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(this.gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(this.okButton)
							.addComponent(this.chckbxAtivar)
							.addComponent(this.chckbxDesativar))
						.addComponent(this.cancelButton))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		
		this.panel_1.setLayout(this.gl_panel_1);
		
		this.lblUrl = new JLabel("URL:");
		this.lblCategoria = new JLabel("Categoria:");
		this.lblNome = new JLabel("Nome:");
		this.textField_URL = new JTextField();
		this.textField_URL.setColumns(10);
		
		this.textField_Categoria = new JTextField();
		this.textField_Categoria.setColumns(10);
		
		this.textField_Nome = new JTextField();
		this.textField_Nome.setColumns(10);
		this.gl_panel = new GroupLayout(panel);
		this.gl_panel.setHorizontalGroup(
				this.gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(this.gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(this.gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(this.lblUrl)
						.addComponent(this.lblNome)
						.addComponent(this.lblCategoria))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(this.gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(this.textField_URL, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
						.addComponent(this.textField_Nome, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
						.addComponent(this.textField_Categoria, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		this.gl_panel.setVerticalGroup(
				this.gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(this.gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(this.gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblUrl)
						.addComponent(this.textField_URL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(this.gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblCategoria)
						.addComponent(this.textField_Categoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(this.gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblNome)
						.addComponent(this.textField_Nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(138, Short.MAX_VALUE))
		);
		
		this.panel.setLayout(this.gl_panel);
		this.contentPanel.setLayout(this.gl_contentPanel);
	}
}
