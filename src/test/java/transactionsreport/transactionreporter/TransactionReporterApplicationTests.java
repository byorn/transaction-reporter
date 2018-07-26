package transactionsreport.transactionreporter;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import transactionsreport.transactionreporter.dataaccess.DataAccess;
import transactionsreport.transactionreporter.dataaccess.FileData;
import transactionsreport.transactionreporter.dataaccess.FileDataAccessImpl;

import java.util.List;


public class TransactionReporterApplicationTests {

	@Test
	public void shouldReadClientTypeFromFile() {

		DataAccess dataAccess = new FileDataAccessImpl("files/TestInput.txt");

		List<FileData> fileDataList = dataAccess.loadData();

		assertThat(fileDataList.get(0).getClientType()).isEqualToIgnoringCase("CL");

	}

}
