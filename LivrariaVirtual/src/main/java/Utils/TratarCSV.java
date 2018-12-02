package Utils;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.opencsv.CSVWriter;


public class TratarCSV {
	
	public boolean GerarCSV(ResultSet rs)
	{
		String Arquivo = "/home/aluno/Dev/download/relatorios/relatorio.csv";
		try
		{
			LimparArquivo(Arquivo);
			
			CSVWriter csvWriter = new CSVWriter(new FileWriter(Arquivo), ';');
			csvWriter.writeAll(rs, true);
			csvWriter.close();
			return true;
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	private void LimparArquivo(String file) throws FileNotFoundException
	{
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
	}
	
}
