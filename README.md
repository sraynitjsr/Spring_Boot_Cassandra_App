# App Using Spring Boot and Cassandra DB

## Sample Curl Commands

### Get All Data
```bash
curl -X GET http://localhost:8080/api/my_table
```

### Get Data by ID
```bash
curl -X GET http://localhost:8080/api/my_table/{id}
```
Replace `{id}` with the UUID of the data we want to retrieve.

### Save Data
```bash
curl -X POST -H "Content-Type: application/json" -d '{"id": "d88f51a6-92f7-4e6d-b5b3-f0fd0d4a2d6f", "name": "John", "age": 30}' http://localhost:8080/api/my_table
```
This command creates a new entry with the specified UUID, name, and age.

### Delete Data by ID
```bash
curl -X DELETE http://localhost:8080/api/my_table/{id}
```
Replace `{id}` with the UUID of the data we want to delete.

### Get Names in Range of Ages
```bash
curl -X GET 'http://localhost:8080/api/my_table/namesInRange?minAge=20&maxAge=40'
```
This command retrieves the names of entries whose ages fall within the specified range of 20 to 40. Adjust the `minAge` and `maxAge` parameters as needed.
