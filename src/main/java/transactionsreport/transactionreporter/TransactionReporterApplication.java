package transactionsreport.transactionreporter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class TransactionReporterApplication {



	public static void main(String[] args) {
		SpringApplication.run(TransactionReporterApplication.class, args);
	}

	@Component
	class FileProcessor implements CommandLineRunner {
		private Logger log = LogManager.getLogger("FileProcessor");
		@Override
		public void run(String... args) throws Exception {
			log.info("starting");
		}
	}
}
