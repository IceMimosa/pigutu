#!/usr/bin/env bash

## 将 application.yml.example 拷贝一份为 application.yml
for i in `find . -type f -name 'application.yml.example'`
do
    cp -n $i `echo $i | sed -e 's/.example//'`
done