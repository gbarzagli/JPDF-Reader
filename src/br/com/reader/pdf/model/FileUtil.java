package br.com.reader.pdf.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe utilitaria que manipula arquivo txt para guardar o
 * ultimo arquivo aberto e a ultima pagina a ser visualizada
 * @author Gabriel Barzagli
 */
public class FileUtil {
	
	/** Arquivo que guarda as informacoes da ultima leitura */
	private static File fileStorage;
	static {
		fileStorage = new File("jpdf-storage.txt");
	}
	
	/**
	 * Salva o caminho absoluto ate o arquivo e o numero da pagina
	 * @param file - arquivo PDF
	 * @param page - numero da pagina
	 * @throws IOException caso de algo errado ao criar ou escrever no arquivo
	 */
	protected static void save(File file, int page) throws IOException {
		FileWriter fwriter = null;
		try {
			fwriter = new FileWriter(fileStorage);
			fwriter.write(file.getAbsolutePath() + ";" + page);
			fwriter.flush();
		} finally {
			if (fwriter != null) {
				fwriter.close();
			}
		}
	}
	
	/**
	 * Retorna um bean para recarregar a ultima leitura.
	 * @return bean contendo o arquivo PDF e a pagina onde parou
	 * @throws IOException caso o arquivo nao exista ou nao seja possivel ler
	 */
	public static LastReadInfo reloadLastRead() throws IOException {
		LastReadInfo bean = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(fileStorage);
			br = new BufferedReader(fr);
			
			String line = br.readLine();
			String[] attribute = line.split(";");
			
			bean = new LastReadInfo();
			bean.file = new File(attribute[0]);
			bean.page = Integer.valueOf(attribute[1]);
		} finally {
			if (br != null) {
				br.close();
			}
			if (fr != null) {
				fr.close();
			}
		}
		
		return bean;
	}
	
}
