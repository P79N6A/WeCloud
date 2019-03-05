#!/usr/bin/env python
#-*- coding:utf-8 -*-
__author__ = 'iambocai'

import urllib2
import requests
import json
import time
import socket
import os
import re
import telnetlib
import sys
import commands

def main():
    today = time.strftime("%Y.%m.%d", time.gmtime())
    url = 'http://elk-es-6.cluster.koolearn.com/nginx_access_log_' + today + '/_search'

    now = time.time();
    now_int = int(now) * 1000
    #
    before_minute = now_int - 1000 * 30
    data = []
    query_data = {
        "size": 0,
        "query": {
            "bool": {
                 "must": {
                      "query_string": {
                           "query": "status:[0 TO 1000] AND (server_name:www.koo.cn OR server_name:weixin.koo.cn OR server_name:m.koo.cn )",
                           "analyze_wildcard": "true",
                           "minimum_should_match": "100%"
                      }
                 },
      "filter": {
        "bool": {
          "must": [
            {
              "range": {
                "@timestamp": {
                  "gte": before_minute,
                  "lte": now_int,
                  "format": "epoch_millis"
                }
              }
            }
          ]
        }
      }
    }
  },
  "aggs": {
    "2": {
      "terms": {
        "field": "server_name",
        "size": 20
      },
      "aggs": {
        "3": {
          "terms": {
            "field": "status",
            "size": 5
            }
          }
        }
      }
    }
  }
    query_data = json.dumps(query_data)
    headers = {"Content-Type": "application/json; charset=UTF-8"}
    response = requests.get(url=url, headers=headers, data=query_data)
    result = response.text
    response.close()

    resultJson = json.loads(result)
    buckets = resultJson["aggregations"]["2"]["buckets"]
    
    for each_bucket in buckets:
        http_stats={}
        status_200 = 0
        status_3xx = 0
        status_4xx = 0
        status_5xx = 0
        total = each_bucket["doc_count"]
        domain = each_bucket["key"]
        status_each_bucket = each_bucket["3"]["buckets"]
        for each_status in status_each_bucket:
            status = each_status["key"]
            status_count=each_status["doc_count"]
            if int(status) == 200 :
               status_200 += status_count
            elif int(status) >= 300 and int(status) < 400:
               status_3xx += status_count
            elif int(status) >= 400 and int(status) < 500:
               status_4xx += status_count
            elif int(status) >= 500 and int(status) < 600:
               status_5xx += status_count
        if int(total) == 0 :
            total = 1

        falcon_data = {
            "metric": "nginx.http.status.200.ratio",
            "endpoint": "koo-http-status",
            "timestamp": int(now),
            "step": 30,
            "value": float(status_200)/float(total)*100,
            "counterType": "GAUGE",
            "tags": "domain="+ domain
        }
        data.append(falcon_data)
        
        falcon_data = {
            "metric": "nginx.http.status.3xx.ratio",
            "endpoint": "koo-http-status",
            "timestamp": int(now),
            "step": 30,
            "value": float(status_3xx)/float(total)*100,
            "counterType": "GAUGE",
            "tags": "domain="+ domain
        }
        data.append(falcon_data)
        ratio_4xx = float(status_4xx)/float(total)*100
	if total <= 10 :
            ratio_4xx = 0;
        falcon_data = {
            "metric": "nginx.http.status.4xx.ratio",
            "endpoint": "koo-http-status",
            "timestamp": int(now),
            "step": 30,
            "value": ratio_4xx,
            "counterType": "GAUGE",
            "tags": "domain="+ domain
        }
        data.append(falcon_data)
       
        falcon_data = {
            "metric": "nginx.http.status.5xx.ratio",
            "endpoint": "koo-http-status",
            "timestamp": int(now),
            "step": 30,
            "value": float(status_5xx)/float(total)*100,
            "counterType": "GAUGE",
            "tags": "domain="+ domain
        }
        data.append(falcon_data)
    return data


if __name__ == '__main__':
    proc = commands.getoutput(''' ps -ef|grep '30_sharks_http_status.py'|grep -v grep|wc -l ''')
    if int(proc) < 3:
        print json.dumps(main())

