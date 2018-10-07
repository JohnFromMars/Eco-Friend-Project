package com.ecofriend.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ecofriend.App;

@WebAppConfiguration
@SpringApplicationConfiguration(App.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCase {
	
	@Test
	public void testDummy(){
		
		System.out.println("Good");
		
	}

}
