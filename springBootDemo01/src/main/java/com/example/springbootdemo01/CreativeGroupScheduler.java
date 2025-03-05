package com.example.springbootdemo01;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * CreativeGroupScheduler 修改提审状态
 *
 * @author zhangrui
 */
@Slf4j
@Component
@RequiredArgsConstructor
@EnableScheduling
public class CreativeGroupScheduler {

    /**
     * 上午首板推荐策略
     * shizhi的条件不能加，因为板块资金可能主要流入排名第一的gp，排除掉500y以上的，误判率就增加了。   没有合适的就kongcang即可。
     */
    @Scheduled(cron = "0 32 9 * * ?")
    public void sbRecommend() {
        log.info("sbRecommend start.....");
        runPython("D:\\workspace\\python\\AkShare\\dataAnalysis\\sb_recommend.py", "09:30:00", "09:32:00");
    }

    /**
     * 下午首板策略
     */
    @Scheduled(cron = "0 33 13 * * ?")
    public void sbRecommend2() {
        log.info("sbRecommend2 start.....");
        runPython("D:\\workspace\\python\\AkShare\\dataAnalysis\\sb_recommend.py", "13:30:00", "13:33:00");
    }


    private void runPython(String path, String... args) {
        try {
            // 调用 Python 脚本
            ProcessBuilder processBuilder = null;
            if (args != null && args.length != 0) {
                String[] params = new String[args.length + 2];
                params[0] = "python";
                params[1] = "\"" + path + "\"";
                for (int i = 2; i < params.length; i++) {
                    params[i] = "\"" + args[i - 2] + "\"";
                }
                processBuilder = new ProcessBuilder(params);
            } else {
                processBuilder = new ProcessBuilder("python", path);
            }
            processBuilder.redirectErrorStream(true); // 将错误流合并到标准输出流

            try {
                Process process = processBuilder.start();
                log.info("=========>inputStreamToString:{}", inputStreamToString(process.getInputStream()).toString());
                /*BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String todayStr = dateFormat.format(new Date());
                    if (line.startsWith(todayStr)) {
                        System.out.println(line);
                    }
                }
                int exitCode = process.waitFor();*/
            } catch (IOException e) {
                e.printStackTrace();
            }
            /*catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String inputStreamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] tmp = new byte[1024];
        int len;
        while ((len = inputStream.read(tmp)) != -1) {
            outputStream.write(tmp, 0, len);
        }
        inputStream.close();
        outputStream.close();
        return outputStream.toString();
    }


}
