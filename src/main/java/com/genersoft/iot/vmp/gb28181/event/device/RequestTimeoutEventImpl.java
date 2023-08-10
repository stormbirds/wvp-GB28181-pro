package com.genersoft.iot.vmp.gb28181.event.device;

import com.genersoft.iot.vmp.gb28181.bean.Device;
import com.genersoft.iot.vmp.gb28181.event.SipSubscribe;
import com.genersoft.iot.vmp.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.sip.ClientTransaction;
import javax.sip.address.SipURI;
import javax.sip.header.CallIdHeader;
import javax.sip.header.ToHeader;
import javax.sip.message.Request;
import java.util.List;

/**
 * @author lin
 */
@Component
public class RequestTimeoutEventImpl implements ApplicationListener<RequestTimeoutEvent> {

    @Autowired
    private IDeviceService deviceService;

    @Override
    public void onApplicationEvent(RequestTimeoutEvent event) {
        ClientTransaction clientTransaction = event.getTimeoutEvent().getClientTransaction();
        if (clientTransaction != null) {
            Request request = clientTransaction.getRequest();
            if (request != null) {
                String host = ((SipURI) request.getRequestURI()).getHost();
                int port = ((SipURI) request.getRequestURI()).getPort();
                List<Device> devices = deviceService.getDeviceByHostAndPort(host, port);
                if (devices == null || devices.isEmpty()) {
                    return;
                }
                devices.forEach(device -> deviceService.offline(device.getDeviceId()));
            }
        }
    }
}
