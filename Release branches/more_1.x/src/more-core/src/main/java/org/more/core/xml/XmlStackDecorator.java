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
package org.more.core.xml;
import java.util.HashMap;
import org.more.util.attribute.DecStackDecorator;
/**
 * 该类继承自{@link DecStackDecorator}装饰器，作用是提供了一个context对象的支持。
 * @version 2010-9-23
 * @author 赵永春 (zyc@byshell.org)
 */
public class XmlStackDecorator<T> extends DecStackDecorator<T> {
    private Object context = null;
    /**获取Context*/
    public Object getContext() {
        return context;
    }
    /**设置Context*/
    public void setContext(Object context) {
        this.context = context;
    }
    //---------------
    private HashMap<String, NameSpace> nameSpaceMap = new HashMap<String, NameSpace>();
    NameSpace getNameSpace(String prefix) {
        return this.nameSpaceMap.get(prefix);
    }
    void addNameSpace(String prefix, NameSpace nameSpace) {
        this.nameSpaceMap.put(prefix, nameSpace);
    }
}