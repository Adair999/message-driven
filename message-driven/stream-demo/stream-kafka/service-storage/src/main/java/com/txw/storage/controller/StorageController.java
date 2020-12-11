package com.txw.storage.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
@Slf4j
@RestController
public class StorageController {
    @RequestMapping(value = "/storage", method = RequestMethod.GET)
    public String storage(HttpServletRequest request) {
        return "storage success";
    }
}