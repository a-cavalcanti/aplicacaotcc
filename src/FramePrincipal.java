import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FramePrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePrincipal frame = new FramePrincipal();
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
	public FramePrincipal() {
		setTitle("Rank Abstracts");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnTreinar = new JButton("Treinar");
		btnTreinar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Treinamento frame = new Treinamento ();
				frame.setVisible(true);
		         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
		         frame.setLocationRelativeTo(null);//coloca a janela no meio da tela  ,
			}
		});
		btnTreinar.setBounds(83, 119, 145, 52);
		contentPane.add(btnTreinar);
		
		JButton btnClassificar = new JButton("Classificar");
		btnClassificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecebeAbstract frame1 = new RecebeAbstract();
				frame1.setVisible(true);
		         frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
		         frame1.setLocationRelativeTo(null);//coloca a janela no meio da tela  ,
			}
		});
		btnClassificar.setBounds(238, 119, 140, 52);
		contentPane.add(btnClassificar);
		
		JLabel lblAplicaoParaRanquear = new JLabel("Aplica\u00E7\u00E3o para ranquear revistas cient\u00EDficas com base em um abstract.");
		lblAplicaoParaRanquear.setBounds(10, 22, 414, 14);
		contentPane.add(lblAplicaoParaRanquear);
		
		JLabel lblRealizeOTreinamento = new JLabel("Realize o treinamento e escolha a Matriz de peso que deseja utilizar para classificar.");
		lblRealizeOTreinamento.setBounds(10, 47, 490, 14);
		contentPane.add(lblRealizeOTreinamento);
		
		JLabel lblAndersonPinheiroCavalcanti = new JLabel("Anderson Pinheiro Cavalcanti");
		lblAndersonPinheiroCavalcanti.setHorizontalAlignment(SwingConstants.CENTER);
		lblAndersonPinheiroCavalcanti.setBounds(146, 236, 207, 14);
		contentPane.add(lblAndersonPinheiroCavalcanti);
	}

}
