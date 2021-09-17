# Subscription demo app

This is a demo app build using Spring Boot. It exposes a series of endpoints
that demonstrates how an user can create a subscription at a specific service.
The subscription is made from a series of products and a plan.
An user can have only one subscription at a time.
The app runs by default on localhost:8080 and uses an in memory H2 database
Demo user, products and plans are inserted in db at start through data.sql file.

## API usage
### Getting the available products and plans

GET: /api/v1/subscriptions/options

Response example:

{
    "plans": [
        {
            "id": 1,
            "planType": "MONTHLY",
            "discount": 0,
            "name": "MONTHLY PLAN"
        }
    ],
    "products": [
        {
            "id": 1,
            "name": "Product1",
            "description": "Product1 Description",
            "price": 10
        }
    ]
}

### Creating a subscription

POST: /api/v1/subscriptions

Request body example(all fields are mandatory):

{
	"planId":1,
	"products":[2,3]
}

### Deleting a subscription

DELETE /api/v1/subscriptions/delete/user/{userId}

### Getting subscriptions per user

GET /api/v1/subscriptions/user/{userId}

### Updating existent subscription

PUT /api/v1/subscriptions

Request body example(all fields are mandatory):

{
	"planId":1,
	"products":[2,3]
}
