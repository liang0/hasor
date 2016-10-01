/*
 * Copyright 2008-2009 the original author or authors.
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
package net.hasor.restful.invoker;
import net.hasor.restful.Validation;
import net.hasor.restful.api.Valid;
import net.hasor.restful.api.ValidBy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/**
 * 线程安全
 * @version : 2016-08-03
 * @author 赵永春 (zyc@hasor.net)
 */
class InnerValid {
    private Map<String, Valid>    paramValidMap = null;
    private Map<String, Class<?>> paramTypeMap  = null;
    InnerValid(Map<String, Valid> paramValidMap, Map<String, Class<?>> paramTypeMap) {
        this.paramValidMap = paramValidMap;
        this.paramTypeMap = paramTypeMap;
    }
    //
    Map<String, ValidData> doValid(InnerRenderData renderData, Object[] resolveParams) {
        ConcurrentMap<String, ValidData> validDateMap = new ConcurrentHashMap<String, ValidData>();
        for (String paramIndex : this.paramValidMap.keySet()) {
            Valid paramValid = this.paramValidMap.get(paramIndex);
            if (paramValid == null) {
                continue;
            }
            final String validName = paramValid.value();
            Class<?> paramType = this.paramTypeMap.get(paramIndex);
            ValidBy validBy = paramType.getAnnotation(ValidBy.class);
            if (validBy == null) {
                onErrors(validDateMap, new ValidData(validName, "@ValidBy is Undefined."));
                continue;
            }
            Class<? extends Validation>[] validArrays = validBy.value();
            Validation[] validObjects = new Validation[validArrays.length];
            for (int i = 0; i < validArrays.length; i++) {
                validObjects[i] = renderData.getAppContext().getInstance(validArrays[i]);
            }
            valid(renderData, resolveParams[Integer.valueOf(paramIndex)], validDateMap, validName, validObjects);
        }
        return validDateMap;
    }
    private static void onErrors(ConcurrentMap<String, ValidData> validDateMap, ValidData newDate) {
        if (newDate == null) {
            return;
        }
        validDateMap.putIfAbsent(newDate.getKey(), newDate);
        ValidData data = validDateMap.get(newDate.getKey());
        if (data != newDate) {
            data.addAll(newDate);
        }
    }
    private static void valid(InnerRenderData renderData, Object resolveParam, final ConcurrentMap<String, ValidData> validDateMap, String validName, Validation[] validObjects) {
        // ---
        for (Validation valid : validObjects) {
            if (valid == null) {
                onErrors(validDateMap, new ValidData(validName, "program is not exist."));
                continue;
            }
            valid.doValidation(validName, resolveParam, new InnerValidErrors(renderData) {
                protected void errors(ValidData messages) {
                    onErrors(validDateMap, messages);
                }
            });
        }
        // ---
    }
}