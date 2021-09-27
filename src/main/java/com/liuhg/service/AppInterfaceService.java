package com.liuhg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

public interface AppInterfaceService {
    public HashMap<String, Object> queryByReleaseTime();
}
