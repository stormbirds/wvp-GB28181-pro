package com.genersoft.iot.vmp.conf;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
@ConfigurationProperties(prefix = "sip", ignoreInvalidFields = true)
public class SipConfig {

	private String ip;

	private Integer port;

	private String domain;

	private String id;

	private String password;
	
	Integer ptzSpeed = 50;

	Integer registerTimeInterval = 120;

	private boolean alarm;

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPtzSpeed(Integer ptzSpeed) {
		this.ptzSpeed = ptzSpeed;
	}


	public void setRegisterTimeInterval(Integer registerTimeInterval) {
		this.registerTimeInterval = registerTimeInterval;
	}

	public String getIp() {
		return ip;
	}


	public Integer getPort() {
		return port;
	}


	public String getDomain() {
		return domain;
	}


	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}


	public Integer getPtzSpeed() {
		return ptzSpeed;
	}

	public Integer getRegisterTimeInterval() {
		return registerTimeInterval;
	}

	public boolean isAlarm() {
		return alarm;
	}

	public void setAlarm(boolean alarm) {
		this.alarm = alarm;
	}

	public void getLocalIp(String deviceLocalIp) {
		if (ObjectUtils.isEmpty(deviceLocalIp)) {

		}
	}
}
