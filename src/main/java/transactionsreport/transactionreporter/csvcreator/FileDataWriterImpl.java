package transactionsreport.transactionreporter.csvcreator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileDataWriterImpl implements WriteData {
    FileWriter fileWriter = null;

    private FileDataWriterImpl() {
    }

    public FileDataWriterImpl(String outputFile) throws IOException {
        fileWriter = new FileWriter(outputFile);
    }

    public void writeToCSV(List<CSVData> csvData) {

        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("Client_Information,Product_Information,Total_Transaction_Amount");
        csvData.forEach(data -> {
            printWriter.println(data.getClientInformation() + "," + data.getProductInformation() + "," + data.getTotalTransactionAmount());
        });
        printWriter.close();

    }


}
