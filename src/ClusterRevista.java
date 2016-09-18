import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.regex.Pattern;


public class ClusterRevista {
	
	static int quantidadeClasses = 15;
	static double limiar = 0.5;
	static float gama = 0.5f;
	public static ArrayList<HashMap<String, Integer>> setDoc;
	static Object[] resultados;
		
	public static ArrayList<String> caminhoSub(String pasta) {

		ArrayList<String> caminhos = new ArrayList<String>();

		FilenameFilter filtro = new FilenameFilter() { 
			public boolean accept(File dir, String name) {
				return name.endsWith(""); 
			}
		};

		File arquivos = new File(".//"+pasta+"//");
		String[] arquivosSubDiretorio = arquivos.list(filtro);
		
		for(int i = 0 ; i < arquivosSubDiretorio.length; i++){					
				caminhos.add(".//"+pasta+"//"+arquivosSubDiretorio[i]);			
		}	
		
		return caminhos;
	}
	
	private static ArrayList<String> adicionaIndices(String string, int metodo) throws IOException {
		
		FileReader arq = new FileReader(string);
		BufferedReader lerArq = new BufferedReader(arq);
		ArrayList<String> indices = new ArrayList<String>();
		String linha;
		String[] documento;
		
		while ((linha = lerArq.readLine()) != null) {
			
			linha = linha.replace(";", " ").
						replace("%", " ").
						replace("^", " ").
						replace("~", " ").
						replace("/", " ").
						replace("\\", " ").
						replace("|", " ").
						replace("`", " ").
						replace("´", " ").
						replace("@", " ").
						replace(":", " ").
						replace("'", " ").
						replace("-", " ").
						replace("_", " ").
						replace("(", " ").
						replace(")", " ").
						replace(".", " ").
						replace("#", " ").
						replace("<", " ").
						replace(">", " ").
						replace("$", " ").
						replace(",", " ").
						replace("[", " ").
						replace("]", " ").
						replace("{", " ").
						replace("}", " ").
						replace("!", " ").
						replace("?", " ").
						replace("+", " ").
						replace("=", " ").
						replace("*", " ").
						replace("&", " ").
						replace("0", " ").replace("1", " ").replace("2", " ").replace("3", " ").replace("4", " ").replace("5", " ").
						replace("6", " ").replace("7", " ").replace("8", " ").replace("9", " ").
						replaceAll("[\"\']", " ").toLowerCase();
			linha = linha.replaceAll("aaa", " ");linha = linha.replaceAll("bbb", " ");linha = linha.replaceAll("ccc", " ");
			linha = linha.replaceAll("ddd", " ");linha = linha.replaceAll("eee", " ");linha = linha.replaceAll("fff", " ");
			linha = linha.replaceAll("ggg", " ");linha = linha.replaceAll("hhh", " ");linha = linha.replaceAll("iii", " ");
			linha = linha.replaceAll("jjj", " ");linha = linha.replaceAll("lll", " ");linha = linha.replaceAll("mmm", " ");
			linha = linha.replaceAll("nnn", " ");linha = linha.replaceAll("ooo", " ");linha = linha.replaceAll("ppp", " ");
			linha = linha.replaceAll("qqq", " ");linha = linha.replaceAll("rrr", " ");linha = linha.replaceAll("sss", " ");
			linha = linha.replaceAll("ttt", " ");linha = linha.replaceAll("uuu", " ");linha = linha.replaceAll("vvv", " ");
			linha = linha.replaceAll("xxx", " ");linha = linha.replaceAll("zzz", " ");linha = linha.replaceAll("www", " ");
			linha = linha.replaceAll("yyy", " ");
			

	        StringTokenizer frase = new StringTokenizer(linha);
	        
	        while (frase.hasMoreTokens( )) {
	        	
	        	String token = frase.nextToken();
	        	
	        	if(metodo == 1){
		        	if(!indices.contains(token) && token.length()>3){
		        		indices.add(token);	
		        	}
	        	}else{
	        		if(token.length()>3){
	        			indices.add(token);	
	        		}
	        	}	        	
	        	
	        }//while
	        
		}//while
		
		arq.close();
		return indices;		
	}
	
