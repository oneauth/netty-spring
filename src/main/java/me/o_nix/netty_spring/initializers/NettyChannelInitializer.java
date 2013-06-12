package me.o_nix.netty_spring.initializers;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

import java.util.LinkedHashSet;
import java.util.Set;

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
