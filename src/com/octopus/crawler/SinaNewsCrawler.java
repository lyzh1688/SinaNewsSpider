package com.octopus.crawler;

import com.lz.octopus.common.httpclient.HttpClient;
import com.lz.octopus.common.httpclient.HttpClientBuilder;
import com.lz.octopus.common.result.Result;
import com.lz.octopus.common.task.Task;
import com.lz.octopus.common.task.TaskType;
import com.lz.octopus.common.task.TaskTypeAnnotation;
import com.lz.octopus.crawler.IBusinessCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kfzx-liuyz1 on 2016/11/11.
 */
@TaskTypeAnnotation(taskType="SINANEWS", version="201611111400")
public class SinaNewsCrawler implements IBusinessCrawler {

    private HttpClient httpClient;

    public SinaNewsCrawler(){
        this.httpClient = HttpClientBuilder.createDefault()
                .setSocketTimeout(10000)
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .buildHttpClient();
    }

    @Override
    public List<Result> crawl(Task task) {
        List<Result> resultList = new ArrayList<>();
        SinaNewsTaskDetail sinaNewsTaskDetail = (SinaNewsTaskDetail)task.getTaskDetail();
        String url = sinaNewsTaskDetail.getUrl();
        Map<String, String> headers = new HashMap<String,String>();
        headers.put("User-Agent", "Opera/9.80 (Windows NT 6.1; U; en) Presto/2.8.131 Version/11.11");
        Map<String, String> retMap = httpClient.sendGet(url,headers);
        String response_content =  retMap.get("response_content");
        Document doc = Jsoup.parse(response_content);
        Elements elements = doc.select("div .r-info").select(".r-info2").select("h2");
        for(Element elem : elements){
            //parse the html
            Element a = elem.select("a").first();
            String href = a.attr("href");
            String title = a.text();
            Element span = elem.select(".fgray_time").first();
            String source = span.text().split(" ")[0];
            String time = span.text().split(" ")[1] + " " + span.text().split(" ")[2];
            SinaNewsResultDetail sinaNewsResultDetail = new SinaNewsResultDetail();

            //fill the field of the result detail
            sinaNewsResultDetail.setHref(href);
            sinaNewsResultDetail.setTime(time);
            sinaNewsResultDetail.setSource(source);
            sinaNewsResultDetail.setTitle(title);

            //construct Result
            Result result = new Result(task.getTaskType(), null);
            result.setBuildTime(task.getBuildTime());
            result.setTaskId(task.getTaskId());
            result.setResultDetail(sinaNewsResultDetail);

            resultList.add(result);
        }

        return resultList;
    }

    @Override
    public Class<?> getTaskDetailClass() {
        return SinaNewsTaskDetail.class;
    }

    public static void main(String[] args) {
        SinaNewsCrawler c = new SinaNewsCrawler();
        SinaNewsTaskDetail taskDetail = new SinaNewsTaskDetail();
        taskDetail.setUrl("http://search.sina.com.cn/?q=%D7%D4%C3%B3%C7%F8&c=news&from=index&col=&range=&source=&country=&size=&time=&a=&page=1&pf=2131425452&ps=2134309112&dpc=1");
        TaskType taskType = new TaskType("SINA_NEWS", 5, 6);
        Task task = new Task(taskType, taskDetail);
        List<Result> resultList = c.crawl(task);

    }
}
