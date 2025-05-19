const Eureka = require('eureka-js-client').Eureka;

const eurekaClient = new Eureka({
    instance: {
        app: 'auth-service',
        hostName: 'auth-service',
        ipAddr: 'auth-service',
        port: {
            $: 4004,
            '@enabled': true,
        },
        vipAddress: 'auth-service',
        dataCenterInfo: {
            '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
            name: 'MyOwn',
        },
        status: 'UP',
        healthCheckUrl: 'http://auth-service:4004/health',
    },
    eureka: {
        host: 'eureka-server',
        port: 8761,
        servicePath: '/eureka/apps/',
        maxRetries: 10,
        requestRetryDelay: 2000,
        heartbeatInterval: 30000,
        registryFetchInterval: 30000,
    },
});

module.exports = eurekaClient; 