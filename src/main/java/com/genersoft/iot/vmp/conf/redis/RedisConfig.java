package com.genersoft.iot.vmp.conf.redis;

import com.alibaba.fastjson.parser.ParserConfig;
import com.genersoft.iot.vmp.common.VideoManagerConstants;
import com.genersoft.iot.vmp.service.impl.*;
import com.genersoft.iot.vmp.skyeye.redis.listener.RedisKeyDeleteEventMessageListener;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.ReadFrom;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.genersoft.iot.vmp.utils.redis.FastJsonRedisSerializer;

import javax.annotation.Resource;
import java.time.Duration;


/**
 * @description:Redis中间件配置类，使用spring-data-redis集成，自动从application.yml中加载redis配置
 * @author: swwheihei
 * @date: 2019年5月30日 上午10:58:25
 * 
 */
@Slf4j
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

	@Autowired
	private RedisGpsMsgListener redisGPSMsgListener;

	@Autowired
	private RedisAlarmMsgListener redisAlarmMsgListener;

	@Autowired
	private RedisStreamMsgListener redisStreamMsgListener;

	@Autowired
	private RedisGbPlayMsgListener redisGbPlayMsgListener;

	@Autowired
	private RedisPushStreamStatusMsgListener redisPushStreamStatusMsgListener;

	@Autowired
	private RedisPushStreamListMsgListener redisPushStreamListMsgListener;

	@Autowired
	private RedisKeyDeleteEventMessageListener redisKeyDeleteEventMessageListener;


	@Resource
	private RedisProperties redisProperties;
	@Resource
	private LettuceConnectionFactory lettuceConnectionFactory;
//	public GenericObjectPoolConfig<?> genericObjectPoolConfig(RedisProperties.Pool properties) {
//		GenericObjectPoolConfig<?> config = new GenericObjectPoolConfig<>();
//		config.setMaxTotal(properties.getMaxActive());
//		config.setMaxIdle(properties.getMaxIdle());
//		config.setMinIdle(properties.getMinIdle());
//		if (properties.getTimeBetweenEvictionRuns() != null) {
//			config.setTimeBetweenEvictionRuns(Duration.ofMillis(properties.getTimeBetweenEvictionRuns().toMillis()) );
//		}
//		if (properties.getMaxWait() != null) {
//			config.setMaxWait(Duration.ofSeconds(properties.getMaxWait().getSeconds()) );
//		}
//		return config;
//	}

//	@Bean
//	public DefaultClientResources lettuceClientResources() {
//		return DefaultClientResources.create();
//	}
//
//	@Bean(destroyMethod = "destroy")
//	public LettuceConnectionFactory lettuceConnectionFactory(ClientResources clientResources) {
//		//redis配置
//		RedisConfiguration redisConfiguration = new
//				RedisStandaloneConfiguration(redisProperties.getHost(),redisProperties.getPort());
//		((RedisStandaloneConfiguration) redisConfiguration).setDatabase(redisProperties.getDatabase());
//		((RedisStandaloneConfiguration) redisConfiguration).setPassword(redisProperties.getPassword());
//
//		//连接池配置
//		GenericObjectPoolConfig genericObjectPoolConfig =
//				new GenericObjectPoolConfig();
//
//		//redis客户端配置
//		LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder
//				builder =  LettucePoolingClientConfiguration.builder().
//				commandTimeout(Duration.ofSeconds(redisProperties.getTimeout().getSeconds()));
//
//		builder.shutdownTimeout(Duration.ofSeconds(redisProperties.getTimeout().getSeconds()));
//		builder.poolConfig(genericObjectPoolConfig);
//		LettuceClientConfiguration lettuceClientConfiguration = builder.build();
//
//		//根据配置和客户端配置创建连接
//		LettuceConnectionFactory lettuceConnectionFactory = new
//				LettuceConnectionFactory(redisConfiguration,lettuceClientConfiguration);
//		lettuceConnectionFactory .afterPropertiesSet();
//
//		return lettuceConnectionFactory;
//	}


	@Bean
	public RedisTemplate<Object, Object> redisTemplate() {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		// 使用fastJson序列化
		FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
		// value值的序列化采用fastJsonRedisSerializer
		redisTemplate.setValueSerializer(fastJsonRedisSerializer);
		redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
		// 全局开启AutoType，不建议使用
		 ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
		// 建议使用这种方式，小范围指定白名单，需要序列化的类
//		ParserConfig.getGlobalInstance().addAccept("com.avatar");
		// key的序列化采用StringRedisSerializer
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setConnectionFactory(lettuceConnectionFactory);
		return redisTemplate;
	}


	/**
	 * redis消息监听器容器 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
	 * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
	 * 
	 * @param connectionFactory
	 * @return
	 */
	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
		container.addMessageListener(redisGPSMsgListener, new PatternTopic(VideoManagerConstants.VM_MSG_GPS));
		container.addMessageListener(redisAlarmMsgListener, new PatternTopic(VideoManagerConstants.VM_MSG_SUBSCRIBE_ALARM_RECEIVE));
		container.addMessageListener(redisStreamMsgListener, new PatternTopic(VideoManagerConstants.WVP_MSG_STREAM_CHANGE_PREFIX + "PUSH"));
		container.addMessageListener(redisGbPlayMsgListener, new PatternTopic(RedisGbPlayMsgListener.WVP_PUSH_STREAM_KEY));
		container.addMessageListener(redisPushStreamStatusMsgListener, new PatternTopic(VideoManagerConstants.VM_MSG_PUSH_STREAM_STATUS_CHANGE));
		container.addMessageListener(redisPushStreamListMsgListener, new PatternTopic(VideoManagerConstants.VM_MSG_PUSH_STREAM_LIST_CHANGE));

		container.addMessageListener(redisKeyDeleteEventMessageListener,new PatternTopic("__keyevent@*__:del"));
		container.addMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message, byte[] bytes) {
				log.info("device {}",message.toString());
			}
		}, new ChannelTopic("device"));
		container.addMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message, byte[] bytes) {
				log.info("alarm {}",message.toString());
			}
		}, new ChannelTopic( "alarm" ));
		container.addMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message, byte[] bytes) {
				log.info("mobileposition {}",message.toString());
			}
		}, new ChannelTopic( "mobileposition" ));
        return container;
    }

}
