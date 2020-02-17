package com.atguigu.springboot.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String locale = request.getParameter("locale");
        Locale locale1 = Locale.getDefault();
        if(!StringUtils.isEmpty(locale)){
            String[] s = locale.split("_");
            locale1 = new Locale(s[0], s[1]);
        }
        return locale1;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
