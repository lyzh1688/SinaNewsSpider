package com.octopus.crawler;

import com.lz.octopus.common.result.Result;
import com.lz.octopus.common.task.TaskTypeAnnotation;
import com.lz.octopus.writter.IResultWritter;
import com.lz.octopus.writter.db.JDBCUtils;

import java.util.List;

/**
 * Created by kfzx-liuyz1 on 2016/11/11.
 */
@TaskTypeAnnotation(taskType="SINANEWS", version="201611111400")
public class SinaNewsResultWritter implements IResultWritter {

    private JDBCUtils jdbcUtils = new JDBCUtils();

    @Override
    public void writeResult(List<Result> list) {
        this.jdbcUtils.batchInsertCrawlResult(list);
    }
}
