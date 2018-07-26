package transactionsreport.transactionreporter.dataaccess;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class FileDataAccessImpl implements DataAccess{

    private List<FileData> fileDataList;

    public FileDataAccessImpl(String inputFile){
       this.fileDataList = processInputFile(inputFile);
    }

    @Override
    public List<FileData> loadData() {
        return this.fileDataList;
    }



    private List<FileData> processInputFile(String inputFilePath) {

        List<FileData> inputList = new ArrayList<>();
        try{
            File inputF = new File(inputFilePath);
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            // skip the header of the csv
            inputList = br.lines().map(mapToItem).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputList ;
    }

    private Function<String, FileData> mapToItem = (line) -> {

        FileData item = null;
        try{
          item = new FileData();
          item.setClientType(line.substring(3,6).trim());
        }catch (ArrayIndexOutOfBoundsException a){
            a.printStackTrace();
            System.out.println("error in line:" + line);
        }catch (Exception a){
            a.printStackTrace();
            System.out.println("error in line:" + line);
        }finally{
            if(item == null){
                System.out.println("CSV File format is incorrect, check if have spaces in the end");
                throw new IllegalStateException("CSV File format is incorrect");
            }
        }

        return item;
    };


}
