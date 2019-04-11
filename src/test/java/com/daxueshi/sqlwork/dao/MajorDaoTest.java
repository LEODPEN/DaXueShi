package com.daxueshi.sqlwork.dao;

import com.daxueshi.sqlwork.domain.Major;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author onion
 * @date 2019-04-11 -16:12
 */
public class MajorDaoTest {
    @Autowired
    private MajorDao majorDao;

    @Test
    public void testMajor(){
        List<Major> all = majorDao.findAll();
        for (Major major : all) {
            System.out.println("majorname = " + major.getMajorName());
        }
    }
}
