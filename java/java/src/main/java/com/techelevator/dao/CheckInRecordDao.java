package com.techelevator.dao;

import com.techelevator.model.CheckInRecord;

import java.util.List;

public interface CheckInRecordDao {
    List<CheckInRecord> getCheckInRecords(String username);
}
