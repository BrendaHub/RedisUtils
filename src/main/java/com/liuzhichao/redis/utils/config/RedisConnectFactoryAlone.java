package com.liuzhichao.redis.utils.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import javax.annotation.Resource;

/**

* 类说明   单机配置
*/
@Configuration
@ConditionalOnProperty(prefix = "edw.sdp.spring.redis", name = "isCluster", havingValue = "false")
public class RedisConnectFactoryAlone {

//	@Autowired
	@Resource
	private RedisConfig redisConfig;
	
//	@Autowired
//	private RedisServer redisServer;
	
//	/**
//	 * 单机配置
//	 * @param jedisPoolConfig
//	 * @return
//	 */
//	@Bean
//	public JedisConnectionFactory jedisConnectionFactory() {
//		JedisConnectionFactory jedisConFactory =
//				new JedisConnectionFactory(redisServer.jedisPoolConfig());
//		jedisConFactory.setHostName(redisConfig.getHostName());
//		jedisConFactory.setPort(redisConfig.getPort());
//		jedisConFactory.setPassword(redisConfig.getPassword());
//		jedisConFactory.setTimeout(redisConfig.getTimeout());
//		return jedisConFactory;
//	}

	// 单机配置， 新版配置
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisConfig.getHostName(),
																											redisConfig.getPort());
		redisStandaloneConfiguration.setPassword(RedisPassword.of(redisConfig.getPassword()));
		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}
	
}
