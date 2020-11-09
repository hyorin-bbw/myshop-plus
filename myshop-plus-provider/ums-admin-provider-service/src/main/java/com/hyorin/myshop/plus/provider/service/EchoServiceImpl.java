package com.hyorin.myshop.plus.provider.service;

import com.hyorin.myshop.provider.api.service.EchoService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String string) {
        return "echo hello dubbo" + string;
    }
}
