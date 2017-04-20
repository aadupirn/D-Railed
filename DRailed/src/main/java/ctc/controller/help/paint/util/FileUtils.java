package ctc.controller.help.paint.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class FileUtils {
	public static List<String> readTxtFile(String filePath){
		 List<String> contentStrs = new LinkedList<String>();
        try {
                String encoding="GBK";
                
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //�ж��ļ��Ƿ����
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//���ǵ������ʽ
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                    	contentStrs.add(lineTxt);
                    }
                    read.close();
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
     return contentStrs;
    }
}