	public static float elevaQuadrado(float x1, float m1, float desvio) {
		float resultado = ((x1 - m1) / desvio) * ((x1 - m1) / desvio);
		return resultado;
	}

	public static float ajustarMedia(int tamanhoCluster, float mediaAtual,
			float padrao) {

		float resultado = (tamanhoCluster * mediaAtual + padrao)
				/ (tamanhoCluster + 1);

		return resultado;
	}

	public static float ajustarDesvio(int tamanhoCluster, float desvioAtual,
		float desvioInicial, float mediaAtual, float padrao) {

		float equacaoA = (float) ((tamanhoCluster - 1)
				* ((desvioAtual - desvioInicial) * (desvioAtual - desvioInicial)) + (tamanhoCluster
				* (mediaAtual * mediaAtual) + (padrao * padrao)))
				/ tamanhoCluster;

		float equacaoB = (float) (((tamanhoCluster + 1) / tamanhoCluster) * ((tamanhoCluster
				* mediaAtual + padrao)
				/ (tamanhoCluster + 1) * (tamanhoCluster * mediaAtual + padrao) / (tamanhoCluster + 1)));

		float resultado = (float) (Math.sqrt(equacaoA - equacaoB) + desvioInicial);
		return resultado;
	}
	
	public static float maior(float[][] a){
	    
		  float max = a[0][0];		  
		  		  
		    for(int i = 0; i < a.length; i++){
		    	for(int j = 0; j < a[i].length; j++){
		    		if(a[i][j] > max){
				        max = a[i][j];				       
				      }
		    	}
		    }
		    return max;
	}
	
	public static float maiorVetor(float[] a){
	    
		  float max = a[0];		  
		  		  
		    for(int i = 0; i < a.length; i++){
		    	
		    		if(a[i] > max){
				        max = a[i];				       
				      }		    	
		    }
		    return max;
	}
	
	public static float maiorPorPosicao(float[][] a, int posicao){
	    
		  float max = 0;		  
		  		  
		    for(int i = 0; i < a.length; i++){		    	
		    		if(a[i][posicao] > max){
				        max = a[i][posicao];				       
				      }
		    	
		    }
		    return max;
	}
	
	
	public static double crossValidation(ArrayList<ArrayList<HashMap<String, Float>>> folds) throws IOException {
		
		double taxa=0;
		Float classe = 0.0f;
		int acertos = 0;		
		Float distancia = 0.0f;
		float minimo = 0.0f;
		
		ArrayList<HashMap<String, Float>> teste;
		ArrayList<ArrayList<HashMap<String, Float>>> treino;
		
		for(int q = 0; q < 10; q++){
		
		teste = new ArrayList<>();
		treino = new ArrayList<>();
		teste = folds.get(q);
		
		for(int i = 0; i < folds.size(); i++){
			if(i!= q){
				treino.add(folds.get(i));
			}
		}		
	
		for (int i = 0; i < teste.size(); i++) {//200
			minimo = Float.MAX_VALUE;
			for (int j = 0; j < treino.size(); j++) {//9				
				for (int k = 0; k < treino.get(j).size(); k++) {//200					
				
						distancia = (float) calcularDistancia(teste.get(i), treino.get(j).get(k));
						
						if (distancia <= minimo && distancia != 0) {						
							minimo = distancia;
							classe = treino.get(j).get(k).get("classe");
						}				
										
				}
			}
			
			minimo = 0;
			
			
			if (teste.get(i).get("classe").equals(classe)) {
				acertos++;
				//acertoPorClasse.put(classe, acertoPorClasse.get(classe)+1.0f);
			}
			
			//treinoTeste.write("Classe = "+String.valueOf(teste.get(i).get("classe")));
			//treinoTeste.write(" Doc = "+String.valueOf(teste.get(i).get("doc")));
			//treinoTeste.write("\r\n");
		}
		
		//System.out.println(acertos);
		//System.out.println((float) acertos/teste.size());
		taxa += (double) acertos/teste.size();
		//System.out.println(taxa);
		
	//	classe = 0.0f;
		acertos = 0;		
		distancia = 0.0f;
		teste = null;
		treino = null;
		
		}	
		
		return taxa/10;
}
		
