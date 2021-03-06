/*-
 * #%L
 * Bobcat
 * %%
 * Copyright (C) 2018 Cognifide Ltd.
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
package com.cognifide.qa.bb.aem.core.siteadmin.internal;

import com.cognifide.qa.bb.qualifier.PageObjectInterface;

/**
 * Represents the template list in Siteadmin in AEM authoring
 */
@PageObjectInterface
public interface TemplateList {

  /**
   * Select the provided template
   *
   * @param templateName name of the template to be selected
   */
  void selectTemplate(String templateName);
}
