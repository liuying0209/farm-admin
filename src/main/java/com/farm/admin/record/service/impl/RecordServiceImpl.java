package com.farm.admin.record.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.farm.admin.admin.Admin;
import com.farm.admin.admin.AdminException;
import com.farm.admin.farm.mapper.FarmMapper;
import com.farm.admin.farm.mapper.PlotCropMapper;
import com.farm.admin.record.AssessRecordVO;
import com.farm.admin.record.RecordParams;
import com.farm.admin.record.dto.RecordDTO;
import com.farm.admin.record.mapper.FarmingParamMapper;
import com.farm.admin.record.mapper.FarmingRecordMapper;
import com.farm.admin.record.mapper.RecordAssessMapper;
import com.farm.admin.record.service.RecordService;
import com.farm.admin.util.AdminUtil;
import com.farm.base.BaseException;
import com.farm.base.common.Page;
import com.farm.base.common.enums.DeductMarksTypeEnum;
import com.farm.base.exception.FarmException;
import com.farm.base.exception.RecordException;
import com.farm.base.farm.Farm;
import com.farm.base.farm.PlotCrop;
import com.farm.base.record.FarmingParam;
import com.farm.base.record.FarmingRecord;
import com.farm.base.record.ParamContent;
import com.farm.base.record.RecordAssess;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 ** @Version 1.0.0
 */
@Service
public class RecordServiceImpl implements RecordService {
    private final static Logger LOGGER = LoggerFactory.getLogger(RecordServiceImpl.class);

    private FarmingParamMapper farmingParamMapper;
    private FarmingRecordMapper farmingRecordMapper;
    private RecordAssessMapper recordAssessMapper;
    private PlotCropMapper plotCropMapper;
    private FarmMapper farmMapper;

    @Autowired
    @SuppressWarnings("all")
    public RecordServiceImpl(FarmingParamMapper farmingParamMapper, FarmingRecordMapper farmingRecordMapper,
                             RecordAssessMapper recordAssessMapper, PlotCropMapper plotCropMapper, FarmMapper farmMapper) {
        this.farmingParamMapper = farmingParamMapper;
        this.farmingRecordMapper = farmingRecordMapper;
        this.recordAssessMapper = recordAssessMapper;
        this.plotCropMapper = plotCropMapper;
        this.farmMapper = farmMapper;
    }

    @Override
    public Page pagingRecord(RecordParams params) throws BaseException {
        if (params.getPageNo() == null || params.getPageSize() == null) {
            LOGGER.error("分页条件为空 pageNo :{}, pageSize :{}", params.getPageNo(), params.getPageSize());
            throw new BaseException(BaseException.ERROR_CODE_ILLEGAL_ARGUMENTS);
        }
        Page<RecordParams> page = new Page<>(params.getPageNo(), params.getPageSize(), params);

        int totalRecord = this.farmingRecordMapper.countRecord(page);
        page.setTotalCount(totalRecord);
        LOGGER.info("查询到的总数是 :{}", totalRecord);
        if (totalRecord > 0) {
            List<RecordDTO> listRecord = this.farmingRecordMapper.pagingRecord(page);

            for (RecordDTO record : listRecord) {
                Long recordId = record.getRecordId();
                Integer assessFlag = record.getAssessFlag();
                if (assessFlag == 1) {
                    LOGGER.info("评估的记录 recordId :{}", recordId);
                    RecordAssess recordAssess = this.recordAssessMapper.findByRecordId(recordId);
                    if (recordAssess != null) {
                        record.setReason(recordAssess.getReason());
                        record.setDeductMarks(recordAssess.getDeductMarks());
                        record.setDeductMarksType(recordAssess.getDeductMarksType());
                    }
                }

                handleRecordDTO(record);
            }
            page.setData(listRecord);

        }
        return page;
    }

    @Override
    public List<RecordDTO> listAllRecord(RecordParams params) {
        List<RecordDTO> listRecord = this.farmingRecordMapper.listAllRecord(params);

        if (CollectionUtils.isNotEmpty(listRecord)) {

            for (RecordDTO record : listRecord) {
                Long recordId = record.getRecordId();
                Integer assessFlag = record.getAssessFlag();
                if (assessFlag == 1) {
                    LOGGER.info("评估的记录 recordId :{}", recordId);
                    RecordAssess recordAssess = this.recordAssessMapper.findByRecordId(recordId);
                    if (recordAssess != null) {
                        record.setReason(recordAssess.getReason());
                        record.setDeductMarks(recordAssess.getDeductMarks());
                        record.setDeductMarksType(recordAssess.getDeductMarksType());
                    }
                }

                handleRecordDTO(record);
            }
        }
        return listRecord;
    }

    /**
     * 封装 页面参数信息
     *
     * @param record
     */
    private void handleRecordDTO(RecordDTO record) {
        Long recordId = record.getRecordId();
        //处理图片  和详细页面参数
        FarmingParam farmingParam = this.farmingParamMapper.findByRecordId(recordId);
        String content = farmingParam.getContent();

        List list = JSONObject.parseObject(content, List.class);

        Map<String, Object> map = handleParams(list);
        String detail = (String) map.get("remark");
        List<String> images = (List<String>) map.get("images");
        record.setImages(images);
        record.setDetail(detail);

    }


    @Override
    public RecordDTO getNoEvaluationRecord(RecordParams params) {

        RecordDTO record = this.farmingRecordMapper.getNoEvaluationRecord(params);

        if (record != null) {
            handleRecordDTO(record);
        }
        return record;
    }

