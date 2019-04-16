#!/bin/bash
Indexs=`curl -XGET -s http://elk-es-6.cluster.koolearn.com/_cat/indices |grep open|awk '{print $3}'|grep -E '(20[0-9]{2}\.[0-9]{1,2}\.[0-9]{1,2})$'`
if [ -n "$Indexs" ]; then
    flag=`date -d "-7 day" +"%Y.%m.%d"`
    arr=()
    for ind in $Indexs ; do
        temp="${ind:0-10}"
        if [[ "$temp" < "$flag" ]]; then
            len="${#arr[*]}"
            arr[len]="$ind"
        fi
    done
fi
arrStr=${arr[*]}
echo "$arrStr"
`python mail.py "$arrStr"`
