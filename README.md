# uaa
spring cloud uaa 


http://localhost:9001/uaa/oauth/authorize?response_type=code&client_id=foo&redirect_uri=http://localhost:9001/uaa/response

curl -XPOST -k foo:bar@localhost:9001/uaa/oauth/token  -d grant_type=authorization_code  -d code={} -d redirect_uri=http://localhost:9001/uaa/response  

curl -H "Authorization: Bearer {}" http://localhost:9001/uaa/car/1
