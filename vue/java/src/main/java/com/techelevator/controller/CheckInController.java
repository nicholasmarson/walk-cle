package com.techelevator.controller;

import com.techelevator.dao.CheckInRecordDao;
import com.techelevator.model.CheckInRecord;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/checkinrecords")
public class CheckInController {

    private CheckInRecordDao checkInRecordDao;

    public CheckInController(CheckInRecordDao checkInRecordDao) {
        this.checkInRecordDao = checkInRecordDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CheckInRecord> getCheckInRecords(Principal principal) {
        String username = principal.getName();
        return checkInRecordDao.getCheckInRecords(username);
    }
}
