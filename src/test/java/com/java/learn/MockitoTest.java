package com.java.learn;

import com.java.learn.mockito.InnerService;
import com.java.learn.mockito.ServiceA;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class MockitoTest {

    @Before
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);
        System.out.println(111);
    }

    @After
    public void afterMethod() {
        System.out.println(222);
    }

    @Autowired
    private ServiceA serviceA;
    @Autowired
    @MockBean
    private InnerService innerService;

    /**
     * 使用Mockito框架测试mock bean的功能
     * 该测试方法主要用于验证serviceA中调用mock的innerService时的行为是否符合预期
     */
    @Test
    public void tesMockBean() {
        // 设置当调用innerService的testEx方法时，不论传入什么参数，都返回"hello mockito"
        Mockito.when(innerService.testEx(Mockito.any())).thenReturn("hello mockito");
        Mockito.when(innerService.testEx(Mockito.any())).thenAnswer(iv -> "hello mockito1");
        // 调用serviceA的testMocK方法，预期返回值为"hello mockito"
        String s = serviceA.testMocK();
        // 验证testEx方法是否被正确调用了两次，以确保mock行为符合预期
        //Mockito.verify(innerService, Mockito.times(2)).testEx(Mockito.any());
        // 断言实际返回值与预期值是否相等
        assertEquals("hello mockito1", s);
    }

    /**
     * 测试当内部服务抛出异常时的处理
     * 该测试方法旨在验证当innerService的testEx方法抛出异常时，是否正确地被测试代码捕获并处理
     * 使用Mockito框架来模拟innerService的行为，并定义当调用testEx方法时（无论参数是什么），都将抛出Exception
     * 然后，测试通过assertThrows方法验证，调用innerService的testEx方法时是否会抛出RuntimeException
     */
    @Test
    public void testException() {
        // 使用Mockito框架设置当调用innerService的testEx方法时抛出Exception
        Mockito.when(innerService.testEx(Mockito.any())).thenThrow(Exception.class);

        // 验证调用testEx方法时是否会抛出RuntimeException
        assertThrows(RuntimeException.class, () -> {
            innerService.testEx("");
        });
    }


    @Autowired
    private MockMvc mockMvc;

    /**
     * 测试MockMvc的功能
     * 该测试方法用于验证通过MockMvc发起的GET请求是否能正确接收并返回预期的响应
     * 主要用于确保控制器层的接口按预期工作，对于测试Web应用的连通性和基本功能非常有用
     *
     * @throws Exception 如果在模拟请求过程中发生错误，则可能抛出此异常
     */
    @Test
    public void tesMockMvc() throws Exception {
        mockMvc.perform(get("/ping"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello mockMvc"));
    }

}
