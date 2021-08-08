package com.calendar.base;

import com.calendar.models.EventGroup;
import com.calendar.repositories.EventGroupRepository;
import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SimpleTest {
    @Autowired
    EventGroupRepository eventGroupRepository;

    @Test
    public void connectionTest(){
        long count = 0L;
        for (EventGroup eventGroup : eventGroupRepository.findAll()) {
            count++;
        }
        System.out.println(count);

        Assert.assertTrue(true);
    }
}
