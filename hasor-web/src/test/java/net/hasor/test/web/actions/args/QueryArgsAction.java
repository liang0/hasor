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
package net.hasor.test.web.actions.args;
import net.hasor.web.annotation.Any;
import net.hasor.web.annotation.QueryParameter;

import java.util.HashMap;
import java.util.Map;

public class QueryArgsAction {
    @Any
    public Map<String, Object> execute(//
            @QueryParameter("byteParam") byte byteParam, //
            @QueryParameter("intParam") int intParam,    //
            @QueryParameter("strParam") String strParam, //
            @QueryParameter("") String eptParam          //
    ) {
        return new HashMap<String, Object>() {{
            put("byteParam", byteParam);
            put("intParam", intParam);
            put("strParam", strParam);
            put("eptParam", eptParam);
        }};
    }
}