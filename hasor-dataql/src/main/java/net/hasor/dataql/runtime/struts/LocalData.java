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
package net.hasor.dataql.runtime.struts;
import java.util.Stack;
/**
 * DS，用于在处理 ASA ASM ASE 阶段内，处理 ROU 指令时寻值使用。
 * @author 赵永春(zyc@hasor.net)
 * @version : 2017-07-19
 */
public class LocalData {
    private Stack<Object> dsStack = new Stack<Object>();
    //
    public void pushData(Object obj) {
        this.dsStack.push(obj);
    }
    public void popData() {
        this.dsStack.pop();
    }
    public Object getData() {
        return this.dsStack.empty() ? null : this.dsStack.peek();
    }
}