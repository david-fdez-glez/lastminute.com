package org.dfernandez.lastminute.com.service;

import org.dfernandez.lastminute.com.dto.SearchRequest;
import org.dfernandez.lastminute.com.dto.SearchResponse;

import java.util.List;

interface SearchEngine {

    List<SearchResponse> search(SearchRequest request);
}
