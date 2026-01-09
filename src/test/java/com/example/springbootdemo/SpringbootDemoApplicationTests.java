package com.example.springbootdemo;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Properties;

@SpringBootTest
class SpringbootDemoApplicationTests {

    @Test
    void contextLoads() {
    }



    public static void main(String[] args) throws NacosException {
        // 测试默认地址
        testNacosConnectivity("47.116.193.3:8848");
    }

    public static void testNacosConnectivity(String serverAddr) throws NacosException {
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        properties.put("username", "nacos");
        properties.put("password", "nacos");
        properties.put("namespace", "public");

        try {
            ConfigService configService = NacosFactory.createConfigService(properties);

            // 尝试获取一个简单的测试配置来验证连通性
            String testKey = "springboot-demo.properties";
            String testGroup = "SPRING_BOOT_DEMO_GROUP";
            String content = configService.getConfig(testKey, testGroup, 30000);

            System.out.println("Nacos连接测试成功！" + content);
            System.out.println("服务器地址: " + serverAddr);

        } catch (NacosException e) {
            System.err.println("Nacos连接测试失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
