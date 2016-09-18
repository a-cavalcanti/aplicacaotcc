import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TelaRevistas extends JFrame {
	
	private JLabel revista1 = new JLabel("");
	private JLabel revista2 = new JLabel("");
	private JLabel revista3 = new JLabel("");
	private JLabel revista4 = new JLabel("");
	private JLabel revista5 = new JLabel("");
	private JLabel revista6 = new JLabel("");
	private JLabel revista7 = new JLabel("");
	private JLabel revista8 = new JLabel("");
	private JLabel revista9 = new JLabel("");
	private JLabel revista10 = new JLabel("");
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRevistas frame = new TelaRevistas();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public  ArrayList<String> lerRevistas(String caminho) throws IOException{
		
		ArrayList<String> revistas = new ArrayList<String>();
		FileReader arq = new FileReader(caminho);
		BufferedReader lerArq = new BufferedReader(arq);

		String linha;
		String[] documento;

		while ((linha = lerArq.readLine()) != null) {

			documento = linha.split(Pattern.quote(";"));
			revistas.add(documento[0]);			
		}
		arq.close();		
		return revistas;		
	}
	
	public ArrayList<String> adicionaNomes(ArrayList<Integer> results) throws IOException{
		ArrayList<String> revistas = lerRevistas("C:\\Users\\Anderson\\nomesRevistas.txt");
		ArrayList<String> nomes = new ArrayList<>();
		
		for(int i=0; i<results.size();i++){
			nomes.add(revistas.get(results.get(i)));
		}
		
		return nomes;
	}
	
	public void setLabels(ArrayList<String> nomes){
		
		revista1.setText(nomes.get(0));
		revista2.setText(nomes.get(1));
		revista3.setText(nomes.get(2));
		revista4.setText(nomes.get(3));
		revista5.setText(nomes.get(4));
		revista6.setText(nomes.get(5));
		revista7.setText(nomes.get(6));
		revista8.setText(nomes.get(7));
		revista9.setText(nomes.get(8));
		revista10.setText(nomes.get(9));
		revista10.setText(nomes.get(10));		
	}

	/**
	 * Create the frame.
	 */
	public TelaRevistas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("1 -");
		label.setBounds(20, 34, 46, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("2 -");
		label_1.setBounds(20, 72, 46, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("3 -");
		label_2.setBounds(20, 110, 46, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("4 -");
		label_3.setBounds(20, 153, 46, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("5 -");
		label_4.setBounds(20, 192, 46, 14);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("6 -");
		label_5.setBounds(20, 235, 46, 14);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("7 -");
		label_6.setBounds(20, 275, 46, 14);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("8 -");
		label_7.setBounds(20, 313, 46, 14);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("9 -");
		label_8.setBounds(20, 349, 46, 14);
		contentPane.add(label_8);
		
		JLabel label_9 = new JLabel("10 -");
		label_9.setBounds(20, 388, 46, 14);
		contentPane.add(label_9);
		
		
		revista1.setBounds(47, 34, 377, 27);
		contentPane.add(revista1);
		
		
		revista2.setBounds(46, 72, 378, 27);
		contentPane.add(revista2);
		
		
		revista3.setBounds(47, 110, 377, 27);
		contentPane.add(revista3);
		
		
		revista4.setBounds(47, 153, 377, 28);
		contentPane.add(revista4);
		
		
		revista5.setBounds(47, 192, 377, 27);
		contentPane.add(revista5);
		
		
		revista6.setBounds(47, 235, 377, 29);
		contentPane.add(revista6);
		
		
		revista7.setBounds(47, 275, 377, 27);
		contentPane.add(revista7);
		
		
		revista8.setBounds(47, 313, 377, 25);
		contentPane.add(revista8);
		
		
		revista9.setBounds(47, 349, 377, 28);
		contentPane.add(revista9);
		
		
		revista10.setBounds(57, 388, 367, 27);
		contentPane.add(revista10);
		
		JLabel lblRelevnciaRevistas = new JLabel("Relev\u00E2ncia - Revistas");
		lblRelevnciaRevistas.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelevnciaRevistas.setBounds(152, 11, 148, 14);
		contentPane.add(lblRelevnciaRevistas);
	}
}
