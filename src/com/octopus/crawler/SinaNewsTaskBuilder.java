package com.octopus.crawler;

import com.lz.octopus.builder.ITaskBuilder;
import com.lz.octopus.common.task.ITaskDetail;
import com.lz.octopus.common.task.TaskTypeAnnotation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kfzx-liuyz1 on 2016/11/11.
 */
@TaskTypeAnnotation(taskType="SINANEWS", version="201611111400")
public class SinaNewsTaskBuilder implements ITaskBuilder {

    private final int pageCnt = 20;

    @Override
    public List<ITaskDetail> buildTasks() {
        List<ITaskDetail> taskList = new ArrayList<>();
        String baseURL = "http://search.sina.com.cn/";
        String crawlURL = "?q=%D7%D4%C3%B3%C7%F8&c=news&from=index&" +
        "col=&range=&source=&country=&size=&time=&a=&page=" + "{PAGENUM}" + "&pf=2131425452&" +
        "ps=2134309112&dpc=1";
        for(int i = 0 ; i < 20; ++i){
            SinaNewsTaskDetail sinaNewsTaskDetail = new SinaNewsTaskDetail();
            sinaNewsTaskDetail.setUrl(baseURL + crawlURL.replace("{PAGENUM}",String.valueOf(i)));
            taskList.add(sinaNewsTaskDetail);
        }

        return taskList;
    }
}
