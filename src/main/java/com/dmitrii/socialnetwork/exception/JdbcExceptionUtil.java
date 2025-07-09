package com.dmitrii.socialnetwork.exception;

import java.sql.SQLException;
import org.springframework.dao.DataIntegrityViolationException;

public final class JdbcExceptionUtil {
    private JdbcExceptionUtil() {}

    public static boolean isDuplicateKey(DataIntegrityViolationException ex) {
        Throwable root = ex.getRootCause();
        if (root instanceof SQLException sqlEx) {
            return "23505".equals(sqlEx.getSQLState());
        }
        return false;
    }
}