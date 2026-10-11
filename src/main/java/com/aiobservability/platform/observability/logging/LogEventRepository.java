package com.aiobservability.platform.observability.logging;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogEventRepository extends ElasticsearchRepository<LogEvent, String> {
}