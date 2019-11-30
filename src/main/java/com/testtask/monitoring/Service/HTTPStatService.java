package com.testtask.monitoring.Service;

import com.testtask.monitoring.model.SiteModel;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HTTPStatService implements HTTPValidation {

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
            this.end = System.currentTimeMillis();
            status = STATUS_OK;
        } catch (IOException e) {
            e.printStackTrace();
            this.status = STATUS_CRITICAL;
        } catch (Exception e) {
            e.printStackTrace();
            this.status = STATUS_CRITICAL;
        }
    }


    public String chcekStatus() {
        int code = response.getStatusLine().getStatusCode();
        if (code < 200 || code > 300) {
            status = STATUS_CRITICAL;
        }
        return status;
    }

    public String checkSize() throws IOException {
        responseSize = this.response.getEntity().getContentLength();
        if (responseSize == -1)
            responseSize = EntityUtils.toByteArray(this.response.getEntity()).length;
        if (responseSize > siteModel.getMax() || responseSize < siteModel.getMin()) {
            this.status = STATUS_CRITICAL;
        }
        return status;
    }

    public String checkTimeResponse() {
        long difference = (end - start);
        if (difference < TIME_OF_OK) {
            status = STATUS_OK;
        } else if (difference > TIME_OF_OK && difference < TIME_OF_WARNING) {
            status = STATUS_WARNING;
        } else {
            status = STATUS_CRITICAL;
        }
        return status;
    }

    public String checkSubstingInHeader() {
        if (!siteModel.getSubstringInResponse().equals("")) {
            for (String tempStr :
                    listResponse) {
                if (!tempStr.contains(siteModel.getSubstringInResponse())) {
                    status = STATUS_CRITICAL;
                }
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
