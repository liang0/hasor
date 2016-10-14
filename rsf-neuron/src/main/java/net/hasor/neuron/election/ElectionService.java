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
package net.hasor.neuron.election;
import net.hasor.rsf.RsfMessage;
import net.hasor.rsf.RsfResult;
/**
 * 选举接口
 *
 * @version : 2016年09月10日
 * @author 赵永春(zyc@hasor.net)
 */
@RsfMessage
public interface ElectionService {
    /** 请求选票 */
    public RsfResult requestVote(CollectVoteData voteData);

    /** 返回投票 */
    public RsfResult responseVote(CollectVoteResult voteData);
    
    
}