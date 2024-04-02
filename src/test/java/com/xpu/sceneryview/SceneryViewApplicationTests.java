package com.xpu.sceneryview;

import com.xpu.sceneryview.utils.WordCloudUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootTest
class SceneryViewApplicationTests {
    @Value("${start}")
    private String start;

    @Test
    void contextLoads() {
    }

    @Test
    void textWordCloud() throws IOException {
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


        String script = "D:\\Test\\Java\\scenery-view\\src\\main\\java\\com\\xpu\\sceneryview\\scripts\\wordcloudtest.py";

        try {

            Runtime runtime = Runtime.getRuntime();
            runtime.exec("conda activate big_data_mining");
            prop = runtime.exec("python " + script);

            BufferedReader in = new BufferedReader(new InputStreamReader(prop.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            int backCode = prop.waitFor();
            System.out.println(backCode);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testUtil(){
        int i = WordCloudUtil.generateWordCloud("北京的故宫真的很大，每一座宫殿都有它自己的故事。不过，人确实很多，建议早点去或者选择淡季游览，这样才能更好地体验故宫的魅力。\n\n");
        System.out.println(i);
    }

    @Test
    void tsetpro(){
        System.out.println(start);
    }




}
