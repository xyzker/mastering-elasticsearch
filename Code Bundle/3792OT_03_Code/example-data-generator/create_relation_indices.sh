#!/bin/bash

# This script creates 4 indices for testing the concept of various relationship model in Elasticsearch.
# It loads 10000 example documents for each of these indices.

curl -XDELETE localhost:9200/rel_natural
curl -XDELETE localhost:9200/rel_nested
curl -XDELETE localhost:9200/rel_pch_m
curl -XDELETE localhost:9200/rel_pch_s

curl -XPUT localhost:9200/rel_natural -d '{ "settings" : { "number_of_replicas" : 0 } }'
curl -XPUT localhost:9200/rel_nested -d '{ "settings" : { "number_of_replicas" : 0 } }'
curl -XPUT localhost:9200/rel_pch_m -d '{ "settings" : { "number_of_replicas" : 0, "number_of_shards" : 5 } }'
curl -XPUT localhost:9200/rel_pch_s -d '{ "settings" : { "number_of_replicas" : 0, "number_of_shards" : 5 } }'

curl -XPOST localhost:9200/rel_natural/book/_mapping?pretty -d '{
	"book" : {
		"properties" : {
			"title" : { "type": "string" },
			"quantity" : { "type": "integer" },
			"edition" : {
				"type" : "object",
				"properties" : {
					"isbn" : { "type" : "string", "index" : "not_analyzed" },
					"circulation" : { "type" : "integer" }
				}
			}
		}
	}
}'

curl -XPOST localhost:9200/rel_nested/book/_mapping?pretty -d '{
	"book" : {
		"properties" : {
			"title" : { "type": "string" },
			"quantity" : { "type": "integer" },
			"edition" : {
				"type" : "nested",
				"properties" : {
					"isbn" : { "type" : "string", "index" : "not_analyzed" },
					"circulation" : { "type" : "integer" }
				}
			}
		}
	}
}'

curl -XPOST localhost:9200/rel_pch_m/book/_mapping?pretty -d '{
	"book" : {
		"properties" : {
			"title" : { "type": "string" },
			"quantity" : { "type": "integer" }
		}
	}
}'

curl -XPOST localhost:9200/rel_pch_s/edition/_mapping?pretty -d '{
	"edition" : {
		"_parent" : {
			"type" : "book"
		},
		"properties" : {
			"isbn" : { "type" : "string", "index" : "not_analyzed" },
			"circulation" : { "type" : "integer" }
		}
	}
}'

$(sh ./generate_data.sh > data.txt)

curl -XPOST localhost:9200/_bulk?pretty --data-binary @data.txt