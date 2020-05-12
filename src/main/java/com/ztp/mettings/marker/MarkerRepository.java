package com.ztp.mettings.marker;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MarkerRepository extends MongoRepository<MarkerEntity, String> {
}
