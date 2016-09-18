import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class Treinamento extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel modelo;
	private ArrayList<float[][]> matrizes = new ArrayList<>();
	private JButton btnNewButton;
	private double limiar = 0.1;
	private JLabel lblNewLabel;
	private JTextField textLimiar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Treinamento frame = new Treinamento();
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
	public Treinamento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 474, 211);
		contentPane.add(scrollPane);
		table = new JTable();
		
		Object[] colunas = {
				"Limiar",
				"Agrupamentos",
				"Taxa Acerto"
		};
		modelo = new DefaultTableModel(colunas,0);
		
		/*
		modelo.addColumn("teste");
		modelo.addColumn("Agrupamentos");
		modelo.addColumn("Taxa Acerto");		
		*/
		
    	//modelo.addRow();
    	
		table.setModel(modelo);
		
		scrollPane.setViewportView(table);
		
		JButton btnExecutar = new JButton("Executar");
		btnExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//String texto = textLimiar.getText();
				
				if(textLimiar.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Digite um limiar!" );
				}else if(!textLimiar.getText().matches("([0].[0-9]{2})+") || !textLimiar.getText().matches("([0],[0-9]{2})+")){
					JOptionPane.showMessageDialog(null, "Digite um limiar válido!!!" );
				}else {
					
					if(textLimiar.getText().contains(",")){
						limiar = Double.valueOf(textLimiar.getText().replace(",", "."));
					}else{
						limiar = Double.valueOf(textLimiar.getText());
					}
					
					if(limiar<0.1 || limiar>0.9){
						JOptionPane.showMessageDialog(null, "Digite um limiar válido!" );
					}else{
					
						ClusterRevista myCluster = new ClusterRevista();
											
							try {															
								float[][] matriz = myCluster.realizaTreino(limiar);
								matrizes.add(matriz);
								
								Object[] linha = myCluster.getResultados();
								modelo.addRow(linha);
								table.setModel(modelo);							
								
							} catch (IOException e) {
								JOptionPane.showMessageDialog(null, "Ocorreu algum erro: " + e.getMessage());
								e.printStackTrace();
							}				
					}
				}
					
							
			}
		});
		btnExecutar.setBounds(10, 323, 89, 23);
		contentPane.add(btnExecutar);
		
		btnNewButton = new JButton("Salvar Matriz");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(table.getSelectedRow() < 0){
					JOptionPane.showMessageDialog(null, "Clique em uma linha para salvar!");
				}else{				
					int valor = table.getSelectedRow();
					double limi = (double) (valor+1)/10.0;
					try {
						salvaMatriz(matrizes.get(valor), "./MatrizSoft"+limi+".txt");
						JOptionPane.showMessageDialog(null, "Matriz Salva!");
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Ocorreu algum erro: "+e.getMessage());
						e.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBounds(120, 323, 160, 23);
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("Treinamento da Base");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(149, 11, 195, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Digite o valor do limiar entre  0,10 e 0,90 e clique em executar.\r\n");
		lblNewLabel_1.setToolTipText("0,10 - Duas casas decimais.");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(139, 262, 277, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblSRealizado = new JLabel("S\u00F3 \u00E9 realizado o treino de um limiar de cada vez.");
		lblSRealizado.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblSRealizado.setBounds(10, 287, 298, 14);
		contentPane.add(lblSRealizado);
		
		JLabel lblSalveAMatriz = new JLabel("Salve a matriz para cada limiar.");
		lblSalveAMatriz.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblSalveAMatriz.setBounds(290, 327, 160, 14);
		contentPane.add(lblSalveAMatriz);
		
		textLimiar = new JTextField();
		textLimiar.setText("0,10");
		textLimiar.setToolTipText("0,10 - Duas casas decimais.");
		textLimiar.setBounds(55, 258, 74, 20);
		contentPane.add(textLimiar);
		textLimiar.setColumns(10);
		
		JLabel lblLimiar = new JLabel("Limiar:");
		lblLimiar.setBounds(10, 264, 46, 14);
		contentPane.add(lblLimiar);
	}
	
	
	public static void salvaMatriz(float[][] conjuntoDadosTransformadosSoft, String caminho) throws IOException{
		
		File tabela8 = new File(caminho);
		FileWriter fw8 = new FileWriter(tabela8, true);
		BufferedWriter table8 = new BufferedWriter(fw8);		
		
		for(int i=0;i<conjuntoDadosTransformadosSoft.length; i++){
			for(int j=0; j<conjuntoDadosTransformadosSoft[0].length; j++){
				if(j == conjuntoDadosTransformadosSoft[0].length -1){
					table8.write(conjuntoDadosTransformadosSoft[i][j]+"");
				}else{
					table8.write(conjuntoDadosTransformadosSoft[i][j]+",");
				}
			}
			if(i != conjuntoDadosTransformadosSoft.length-1)
				table8.write("\r\n");
		}
		//table8.newLine();
		table8.close();
	}
}