	public static double calcularDistanciaDoisPontos(float valor1, float valor2) {

		float distancia = 0.0f;
		float temp = 0.0f;		
		
		temp = (float) (temp + Math.pow(Math.abs((valor1 - valor2)), 2));
		
		distancia = (float) Math.sqrt(temp);
		return distancia;
	}
		
		public static float calcularDistancia(HashMap<String, Float> teste, HashMap<String, Float> treino) {

			float distancia = 0.0f;
			float[] vector = new float[teste.size()-2];
			String agrup;
			
			for(int i = 1; i <= teste.size()-2; i++){
				agrup = "g"+i;
				vector[i-1] = (float) ( Math.pow(Math.abs((teste.get(agrup) - treino.get(agrup))), 2));
				agrup = null;
			}			
			
			for(int i = 0; i < vector.length; i++){
				distancia += vector[i];
			}
			
			distancia = (float) Math.sqrt(distancia);
			
			return distancia;
		}
		
		
		private static double getFuncaoSigmoide(double u) {

			return (float) (1 / (1 + Math.pow(Math.E, ((-0.5 * (u - 10)) ))));

		}
		
		public static char ultimoChar(String s) {
			char r = (char) 0;
			for (char c : s.toCharArray()) {
				r = c;
			}
			return r;
		}
		
		
private static void salvaArquivo(float[][] conjuntoDadosTransformados, String nome) throws IOException{
			
			
			File tabela8 = new File("C:\\Users\\Anderson\\Resultados\\"+nome+".arff");
			FileWriter fw8 = new FileWriter(tabela8, true);
			BufferedWriter table8 = new BufferedWriter(fw8);
			
			table8.write("@RELATION "+nome);
			table8.write("\r\n");
			table8.write("\r\n");
			
			for(int j = 0; j < conjuntoDadosTransformados[0].length; j++){//nº agrupamentos
				int var = j+1;			
				table8.write("@ATTRIBUTE "+String.valueOf("g"+var)+ " REAL");
				table8.write("\r\n");
			}		
			
			table8.write("\r\n");
			table8.write("\r\n");
			table8.write("@ATTRIBUTE class 	{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14}");
			table8.write("\r\n");
			table8.write("\r\n");
			table8.write("@DATA");
			table8.write("\r\n");
			table8.write("\r\n");
			for(int i = 0; i < conjuntoDadosTransformados.length; i++){	//nº doc = 19997						
				for(int j = 0; j < conjuntoDadosTransformados[0].length; j++){//nº agrupamentos								
					table8.write(String.valueOf(conjuntoDadosTransformados[i][j])+", ");				
				}
				table8.write(String.valueOf(setDoc.get(i).get("classe")));
				table8.write("\r\n");
			}		
			
			table8.newLine();			 
			table8.close();		
			
		}

	public static ArrayList<String> lerIndices(String caminho) throws IOException{
		
		ArrayList<String> indices = new ArrayList<String>();
		FileReader arq = new FileReader(caminho);
		BufferedReader lerArq = new BufferedReader(arq);

		String linha;
		String[] documento;

		while ((linha = lerArq.readLine()) != null) {

			documento = linha.split(Pattern.quote(","));

			for (int j = 0; j < documento.length; j++) {
				if (!indices.contains(documento[j])) {
					indices.add(documento[j]);
				}
			}
		}
		arq.close();		
		return indices;		
	}
	
