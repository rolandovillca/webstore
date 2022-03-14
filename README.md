# webstore

# Product Service APIs
## CREATE

HTTP method : POST
api : http://localhost:8081/products/

body: {
"product_no": "433452",
"name": "Nick",
"price": 60.9,
"description": "apple",
"no_in_stock": 89
}

## UPDATE
HTTP method : PUT
api : http://localhost:8081/products/{productId}

body: {
"name": "Nick",
"price": 60.9,
"description": "apple",
"no_in_stock": 90
}

## REMOVE
HTTP method : DELETE
api : http://localhost:8081/products/{productId}

## GET BY ID
HTTP method : GET
api : http://localhost:8081/products/{productId}

## GET ALL
HTTP method : GET
api : http://localhost:8081/products



