/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zuoxiaolong.niubi.job.service.impl;

import com.zuoxiaolong.niubi.job.test.zookeeper.ZookeeperServerCluster;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Xiaolong Zuo
 * @since 0.9.4.2
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-service-test.xml"})
@Transactional
public abstract class AbstractSpringContextTest implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @BeforeClass
    public static void setup() {
        ZookeeperServerCluster.startZookeeperCluster();
    }

    @AfterClass
    public static void teardown() {
        ZookeeperServerCluster.stopZookeeperCluster();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    protected String getSampleJarFile() {
        System.out.println(ClassLoader.getSystemResource("").getFile());
        System.out.println(AbstractSpringContextTest.class.getClassLoader().getSystemResource("").getFile());
        System.out.println(MasterSlaveJobLogServiceImplTest.class.getClassLoader().getSystemResource("").getFile());
        System.out.println(applicationContext.getClassLoader().getSystemResource("").getFile());
        System.out.println(ClassLoader.getSystemResource("niubi-job-sample-spring.jar").getFile());
        System.out.println(AbstractSpringContextTest.class.getClassLoader().getSystemResource("niubi-job-sample-spring.jar").getFile());
        System.out.println(MasterSlaveJobLogServiceImplTest.class.getClassLoader().getSystemResource("niubi-job-sample-spring.jar").getFile());
        System.out.println(applicationContext.getClassLoader().getSystemResource("niubi-job-sample-spring.jar").getFile());
        return ClassLoader.getSystemResource("niubi-job-sample-spring.jar").getFile();
    }

}
