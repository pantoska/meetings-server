package com.ztp.mettings.event;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<EventEntity, String> {
}
