package com.farm.admin.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class CsvUtils {
    /**
     * CSV文件生成方法
     *
     * @param head
     * @param dataList
     * @param filename
     * @return
     */
    public static File createCSVFile(List<Object> head, List<List<Object>> dataList,
                                     String filename) {

        File csvFile = null;
        BufferedWriter csvWriter = null;
        try {
            String path = System.getProperty("java.io.tmpdir");
            csvFile = new File(path + File.separator + filename + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312使正确读取分隔符","
            csvWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "GB2312"), 1024);
            // 写入文件头部
            writeRow(head, csvWriter);

            // 写入文件内容
            for (List<Object> row : dataList) {
                writeRow(row, csvWriter);
            }
            csvWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    /**
     * 写一行数据方法
     *
     * @param row
     * @param csvWriter
     * @throws IOException
     */
    private static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        // 写入文件头部
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }

    public static void toExport(List<Object> csvHeader, List<List<Object>> dataList,
                                HttpServletResponse httpServletResponse) throws IOException {

        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".csv";
        String path = System.getProperty("java.io.tmpdir") + "\\" + fileName;
        File file = CsvUtils.createCSVFile(csvHeader, dataList, fileName);
        CsvUtils.toResponse(httpServletResponse, file, fileName);
    }

    public static void toResponse(HttpServletResponse httpServletResponse, File file, String fileName) throws IOException {
        BufferedInputStream bis = null;
        ServletOutputStream out = null;

        bis = new BufferedInputStream(new FileInputStream(file));
        out = httpServletResponse.getOutputStream();
        try {
            httpServletResponse.setContentType("application/octet-stream");
            httpServletResponse.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            byte[] buff = new byte[1024];
            int bytesRead = 0;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                out.write(buff, 0, bytesRead);
            }
        } finally {
            bis.close();
            out.close();
            if (file.exists()) {
                file.delete();
            }
        }
    }
}