	public static void preencheTabela(ArrayList<String> indices, String caminho) throws IOException{
		
		setDoc = new ArrayList<HashMap<String, Integer>>();
		FileReader arq = new FileReader(caminho);
		BufferedReader lerArq = new BufferedReader(arq);

		String linha1;
		int cont = 0;

		while ((linha1 = lerArq.readLine()) != null) {
			HashMap<String, Integer> documentos = new HashMap<String, Integer>(); //estrutura de dado que guarda a quantidade em que cada palavra apareceu no documento
																				
			String[] documento1;
			documento1 = linha1.split(Pattern.quote(","));
			cont++;
			documentos.put("classe", Integer.valueOf(documento1[0]));
			
			for (int j = 1; j < documento1.length; j++) {//29420				
				documentos.put(indices.get(j-1), Integer.valueOf(documento1[j]));
			}
			setDoc.add(documentos); 			
		}

		arq.close();
		
	}
	
	
	
	public static ArrayList<ArrayList<Float>> calculaPadroes(ArrayList<String> indices){
		
		ArrayList<ArrayList<Float>> padraoPalavras = new ArrayList<ArrayList<Float>>();
		ArrayList<Float> padroes;	
		
		float freq=0, freqTotal=0;		
			
			for(int i = 0; i < setDoc.get(0).size()-1; i++){			
				padroes = new ArrayList<Float>();
				
				for(int k=0; k <quantidadeClasses; k++){
					
					for(int j = 0; j < setDoc.size(); j++){ 			
					
						if(setDoc.get(j).get("classe") == k){
							freq = freq + setDoc.get(j).get(indices.get(i));
						}
						
						freqTotal = freqTotal + setDoc.get(j).get(indices.get(i));
					}					
					
					if(freq == 0 || freqTotal ==0){
						padroes.add(0.0f);
					}
					else{
						padroes.add(freq/freqTotal);
					}
					freq = 0;
					freqTotal = 0;
				}
			padraoPalavras.add(padroes);
			padroes = null;
		}
			
		return padraoPalavras;

	}
	
