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


//    /**
//     * 股票代码与名称收集
//     */
//    @Scheduled(cron = "0 16 9 * * ?")
//    public void getStockCodeSh() {
//        log.info("getStockCodeSh start.....");
//        runPython("D:\\workspace\\python\\AkShare\\dataMining\\stockInfo\\getStockCodeSh.py");
//    }
//
//    /**
//     * 股票代码与名称收集
//     */
//    @Scheduled(cron = "0 17 9 * * ?")
//    public void getStockCodeSz() {
//        log.info("getStockCodeSz start.....");
//        runPython("D:\\workspace\\python\\AkShare\\dataMining\\stockInfo\\getStockCodeSz.py");
//    }
//
//    /**
//     * 股票基本信息收集
//     */
//    @Scheduled(cron = "0 18 9 * * ?")
//    public void getStockInfo() {
//        log.info("getStockInfo start.....");
//        runPython("D:\\workspace\\python\\AkShare\\dataMining\\stockInfo\\getStockInfo.py");
//    }
//
//
//    /**
//     * 上午主力抢筹推荐
//     */
//    @Scheduled(cron = "0 15 11 * * ?")
//    public void recommend() {
//        log.info("recommend start.....");
//        runPython("D:\\workspace\\python\\AkShare\\dataAnalysis\\zlqc_recommend.py");
//    }
//
//    /**
//     * python测试
//     */
//    @Scheduled(cron = "0 45 14 * * ?")
//    public void testPython() {
//        log.info("testPython start.....");
//        runPython("D:\\workspace\\python\\AkShare\\dataAnalysis\\Hello.py", "14:43:00", "14:45:00");
//    }
//
//    /**
//     * 下午主力抢筹推荐
//     */
//    @Scheduled(cron = "0 40 14 * * ?")
//    public void recommend2() {
//        log.info("recommend2 start.....");
//        runPython("D:\\workspace\\python\\AkShare\\dataAnalysis\\zlqc_recommend.py");
//    }
//
//    /**
//     * 行业板块信息收集
//     */
//    @Scheduled(cron = "0 10 15 * * ?")
//    public void getBoardInfo() {
//        log.info("getBoardInfo start.....");
//        runPython("D:\\workspace\\python\\AkShare\\dataMining\\stockBoard\\getBoardInfo.py");
//    }
//
//    /**
//     * 行业板块成分股收集
//     */
//    @Scheduled(cron = "0 15 15 * * ?")
//    public void getStockBoardRelation() {
//        log.info("getStockBoardRelation start.....");
//        runPython("D:\\workspace\\python\\AkShare\\dataMining\\stockBoard\\getStockBoardRelation.py");
//    }
//
//    /**
//     * 资金流向信息收集
//     */
//    @Scheduled(cron = "0 0/1 * * * ?")
//    public void getSectorMainFlow() {
//        log.info("getSectorMainFlow start.....");
//        runPython("D:\\workspace\\python\\AkShare\\dataMining\\stockBoard\\getSectorMainFlow.py");
//    }
//
//    /**
//     * 股票内外盘信息收集
//     */
//    @Scheduled(cron = "30 0/5 * * * ?")
//    public void getStockTradeData() {
//        log.info("getStockTradeData start.....");
//        runPython("D:\\workspace\\python\\AkShare\\dataMining\\stockTrade\\getStockTradeData.py");
//    }
//
//    /**
//     * 股票股东信息收集
//     */
//    @Scheduled(cron = "0 20 15 * * ?")
//    public void getStockHolderData() {
//        log.info("getStockHolderData start.....");
//        runPython("D:\\workspace\\python\\AkShare\\dataMining\\stockInfo\\getStockHolderData.py");
//    }
//
//    /**
//     * 股票业绩信息收集
//     */
//    @Scheduled(cron = "0 25 15 * * ?")
//    public void getStockFinancialData() {
//        log.info("getStockFinancialData start.....");
//        runPython("D:\\workspace\\python\\AkShare\\dataMining\\stockInfo\\getStockFinancialData.py");
//    }
//
//    /**
//     * 股票业绩预测
//     */
//    @Scheduled(cron = "0 30 15 * * ?")
//    public void getStockPerformanceForecastData() {
//        log.info("getStockPerformanceForecastData start.....");
//        runPython("D:\\workspace\\python\\AkShare\\dataMining\\stockInfo\\getStockPerformanceForecastData.py");
//    }
//
//    /**
//     * 概念板块信息收集
//     */
//    @Scheduled(cron = "0 35 15 * * ?")
//    public void getStockBoardConceptName() {
//        log.info("getStockBoardConceptName start.....");
//        runPython("D:\\workspace\\python\\AkShare\\dataMining\\stockBoard\\getStockBoardConceptName.py");
//    }
//
//    /**
//     * 概念板块成分股收集
//     */
//    @Scheduled(cron = "0 40 15 * * ?")
//    public void getStockBoardConceptRelation() {
//        log.info("getStockBoardConceptRelation start.....");
//        runPython("D:\\workspace\\python\\AkShare\\dataMining\\stockBoard\\getStockBoardConceptRelation.py");
//    }
//
//    /**
//     * 人气排名收集
//     */
//    @Scheduled(cron = "0 0 20 * * ?")
//    public void getStockHotRank() {
//        log.info("getStockHotRank start.....");
//        runPython("D:\\workspace\\python\\AkShare\\dataMining\\stockTrade\\getStockHotRank.py");
//    }


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
