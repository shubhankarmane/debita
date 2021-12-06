package com.shubhankar.debita.util;

import javax.servlet.http.HttpServletRequest;

public class GetUserIdFromRequest {
    public static Integer get(HttpServletRequest request) {
        return (Integer) request.getAttribute("userId");
    }
}
