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


# Customer Service APIs
Method GET ALL:

API: http://localhost:8888/api/v1/customer/all

# Method POST:
API: http://localhost:8888/api/v1/customer/add

{
"customerId": "c100",
"firstName": "John",
"lastName": "Smith",
"address":
{
"street": "1000 N 4th St",
"city": "Fairfield",
"zipcode": "5555555"
},
"phone": "641-111-2222",
"email": "user.mail.com"
}

# Method PUT:
API: http://localhost:8888/api/v1/customer/update

{
"customerId": "c100",
"firstName": "John Updated",
"lastName": "Smith",
"address":
{
"street": "1000 N 4th St",
"city": "Fairfield",
"zipcode": "7777777"
},
"phone": "641-111-2222",
"email": "user_updated@mail.com"
}

# Method DELETE:
API: http://localhost:8888/api/v1/customer/remove

{
"customerId": "c100",
"firstName": "John",
"lastName": "Smith",
"address":
{
"street": "1000 N 4th St",
"city": "Fairfield",
"zipcode": "5555555"
},
"phone": "641-111-2222",
"email": "user.mail.com"
}