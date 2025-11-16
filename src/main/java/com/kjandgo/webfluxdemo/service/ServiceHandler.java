package com.kjandgo.webfluxdemo.service;

import com.kjandgo.webfluxdemo.repository.ServiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class ServiceHandler {
    private final ServiceRepository serviceRepository;

    public ServiceHandler(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Mono<ServerResponse> getService(ServerRequest request) {
        return serviceRepository.findById(request.pathVariable("id"))
                .flatMap(service -> ServerResponse.ok().body(BodyInserters.fromValue(service)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getServices(ServerRequest request) {
        return ServerResponse.ok().body(serviceRepository.findAll(), Service.class);
    }

//    public Mono<ServerResponse> createService(ServerRequest request) {
//        return request.bodyToMono(Service.class)
//                .flatMap(service -> serviceRepository.save(service))
//                .flatMap(service -> ServerResponse.created(URI.create("/service/" + service.getId())).build());
//    }
//
//    public Mono<ServerResponse> updateService(ServerRequest request) {
//        return request.bodyToMono(Service.class)
//                .flatMap(service -> serviceRepository.findById(request.pathVariable("id"))
//                        .flatMap(existingService -> {
//                            existingService.setName(service.getName());
//                            existingService.setDescription(service.getDescription());
//                            return serviceRepository.save(existingService);
//                        }))
//                .flatMap(service -> ServerResponse.noContent().build())
//                .switchIfEmpty(ServerResponse.notFound().build());
//    }
//
//    public Mono<ServerResponse> deleteService(ServerRequest request) {
//        return serviceRepository.deleteById(request.pathVariable("id"))
//                .flatMap(result -> ServerResponse.noContent().build())
//                .switchIfEmpty(ServerResponse.notFound().build());
//    }
}
