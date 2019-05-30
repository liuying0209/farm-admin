package com.farm.admin.export.impl;

import com.farm.admin.export.RecordExportService;
import com.farm.admin.farm.PlotCropParams;
import com.farm.admin.farm.service.PlotCropService;
import com.farm.admin.record.RecordParams;
import com.farm.admin.record.dto.RecordDTO;
import com.farm.admin.record.service.RecordService;
import com.farm.admin.util.CsvUtils;
import com.farm.base.common.enums.CropStatusEnum;
import com.farm.base.farm.PlotCrop;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 ** @Version 1.0.0
 */
@Service
public class RecordExportServiceImpl implements RecordExportService {

    private static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");
    private static final FastDateFormat OSS_FORMAT = FastDateFormat.getInstance("yyyyMMdd_HHmmss");

    @Autowired
    private RecordService recordService;
    @Autowired
    private PlotCropService plotCropService;

    @Override
    public void exportRecord(RecordParams params, HttpServletResponse response) throws IOException {
        Object[] csvHeader = {"农场名称", "地块名称", "作物", "批次", "农事环节", "农事时间", "记录时间", "总得分", "扣分原因"};

        List<RecordDTO> list = this.recordService.listAllRecord(params);
        List<Object> headerList = Arrays.asList(csvHeader);
        List<List<Object>> resultDataList = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(list)) {

            for (RecordDTO record : list) {
                Date updateTime = record.getUpdateTime();

                Integer score = record.getScore();
                Integer deductMarks = record.getDeductMarks();
                Integer totalScore = score - deductMarks;

                String reason = record.getReason();
                String content =  reason + "\t";

                Object[] accountProfitArray = new Object[csvHeader.length];
                accountProfitArray[0] = record.getFarmName();
                accountProfitArray[1] = record.getPlotName();
                accountProfitArray[2] = record.getCropName();
                accountProfitArray[3] = record.getCropVariety();
                accountProfitArray[4] = record.getFarmWorkName();
                accountProfitArray[5] = record.getTime() == null ? "" : DATE_FORMAT.format(record.getTime()) + "\t";
                accountProfitArray[6] = updateTime == null ? "" : DATE_FORMAT.format(updateTime) + "\t";
                accountProfitArray[7] = totalScore;
                accountProfitArray[8] = content;
                resultDataList.add(Arrays.asList(accountProfitArray));

            }
            CsvUtils.toExport(headerList, resultDataList, response);

        }


    }

    @Override
    public void exportPlotCrop(PlotCropParams params, HttpServletResponse response) throws IOException {


        Object[] csvHeader = {"地块", "作物", "品种", "批次", "分值", "总分", "状态"};

        List<PlotCrop> list = this.plotCropService.listAll(params);
        List<Object> headerList = Arrays.asList(csvHeader);
        List<List<Object>> resultDataList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(list)){

            for (PlotCrop plotCrop :list){
                String plotName = plotCrop.getPlotName();
                String cropName = plotCrop.getCropName();
                String cropVariety = plotCrop.getCropVariety();
                String batchTime = plotCrop.getBatchTime();
                Integer score = plotCrop.getScore();
                Integer rawScore = plotCrop.getRawScore();
                CropStatusEnum status = plotCrop.getStatus();

                Object[] accountProfitArray = new Object[csvHeader.length];
                accountProfitArray[0] = plotName;
                accountProfitArray[1] = cropName;
                accountProfitArray[2] = cropVariety;
                accountProfitArray[3] = batchTime;
                accountProfitArray[4] = score;
                accountProfitArray[5] = rawScore;
                accountProfitArray[6] = status.getMsg();

                resultDataList.add(Arrays.asList(accountProfitArray));

            }

            CsvUtils.toExport(headerList, resultDataList, response);

        }

    }
}
