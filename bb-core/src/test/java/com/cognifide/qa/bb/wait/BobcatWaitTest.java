/*-
 * #%L
 * Bobcat
 * %%
 * Copyright (C) 2016 Cognifide Ltd.
 * %%
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
 * #L%
 */
package com.cognifide.qa.bb.wait;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.cognifide.qa.bb.provider.selenium.webdriver.WebDriverProvider;

@ExtendWith(MockitoExtension.class)
public class BobcatWaitTest {

  @Mock
  private WebDriverProvider webDriverProvider;

  @Mock
  private WebDriver webDriver;

  @Mock
  private WebDriver.Options options;

  @Mock
  private WebDriver.Timeouts timeouts;

  @Mock
  private ExpectedCondition<Boolean> condition;

  @InjectMocks
  private BobcatWait tested;

  @BeforeEach
  public void setup() {
    when(webDriver.manage()).thenReturn(options);
    when(options.timeouts()).thenReturn(timeouts);
    when(timeouts.implicitlyWait(anyLong(), any())).thenReturn(timeouts);
    when(webDriverProvider.get()).thenReturn(webDriver);
  }

  @Test
  public void whenUntilIsInvokedImplicitTimeoutsShouldBeReducedThenRestored() {
    BobcatWait spied = spy(tested);
    InOrder inOrder = inOrder(spied);
    when(condition.apply(any())).thenReturn(true);

    spied.until(condition);

    inOrder.verify(spied).setImplicitTimeoutToNearZero();
    inOrder.verify(spied).getWebDriverWait();
    inOrder.verify(spied).restoreImplicitTimeout();
  }

  @Test
  public void isConditionMetShouldCatchTimeoutExceptionAndReturnBoolean() {
    when(condition.apply(any())).thenThrow(new TimeoutException());

    boolean result = true;
    try {
      result = tested.isConditionMet(condition);
    } catch (TimeoutException e) {
      fail("Exception should not be thrown");
    }
    assertThat(result).isFalse();
  }
}
