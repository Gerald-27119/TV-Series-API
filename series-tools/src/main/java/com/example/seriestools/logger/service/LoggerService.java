package com.example.seriestools.logger.service;

import org.springframework.stereotype.Service;
import org.springframework.core.io.InputStreamResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class LoggerService {

    private static final String LOG_UPDATER_PATH = "updater.log";

    public InputStreamResource getLogFile() throws IOException {
        File file = new File(LOG_UPDATER_PATH);
        return new InputStreamResource(new FileInputStream(file));
    }

    public long getFileLength() {
        File file = new File(LOG_UPDATER_PATH);
        return file.length();
    }

    public String getFileName() {
        File file = new File(LOG_UPDATER_PATH);
        return file.getName();
    }
}