	public static ArrayList<HashMap<String, ArrayList<Float>>> realizaAgrupamentos(ArrayList<ArrayList<Float>> padraoPalavras){
		
		float padraoInicial = (float) 0.6;
		int quantidadeClusters = 0;
		
		ArrayList<HashMap<String, ArrayList<Float>>> agrupamentos = new ArrayList<HashMap<String, ArrayList<Float>>>();
		HashMap<Integer, Integer> selecao = new HashMap<>();
		
		//cluster Inicial
		HashMap<String, ArrayList<Float>> clusterIncial;
		ArrayList<Float> media;
		ArrayList<Float> desvioPadrao;
		ArrayList<Float> padrao;
		
		//novo cluster
		HashMap<String, ArrayList<Float>> clusterAux;
		
		for (int i = 0; i < padraoPalavras.size(); i++) {

			if (quantidadeClusters == 0) {
				
				// cria cluster
				
				clusterIncial = new HashMap<String, ArrayList<Float>>();
				media = new ArrayList<Float>();
				desvioPadrao = new ArrayList<Float>();
				padrao = new ArrayList<Float>();
				
				media = padraoPalavras.get(0);
				System.out.println("mediaInicial = "+media);
				
				for (int j = 0; j < quantidadeClasses; j++) {
					desvioPadrao.add(padraoInicial);
				}

				padrao.add((float) i);
				
				selecao.put(quantidadeClusters, i);
				
				clusterIncial.put("media", media);
				clusterIncial.put("desvio", desvioPadrao);
				clusterIncial.put("padrao", padrao);
				agrupamentos.add(clusterIncial);

				quantidadeClusters++;
				
				media = null;
				desvioPadrao = null;
				padrao = null;
				clusterIncial = null;

			} else {
				// calcular similaridade com clusters existentes
				
				double valorExponencial = 0;
				int indiceAgrupamento = 0;
				double somatorio = 0;

				for (int k = 0; k < agrupamentos.size(); k++) { // for para testar todos agrupamentos com determinado padrao existente

					for (int l = 1; l < agrupamentos.get(0).get("media").size(); l++) {						
						
						somatorio += 
								(elevaQuadrado(padraoPalavras.get(i).get(l), 
								agrupamentos.get(k).get("media").get(l), 
								agrupamentos.get(k).get("desvio").get(l)) * -1)	;	

					}
					
					valorExponencial = Math.exp(somatorio);
					somatorio = 0;
				
					if (valorExponencial > limiar) { // quebra o laço se o valorExponencial for maior que o limiar
						indiceAgrupamento = k;
						break;
					}
				}

				if (valorExponencial < limiar) {
					// novo cluster	
					
					media = new ArrayList<Float>();
					desvioPadrao = new ArrayList<Float>();
					padrao = new ArrayList<Float>();
					clusterAux = new HashMap<String, ArrayList<Float>>();

					media = padraoPalavras.get(i);
					
					selecao.put(quantidadeClusters, i);
					
					for (int j = 0; j < quantidadeClasses; j++) {
						desvioPadrao.add(padraoInicial);
					}

					padrao.add((float) i);

					clusterAux.put("media", media);
					clusterAux.put("desvio", desvioPadrao);
					clusterAux.put("padrao", padrao);
					agrupamentos.add(clusterAux);

					quantidadeClusters++;
					
					clusterAux = null;

				} else {
					// reajustar a media e o desvio padrao do agrupamento
					
					 // OBS: segundo o autor do artigo o desvio padrao deve
					 // ser ajustado primeiro, pois ele deve usar a media
					 // antiga para fazer o ajuste.
					 

					agrupamentos.get(indiceAgrupamento).get("padrao")
							.add((float) i); // adicionando o indice do
												// padrao ao vetor de
												// agrupamentos

					// Ajuste do desvio padrao
					for (int m = 0; m < agrupamentos.get(0).get("desvio").size(); m++) { // for para ajustar as medias
						agrupamentos.get(indiceAgrupamento).get("desvio").set(
												m, 
												ajustarDesvio(agrupamentos.get(indiceAgrupamento).get("padrao").size(),
												agrupamentos.get(indiceAgrupamento).get("desvio").get(m),
												padraoInicial,			
												agrupamentos.get(indiceAgrupamento).get("media").get(m),
												padraoPalavras.get(i).get(m)));
					}

					// Ajuste da media
					for (int m = 0; m < agrupamentos.get(0).get("media").size(); m++) { // for para ajustar as medias
						
						agrupamentos.get(indiceAgrupamento).get("media").set(m,
										ajustarMedia(agrupamentos.get(indiceAgrupamento).get("padrao").size(),
													agrupamentos.get(indiceAgrupamento).get("media").get(m),
													padraoPalavras.get(i).get(m)
												));
					}// Todas as médias de acordo com o exemplo do Artigo
					
				}// novo cluster

			}// else

		}// for
		
		
		return agrupamentos;
		
	}
	
