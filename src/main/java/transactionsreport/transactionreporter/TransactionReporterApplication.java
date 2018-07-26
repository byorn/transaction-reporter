package transactionsreport.transactionreporter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import transactionsreport.transactionreporter.csvcreator.CSVData;
import transactionsreport.transactionreporter.csvcreator.FileDataWriterImpl;
import transactionsreport.transactionreporter.csvcreator.WriteData;
import transactionsreport.transactionreporter.dataaccess.DataAccess;
import transactionsreport.transactionreporter.dataaccess.FileData;
import transactionsreport.transactionreporter.dataaccess.FileDataAccessImpl;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TransactionReporterApplication {

	private static final String INPUT_FILE="files/Input.txt";
	private static final String OUTPUT_FILE="files/output.csv";

	public static void main(String[] args) {
		SpringApplication.run(TransactionReporterApplication.class, args);
	}

	@Component
	class FileProcessor implements CommandLineRunner {
		private Logger log = LogManager.getLogger("FileProcessor");


		@Override
		public void run(String... args) throws Exception {
			log.info("starting");
			DataAccess dataAccess = new FileDataAccessImpl(INPUT_FILE);
			List<FileData> fileDataList = dataAccess.loadData();
			List<CSVData> csvDataList = new ArrayList<>();
			fileDataList.forEach(fileData -> {
				CSVData csvData = new CSVData();
				csvData.setClientInformation(fileData.getClientType() + " " + fileData.getClientNumber() + " " + fileData.getAccountNumber() + " " + fileData.getSubAccountNumber());
				csvData.setProductInformation(fileData.getExchangeCode() + " " + fileData.getProductGroupCode() + " " + fileData.getSymbol() + " " + fileData.getExpirationDate());
				csvData.setTotalTransactionAmount(String.valueOf(Integer.valueOf(fileData.getQtyLong())-Integer.valueOf(fileData.getQtyShort())));
				csvDataList.add(csvData);
			});
			log.info("finished processing data");

			WriteData writeData =  new FileDataWriterImpl(OUTPUT_FILE);
			writeData.writeToCSV(csvDataList);
			log.info("finished creating csv file");
		}
	}
}
