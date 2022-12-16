package com.tc.utils;

import com.tc.exceptions.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    private static final String DATE_FORMAT_STR = "MM-dd-yyyy";

    private static final String DATE_TIME_FORMAT_STR = "yyyy-MM-dd HH:mm:ss.SSS";

    public static boolean validDate(String date) {
        LocalDate dateFormatted = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_STR);
        try {
            dateFormatted = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            LOGGER.info("Date cannot be converted, error: " + e.getMessage());
            return false;
        } finally {

            return true;
        }
    }

    public static LocalDate formatStrinToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_STR);
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException  e) {
            LOGGER.info("Date cannot be converted, error: " + e.getMessage());
        } finally {
            return localDate;
        }
    }

    public static LocalDateTime formatStrinToLocalDateTime(String date) {
        LocalDateTime dateFormatted = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_STR);
        try {
            dateFormatted = LocalDateTime.parse(date, formatter);
        } catch (BusinessException e) {
            throw new BusinessException("Date cannot be converted, error: " + e.getMessage());
        } finally {
            return dateFormatted;
        }
    }
}
