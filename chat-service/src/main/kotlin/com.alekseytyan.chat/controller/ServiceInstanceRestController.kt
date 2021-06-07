package com.alekseytyan.chat.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.ServiceInstance
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
internal class ServiceInstanceRestController {

    @Autowired
    private val discoveryClient: DiscoveryClient? = null

    @RequestMapping("/service-instances/{applicationName}")
    fun serviceInstancesByApplicationName(@PathVariable applicationName: String?): List<ServiceInstance> {
        return discoveryClient!!.getInstances(applicationName)
    }
}