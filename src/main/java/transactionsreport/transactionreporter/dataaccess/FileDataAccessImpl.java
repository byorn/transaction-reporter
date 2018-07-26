package transactionsreport.transactionreporter.dataaccess;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class FileDataAccessImpl implements DataAccess {

    private Logger log = LogManager.getLogger("FileProcessor");

    private List<FileData> fileDataList;

    public FileDataAccessImpl(String inputFile) {
        this.fileDataList = processInputFile(inputFile);
    }

    @Override
    public List<FileData> loadData() {
        return this.fileDataList;
    }


    private List<FileData> processInputFile(String inputFilePath) {

        List<FileData> inputList = new ArrayList<>();
        try {
            File inputF = new File(inputFilePath);
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

            inputList = br.lines().map(mapToItem).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
        }
        return inputList;
    }

    private Function<String, FileData> mapToItem = (line) -> {

        FileData item = null;
        try {
            item = new FileData();
            item.setClientType(line.substring(3, 7));
            item.setClientNumber(line.substring(7, 11));
            item.setAccountNumber(line.substring(11, 15));
            item.setSubAccountNumber(line.substring(15, 19));
            item.setExchangeCode(line.substring(27, 31));
            item.setProductGroupCode(line.substring(25, 27));
            item.setSymbol(line.substring(31, 37));
            item.setExpirationDate(line.substring(37, 45));
            item.setQtyLong(line.substring(52, 62));
            item.setQtyShort(line.substring(63, 73));

        } catch (ArrayIndexOutOfBoundsException a) {
            a.printStackTrace();
            log.error(a);
            log.error("error in line:" + line);
        } catch (Exception a) {
            a.printStackTrace();
            log.error(a);
            log.error("error in line:" + line);
        } finally {
            if (item == null) {

                log.error("Input.txt File format is incorrect, check if have spaces in the end");
                throw new IllegalStateException("Input.txt File format is incorrect, check if have spaces in the end");
            }
        }

        return item;
    };


}
