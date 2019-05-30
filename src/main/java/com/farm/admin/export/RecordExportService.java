package com.farm.admin.export;

import com.farm.admin.farm.PlotCropParams;
import com.farm.admin.record.RecordParams;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 ** @Version 1.0.0
 */
public interface RecordExportService {

    /**
     * 导出记录
     * @param params
     * @param response
     * @throws IOException
     */
    void exportRecord(RecordParams params, HttpServletResponse response) throws IOException;
    /**
     * 导出品种作物
     */

    void exportPlotCrop(PlotCropParams params,HttpServletResponse response) throws IOException;

}
