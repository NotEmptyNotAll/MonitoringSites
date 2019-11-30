package com.testtask.monitoring.Service;

import com.testtask.monitoring.StatusConst;
import com.testtask.monitoring.model.SiteModel;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HTTPStatService {

    private HttpResponse response;

    private String status;

    private long responseSize;

    private long start;

    private long end;

    private ArrayList<String> listResponse = new ArrayList<>();

    private SiteModel siteModel;


    public HTTPStatService(SiteModel siteModel) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(siteModel.getUrl());
            this.siteModel = siteModel;
            this.start = System.currentTimeMillis();
            this.response = client.execute(request);
            responseSize = EntityUtils.toByteArray ( this.response.getEntity()).length;
            this.end = System.currentTimeMillis();
            getAllHeadersFromHTTP();
            chcekStatus();
            checkTimeResponse();
            checkSize();
        } catch (IOException e) {
            e.printStackTrace();
            this.status = StatusConst.STATUS_CRITICAL;
        } catch (Exception e) {
            e.printStackTrace();
            this.status = StatusConst.STATUS_CRITICAL;
        }
        //checkSize();
        //  getAllHeadersFromHTTP();
    }


    private String chcekStatus() {
        int code = response.getStatusLine().getStatusCode();
        if (code < 200 || code > 300) {
            status = StatusConst.STATUS_CRITICAL;
        } else {
            status = StatusConst.STATUS_OK;
        }
        return status;
    }

    private String checkSize() {
        responseSize = this.response.getEntity().getContentLength();
        if(responseSize == -1)
            return status;
        if (responseSize > siteModel.getMax() || responseSize < siteModel.getMin() ) {
            this.status = StatusConst.STATUS_CRITICAL;
        }
        return status;
    }

    private String checkTimeResponse() {
        long difference = (end - start);
        if (difference < StatusConst.TIME_OF_OK) {
            status = StatusConst.STATUS_OK;
        } else if (difference > StatusConst.TIME_OF_OK && difference < StatusConst.TIME_OF_WARNING) {
            status = StatusConst.STATUS_WARNING;
        } else {
            status = StatusConst.STATUS_CRITICAL;
        }
        return status;
    }

    private String checkSubstingInHeader() {
        for (String tempStr :
                listResponse) {
            if (tempStr.contains(siteModel.getSubstringInResponse())) {
                System.out.println(StatusConst.STATUS_OK);
                status = StatusConst.STATUS_OK;
            } else {
                System.out.println(StatusConst.STATUS_WARNING);
                status = StatusConst.STATUS_CRITICAL;
            }
        }
        return status;
    }

    private void getAllHeadersFromHTTP() {
        Header[] headers = this.response.getAllHeaders();
        for (Header header : headers) {
            this.listResponse.add((header.getName()
                    + " : " + header.getValue()));
        }
    }

    public String getStatus() {
        return status;
    }

    public List<String> getAllHeaders() {
        return this.listResponse;
    }

}
