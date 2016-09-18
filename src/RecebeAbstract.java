import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class RecebeAbstract extends JFrame {

	private JPanel contentPane;
	private File file;
	private String arquivo;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecebeAbstract frame = new RecebeAbstract();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	

	/**
	 * Create the frame.
	 */
	public RecebeAbstract() {
		setTitle("Journal Finder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JTextArea txtrColeAquiO = new JTextArea();
		txtrColeAquiO.setToolTipText("Cole aqui o seu Abstract");
		txtrColeAquiO.setTabSize(10);
		txtrColeAquiO.setLineWrap(true);
		txtrColeAquiO.setRows(10);
		txtrColeAquiO.setBounds(10, 24, 414, 184);
		contentPane.add(txtrColeAquiO);
		
		JButton btnExecutar = new JButton("Executar");
		btnExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtrColeAquiO.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Campo Abstract vazio!");
				}else if(arquivo==null){
					JOptionPane.showMessageDialog(null, "Selecione o arquivo!");
				}else{
				
				String textAbstract = txtrColeAquiO.getText();
				VerifyAbstract verificarAbstract = new VerifyAbstract(0.5);
				
						try {
							ArrayList<Integer> results = new ArrayList<>();
							results = verificarAbstract.verificaAbstract(textAbstract, arquivo);
							
							TelaRevistas frame = new TelaRevistas();
							ArrayList<String> nomes = frame.adicionaNomes(results);
							frame.setLabels(nomes);
							frame.setVisible(true);
							frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
					        frame.setLocationRelativeTo(null);//coloca a janela no meio da tela
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}//else	
				
			}
		});
		btnExecutar.setBounds(335, 219, 89, 23);
		contentPane.add(btnExecutar);
		
		
		
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtrColeAquiO.setText("");
			}
		});
		btnLimpar.setBounds(236, 219, 89, 23);
		contentPane.add(btnLimpar);
		
		JButton btnAbrirArquivo = new JButton("Abrir Arquivo");
		btnAbrirArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Selecione uma Matriz.");
		        fileChooser.setCurrentDirectory(new File("./"));
		        
				int retorno = fileChooser.showOpenDialog(null);
				
				if (retorno == JFileChooser.APPROVE_OPTION) {
					  file = fileChooser.getSelectedFile();
					  arquivo = String.valueOf(file);
					  System.out.println(arquivo);
					  arquivo = arquivo.replaceAll("[\\\\]" , "\\\\"+"\\\\");
					  System.out.println(arquivo);
					  
					} else{					  
					}
				
				
			}
		});
		btnAbrirArquivo.setBounds(101, 219, 125, 23);
		contentPane.add(btnAbrirArquivo);
	}
}
