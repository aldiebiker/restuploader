# restuploader

## Package the application

```bash
git clone https://github.com/aldiebiker/restuploader.git
cd restuploader
mvn spring-boot:run
```

## Test the application
 
 ```bash
 curl -i -X POST -H "Content-Type: multipart/form-data"  -F "fileName=@2018.png"  http://localhost:8080/files/
 ```
 
 
 ## Limitation (configurable)
 Max file size 1MB 