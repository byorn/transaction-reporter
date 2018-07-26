package transactionsreport.transactionreporter;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import transactionsreport.transactionreporter.dataaccess.DataAccess;
import transactionsreport.transactionreporter.dataaccess.FileData;
import transactionsreport.transactionreporter.dataaccess.FileDataAccessImpl;

import java.util.List;


public class FileDataAccessImplTests {

	@Test
	public void shouldReadLineFromTestFile() {

		DataAccess dataAccess = new FileDataAccessImpl("files/TestInput.txt");

		List<FileData> fileDataList = dataAccess.loadData();

		assertThat(fileDataList.get(0).getClientType()).isEqualToIgnoringCase("CL  ");
		assertThat(fileDataList.get(0).getClientNumber()).isEqualToIgnoringCase("4321");
		assertThat(fileDataList.get(0).getAccountNumber()).isEqualToIgnoringCase("0002");
		assertThat(fileDataList.get(0).getSubAccountNumber()).isEqualToIgnoringCase("0001");
		assertThat(fileDataList.get(0).getExchangeCode()).isEqualToIgnoringCase("SGX ");
		assertThat(fileDataList.get(0).getProductGroupCode()).isEqualToIgnoringCase("FU");
		assertThat(fileDataList.get(0).getSymbol()).isEqualToIgnoringCase("NK    ");
		assertThat(fileDataList.get(0).getExpirationDate()).isEqualToIgnoringCase("20100910");
		assertThat(fileDataList.get(0).getQtyLong()).isEqualToIgnoringCase("0000000001");
		assertThat(fileDataList.get(0).getQtyShort()).isEqualToIgnoringCase("0000000000");
	}

}
