/*
   Copyright 2016 Jianan - qinxiandiqi@foxmail.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.qinxiandiqi.androiddemo;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by Jianan on 2016/07/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class MockJUnitTest {

    private final String APP_NAME = "JUnit Demo";

    @Mock
    Context mContext;

    @Test
    public void testString(){
        when(mContext.getString(R.string.app_name))
                .thenReturn(APP_NAME);

        String name = mContext.getString(R.string.app_name);

        assertEquals(name, APP_NAME);
    }
}