	public static float[][] calculaMatrizPeso(ArrayList<HashMap<String, ArrayList<Float>>> agrupamentos, ArrayList<ArrayList<Float>> padraoPalavras){
		
		float[][] matrizSimilaridade = new float[agrupamentos.size()][padraoPalavras.size()];
		
		double somatorio = 0;
		double valorExponencial =0;
		
		for (int i = 0; i < agrupamentos.size(); i++) {
			for (int j = 0; j < padraoPalavras.size(); j++) {
				for (int k = 0; k < padraoPalavras.get(j).size(); k++) {
					
					somatorio +=  
							(elevaQuadrado(padraoPalavras.get(j).get(k), 
							agrupamentos.get(i).get("media").get(k), 
							agrupamentos.get(i).get("desvio").get(k)) * -1);					
					
				}
				valorExponencial = Math.exp(somatorio);				
				matrizSimilaridade[i][j] = (float) valorExponencial;
				
				somatorio = 0;
				valorExponencial = 0;
			}
		}
		
		
		float[][] matrizPesoSoft = new float[padraoPalavras.size()][agrupamentos.size()];
        for(int i = 0; i < agrupamentos.size(); i++){
        	for(int j = 0; j < padraoPalavras.size(); j++){
        			matrizPesoSoft[j][i] = matrizSimilaridade[i][j];
        			
        	}
        	
        }
        
        return matrizPesoSoft;
	}
	
	public static float[][] calculaMatrizDadosTransformados(float[][] matrizPesoSoft, ArrayList<String> indices){
		
		float[][] conjuntoDadosTransformadosSoft = new float[setDoc.size()][matrizPesoSoft[0].length];
		float resultado = 0;
		
		for(int i = 0; i < setDoc.size(); i++){ //para analisar todos os documentos um por vez
			for(int colunas = 0; colunas < matrizPesoSoft[0].length; colunas++){//para multiplicar cada linha da matriz de peso
				for(int linhas = 0; linhas < matrizPesoSoft.length; linhas++){//para multiplicar cada coluna da matriz de peso				
					resultado = resultado + Float.valueOf(setDoc.get(i).get(indices.get(linhas)))*matrizPesoSoft[linhas][colunas];
				}			
				conjuntoDadosTransformadosSoft[i][colunas] = resultado;
				resultado = 0;			
			}
			
		}
		
		return conjuntoDadosTransformadosSoft;
		
	}
	
	public static double realizaClassificacao(float[][] conjuntoDadosTransformadosSoft) throws IOException{
		
		double taxaAcerto=0;
		
		ArrayList<HashMap<String, Float>> matrizSoft = new ArrayList<>();
		HashMap<String, Float> indicesMatriz;		

		for(int i = 0; i < conjuntoDadosTransformadosSoft.length; i++){	//nº doc = 19997
			
			indicesMatriz = new HashMap<>();
			int var = 0;
			indicesMatriz.put("doc",Float.valueOf(i));
			indicesMatriz.put("classe", Float.valueOf(setDoc.get(i).get("classe")));
			
			for(int j = 0; j < conjuntoDadosTransformadosSoft[0].length; j++){//nº agrupamentos
				var = j+1;
				indicesMatriz.put("g"+var, conjuntoDadosTransformadosSoft[i][j]);									
			}		
			
			matrizSoft.add(indicesMatriz);
			indicesMatriz = null;
							
		}
		
		ArrayList<ArrayList<HashMap<String, Float>>> folds = new ArrayList<>();

		ArrayList<HashMap<String, Float>> fold0 = new ArrayList<>();
		ArrayList<HashMap<String, Float>> fold1 = new ArrayList<>();
		ArrayList<HashMap<String, Float>> fold2 = new ArrayList<>();
		ArrayList<HashMap<String, Float>> fold3 = new ArrayList<>();
		ArrayList<HashMap<String, Float>> fold4 = new ArrayList<>();
		ArrayList<HashMap<String, Float>> fold5 = new ArrayList<>();
		ArrayList<HashMap<String, Float>> fold6 = new ArrayList<>();
		ArrayList<HashMap<String, Float>> fold7 = new ArrayList<>();
		ArrayList<HashMap<String, Float>> fold8 = new ArrayList<>();
		ArrayList<HashMap<String, Float>> fold9 = new ArrayList<>();

		for (int j = 0; j < 1500; j++) {

			if (ultimoChar(String.valueOf(j)) == '0') {
				fold0.add(matrizSoft.get(j));
			} else if (ultimoChar(String.valueOf(j)) == '1') {
				fold1.add(matrizSoft.get(j));
			} else if (ultimoChar(String.valueOf(j)) == '2') {
				fold2.add(matrizSoft.get(j));
			} else if (ultimoChar(String.valueOf(j)) == '3') {
				fold3.add(matrizSoft.get(j));
			} else if (ultimoChar(String.valueOf(j)) == '4') {
				fold4.add(matrizSoft.get(j));
			} else if (ultimoChar(String.valueOf(j)) == '5') {
				fold5.add(matrizSoft.get(j));
			} else if (ultimoChar(String.valueOf(j)) == '6') {
				fold6.add(matrizSoft.get(j));
			} else if (ultimoChar(String.valueOf(j)) == '7') {
				fold7.add(matrizSoft.get(j));
			} else if (ultimoChar(String.valueOf(j)) == '8') {
				fold8.add(matrizSoft.get(j));
			} else if (ultimoChar(String.valueOf(j)) == '9') {
				fold9.add(matrizSoft.get(j));
			}

		}

		folds.add(fold0);
		folds.add(fold1);
		folds.add(fold2);
		folds.add(fold3);
		folds.add(fold4);
		folds.add(fold5);
		folds.add(fold6);
		folds.add(fold7);
		folds.add(fold8);
		folds.add(fold9);
		
		taxaAcerto = crossValidation(folds);
		return taxaAcerto;
	}
	
