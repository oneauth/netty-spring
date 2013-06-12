/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package me.o_nix.netty_spring.initializers;

import java.util.LinkedHashSet;
import java.util.Set;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public class NettyChannelInitializer<T extends Channel> extends ChannelInitializer<T> {
	private Set<ChannelHandler> handlers = new LinkedHashSet<>();

	public Set<ChannelHandler> getHandlers() {
		return handlers;
	}

	public void setHandlers(Set<ChannelHandler> handlers) {
		this.handlers = handlers;
	}

    @Override
    public void initChannel(T socketChannel) throws Exception {
		final ChannelPipeline pipeline = socketChannel.pipeline();

		for (ChannelHandler handler : handlers)
			pipeline.addLast().addLast(handler);
    }
}
