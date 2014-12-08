[![Build Status](https://travis-ci.org/crazysoftwarecoder/nGinxxer.svg?branch=master)](https://travis-ci.org/crazysoftwarecoder/nGinxxer)

nGinxxer is a configuration generator for nGinx.

Takes a JSON as input and outputs formatted nGinx configuration. Flexible JSON structure allows new directives to be added as nGinx matures without changing code.

## Programmatic API

nGinxxer.getNGinxConfigurationString(String jsonString)

## CLI
```
$ mvn clean compile assembly:single

java -jar target/nginxxer.jar <input_json> <output_json>
```

Example: java -jar target/nginxxer.jar src/test/resources/com/myseriousorganization/nginx/sample_input.json /tmp/nginx.conf

## Sample JSON format

https://raw.githubusercontent.com/crazysoftwarecoder/nGinxxer/master/src/test/resources/com/myseriousorganization/nginx/sample_input.json

which will output https://raw.githubusercontent.com/crazysoftwarecoder/nGinxxer/master/src/test/resources/com/myseriousorganization/nginx/sample_nginx.conf

## Why not Puppet?

As nGinx configuration matures, the puppet manifest and erb templates have to change to accomodate new configuration directives. Not scalable because nGinx is in a period of rapid growth right now.