    @Override
    @Transactional
    public void assessRecord(AssessRecordVO params) throws RecordException, AdminException, FarmException {
        Long recordId = params.getRecordId();
        if (recordId == null) {
            LOGGER.error("必要参数为空");
            throw new RecordException(RecordException.ERROR_CODE_ILLEGAL_ARGUMENTS);
        }

        Integer deductMarks = params.getDeductMarks();
        Integer deductMarksType = params.getDeductMarksType();
        String reason = params.getReason();

        FarmingRecord currentRecord = this.farmingRecordMapper.findById(recordId);
        Integer assessFlag = currentRecord.getAssessFlag();
        if (assessFlag == 1) {
            LOGGER.error("该记录已经评估");
            throw new FarmException(FarmException.THE_RECORD_ASSESS_ERROR);
        }


        FarmingRecord record = new FarmingRecord();
        record.setId(recordId);
        record.setAssessFlag(1);
        if (deductMarksType == 1) {
            record.setScore(0);
        }
        this.farmingRecordMapper.update(record);

        Admin admin = AdminUtil.getAdmin();
        Long adminId = admin.getId();

        RecordAssess recordAssess = new RecordAssess();
        recordAssess.setRecordId(recordId);
        if (deductMarksType == 1) {
            recordAssess.setDeductMarks(0);
        } else {
            recordAssess.setDeductMarks(deductMarks);
        }
        recordAssess.setReason(reason);
        recordAssess.setAdminId(adminId);

        if (deductMarksType != null) {
            recordAssess.setDeductMarksType(DeductMarksTypeEnum.valueOf(deductMarksType));
        }

        this.recordAssessMapper.insert(recordAssess);

        countScore(recordId);
    }

    /**
     * 更新所有分数
     *
     * @throws RecordException
     */
    private void countScore(Long recordId) throws FarmException {

        //更新相应的 作物分值 农场 分值
        FarmingRecord farmingRecord = this.farmingRecordMapper.findById(recordId);
        if (farmingRecord == null) {
            LOGGER.error("获取记录有误");
            throw new FarmException(FarmException.FARMING_RECORD_INFO_ERROR);
        }
        Long plotCropId = farmingRecord.getPlotCropId();

        int plotCropCount = farmingRecordMapper.countScoreByPlotCropId(plotCropId);


        PlotCrop plotCrop = new PlotCrop();
        plotCrop.setId(plotCropId);
        plotCrop.setScore(plotCropCount);

        this.plotCropMapper.update(plotCrop);

        //获取农场id
        long farmId = this.plotCropMapper.getFarmId(plotCropId);

        int countPlotCrop = this.plotCropMapper.countPlotCropByFarmId(farmId);

        if (countPlotCrop == 0) {
            return;
        }

        //查询总的分
        int farmScore = this.plotCropMapper.countFarmScore(farmId);
        Integer result = farmScore / countPlotCrop;
        //修改农场分值
        Farm farm = new Farm();
        farm.setId(farmId);
        farm.setScore(result);
        this.farmMapper.update(farm);

    }


    @Override
    public void updateAssess(AssessRecordVO params) throws RecordException, FarmException {

        Long recordId = params.getRecordId();
        if (recordId == null) {
            LOGGER.error("必要参数为空");
            throw new RecordException(RecordException.ERROR_CODE_ILLEGAL_ARGUMENTS);

        }

        Integer deductMarks = params.getDeductMarks();
        Integer deductMarksType = params.getDeductMarksType();
        String reason = params.getReason();

        RecordAssess assess = this.recordAssessMapper.findByRecordId(recordId);
        if (assess != null) {
            assess.setRecordId(recordId);
            assess.setDeductMarksType(DeductMarksTypeEnum.valueOf(deductMarksType));
            assess.setReason(reason);
            assess.setDeductMarks(deductMarks);
            this.recordAssessMapper.update(assess);
            countScore(recordId);
        }
    }


    /**
     * 递归获取 图片和文案
     */
    private static Map<String, Object> handleParams(List list) {

        Map<String, Object> result = new HashMap<>();
        String remark = "";

        List<String> images = new ArrayList<>();

        if (CollectionUtils.isEmpty(list)) {
            result.put("images", images);
            result.put("remark", remark);
            return result;
        }

        for (Object obj : list) {

            JSONObject jsonObject = (JSONObject) obj;
            ParamContent paramContent = jsonObject.toJavaObject(ParamContent.class);
            if ("0".equals(paramContent.getFlag())) {
                continue;
            }


            switch (paramContent.getType()) {
                case "2":
                    //递归
                    JSONArray value = (JSONArray) paramContent.getValue();
                    List sunList = value.toJavaObject(List.class);
                    Map<String, Object> map = handleParams(sunList);
                    String remarkStr = (String) map.get("remark");
                    List<String> tempList = (List<String>) map.get("images");
                    images.addAll(tempList);
                    remark += remarkStr;
                    break;
                case "0":
                    String valueStr = paramContent.getValue().toString();
                    String[] split = valueStr.split(",");
                    List<String> stringList = Arrays.asList(split);
                    images.addAll(stringList);
                    break;
                default:
                    String key = paramContent.getName();
                    String valueText = paramContent.getValue().toString();
                    String unit = paramContent.getUnit();
                    remark += key + valueText + unit + " ";
                    break;
            }
        }

        result.put("images", images);
        result.put("remark", remark);
        return result;
    }
}
