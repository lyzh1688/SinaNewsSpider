package com.octopus.crawler;

import com.lz.octopus.common.task.ITaskDetail;
import com.lz.octopus.common.task.TaskTypeAnnotation;

/**
 * Created by kfzx-liuyz1 on 2016/11/11.
 */
@TaskTypeAnnotation(taskType="SINANEWS", version="201611111400")
public class SinaNewsTaskDetail extends ITaskDetail {

    private String url;

    private String taskType = "SINANEWS";

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
