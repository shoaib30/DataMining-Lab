import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Application extends JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2517616123689799182L;
	JButton btnViewData;
	JButton btnGenerateRules;
	JButton btnSelectFile;
	JButton btnBack;
	JTextField tfFileName;
	JPanel pnlInputFile;
	JPanel pnlbtns;
	JLabel lblFileStatus;
	JLabel lblMinSupport, lblMinConfidence;
	JButton btnGenerate;
	JTextField jfSupport, jfConfidence;
	String fileName;
	int minSupport, minConf;
	void setElemets(){
		btnViewData = new JButton("View Data");
		btnGenerateRules = new JButton("Generate Rules");
		btnSelectFile = new JButton("Select File");
		btnBack = new JButton("Back");
		tfFileName = new JTextField(20);
		pnlInputFile = new JPanel();
		pnlbtns = new JPanel();
		lblFileStatus = new JLabel("Select File");
		lblMinSupport = new JLabel("Enter Minimum Support: ");
		lblMinConfidence = new JLabel("Enter Minimum Confidence: ");
		btnGenerate = new JButton("Generate");
		jfSupport = new JTextField(15);
		jfConfidence = new JTextField(15);
		pnlInputFile.add(lblFileStatus);
		pnlInputFile.add(tfFileName);
		pnlInputFile.add(btnSelectFile);
		pnlbtns.setLayout(new GridLayout(0,1));
		pnlbtns.add(btnViewData);
		pnlbtns.add(btnGenerateRules);
		btnViewData.setEnabled(false);
		btnGenerateRules.setEnabled(false);
		this.setLayout(new GridLayout(0,1));
		this.add(pnlInputFile);
	}
	void setActions(JFrame jfk, JPanel main)	{
		btnSelectFile.addActionListener(new ActionListener()	{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fileName = tfFileName.getText();
				System.out.println("Filename: " + fileName);
				if(fileName != null && !fileName.isEmpty()){
					File f = new File(fileName);
					if(f.exists() && !f.isDirectory()) { 
					    btnSelectFile.setEnabled(false);
					    btnViewData.setEnabled(true);
					    btnGenerateRules.setEnabled(true);
					    removeAll();
					    add(pnlbtns);
					    revalidate();
					    repaint();
					}else{
						lblFileStatus.setText("File Does Not Exist");
					}
				}
			}
		});
		btnViewData.addActionListener(new ActionListener() 	{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				removeAll();
				jfk.setSize(400,500);
				DefaultTableModel model = new DefaultTableModel();
				JTable jtData = new JTable(model);
				String headers[] = new String[]	{"Data1" , "Data2"};
				model.setColumnIdentifiers(headers);
				model.addRow(new String[]{"1","0"});
				model.addRow(new String[]{"1","0"});
				model.addRow(new String[]{"1","0"});
				model.addRow(new String[]{"1","0"});
				model.addRow(new String[]{"1","0"});
				jtData.setPreferredScrollableViewportSize(jtData.getPreferredSize());
				setLayout(new BorderLayout());
				add(new JScrollPane(jtData));
				add(btnBack, BorderLayout.PAGE_END);
				revalidate();
				repaint();
			}
			
		});
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				removeAll();
				setLayout(new GridLayout(0,1));
				jfk.setSize(400,200);
				add(pnlbtns);
				revalidate();
				repaint();
			}
			
		});
		btnGenerateRules.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				removeAll();
				setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
				JPanel pnlConf = new JPanel();
				pnlConf.add(lblMinConfidence);
				pnlConf.add(jfConfidence);
				JPanel pnlSupport = new JPanel();
				pnlSupport.add(lblMinSupport);
				pnlSupport.add(jfSupport);
				add(pnlSupport);
				add(pnlConf);
				add(btnGenerate);
				add(btnBack);
				revalidate();
				repaint();
			}
			
		});
		btnGenerate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(jfSupport.getText() != null && jfConfidence.getText() != null && !jfSupport.getText().isEmpty() && !jfConfidence.getText().isEmpty())	{
					removeAll();
					minSupport = Integer.parseInt(jfSupport.getText());
					minConf = Integer.parseInt(jfConfidence.getText());
					add(new JLabel("Minimum Support: " + minSupport));
					add(new JLabel("Minimum Confidence: " + minConf));
					add(new JTextArea("a->b\nb->c"));
					add(btnBack);
					revalidate();
					repaint();
				}
				
			}
			
		});
	}
	Application(JFrame jfk)	{
		setElemets();
		setActions(jfk, this);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame jfk = new JFrame("Apriori Application");
		jfk.setContentPane(new Application( jfk));
		jfk.setSize(400,200);
		jfk.setVisible(true);
	}

}