	public float[][] realizaTreino(double limiar) throws IOException {
			
		Long agora = System.currentTimeMillis();
		this.limiar = limiar;
		ArrayList<String> caminho = caminhoSub("revistasIndices");			
		ArrayList<String> base = caminhoSub("revistaBase");
		
		ArrayList<String> indices = new ArrayList<String>();			
		indices = lerIndices(caminho.get(0));
		
		preencheTabela(indices, base.get(0));
		
		System.out.println(indices);		
		System.out.println(setDoc.size());			
		
		ArrayList<ArrayList<Float>> padraoPalavras = new ArrayList<ArrayList<Float>>();			
		padraoPalavras = calculaPadroes(indices);
		
		ArrayList<HashMap<String, ArrayList<Float>>> agrupamentos = new ArrayList<HashMap<String, ArrayList<Float>>>();			
		agrupamentos = realizaAgrupamentos(padraoPalavras);
		
		System.out.println("AGRUPAMENTOS = "+agrupamentos.size());
		System.out.println((System.currentTimeMillis()-agora)+"ms");
		
		
		//T
		float[][] matrizPesoSoft = calculaMatrizPeso(agrupamentos, padraoPalavras);	    
	    //salvaMatriz(matrizPesoSoft, "./MatrizPesoSoft"+limiar+".txt");
	    
		//D's
		float[][] conjuntoDadosTransformadosSoft = calculaMatrizDadosTransformados(matrizPesoSoft, indices);		
		//matrizPesoSoft = null;
		
				
		double taxaAcerto = realizaClassificacao(conjuntoDadosTransformadosSoft);
		System.out.println("Taxa Acerto = "+taxaAcerto);
		
		Object[] resultadosLimiar = new Object[]{
				String.valueOf(limiar),				
				String.valueOf(agrupamentos.size()),
				String.valueOf(taxaAcerto).substring(0, 4)
		};
		setResultados(resultadosLimiar);
		
		//salvaArquivo(conjuntoDadosTransformadosSoft, "softRevista"+String.valueOf(limiar));	 
	
		//System.out.println((System.currentTimeMillis()-agora)+"ms");	
	
		//setDoc=null;	
		
		return matrizPesoSoft;
	}
	
	public static void setResultados(Object[] results){
		resultados = results;
	}
	
	public static Object[] getResultados(){
		return resultados;
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
			table8.write("\r\n");
		}
		table8.newLine();
		table8.close();
	}
	
	
}
