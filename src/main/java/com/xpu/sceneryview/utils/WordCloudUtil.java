package com.xpu.sceneryview.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @description
 * @Author lubb
 * @create 2024-04-02 11:07
 */
@Component
public class WordCloudUtil {

    /**
     *
     * @param scripts
     * @param content
     * @param server
     * @param stopwordsPath
     * @param wordcloudPath
     * @return
     */
    public static int generateWordCloud(String scripts,String content,boolean server,String stopwordsPath,String wordcloudPath,String fontPath){
        /*
        * /*
			附加：
			String[] args1=new String[]{"/home/huan/anaconda2/bin/python","/home/huan/myfile/pythonfile/helloword.py"};
            Process pr=Runtime.getRuntime().exec(args1);
			String数组里的那一行很重要
			首先一定要设置好你所使用的python的位置，切记不要直接使用python，因为系统会默认使用自带的python，所以一定要设置好你所使用的python的位置，否则可能会出现意想不到的问题（比如说我使用的是anaconda中的python，而ubuntu系统会默认调用自带的python，而我自带的python中并没有numpy库，所以会造成相应的代码不会执行的问题，所以设置好python的位置是很重要的）。还有就是要设置好py文件的位置，使用绝对路径。在这里插入代码片

       还有就是可以看出，此方法可以满足我们python代码中调用第三方库的情况，简单实用。
			*/

        Process prop;

        try {

            Runtime runtime = Runtime.getRuntime();
            if(!server){
                runtime.exec("conda activate big_data_mining");
            }
            String cmd = "python " + scripts + " "+ stopwordsPath + " " + wordcloudPath + " " + fontPath  + " \"" + content + "\"";
//            System.out.println(cmd);
            prop = runtime.exec(cmd);

            BufferedReader in = new BufferedReader(new InputStreamReader(prop.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            return prop.waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
