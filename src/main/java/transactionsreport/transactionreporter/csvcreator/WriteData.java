package transactionsreport.transactionreporter.csvcreator;


import transactionsreport.transactionreporter.dataaccess.FileData;

import java.io.IOException;
import java.util.List;

public interface WriteData {
    void writeToCSV(List<CSVData> csvData) throws IOException;


}
