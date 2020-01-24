package com.testtask.monitoring.Service;

import java.io.IOException;

public interface HTTPValidation {
    String STATUS_WARNING = "WARNING";
    String STATUS_OK = "OK";
    String STATUS_CRITICAL = "CRITICAL";

    int TIME_OF_WARNING = 5000;
    int TIME_OF_OK = 3000;


    String chcekStatus();

    String checkSize() throws IOException;

    String checkTimeResponse();

    String checkSubstingInHeader();

    String getStatus();
}
