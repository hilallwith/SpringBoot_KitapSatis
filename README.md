##spring boot seller

###endopoints

####sing up
...
POST /api/authentication/sign-up HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 75

{
"name":"user",
"username":"user",
"password":"user"
}
...

####sing in

...
POST /api/authentication/sign-in HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 75

{

"username":"user",
"password":"user"
}

...

####make admin

...
PUT /api/internal/make-admin/admin HTTP/1.1
Host: localhost:8080
Authorization: Bearer InternalApiKey1234!
...


####save book

...
POST /api/book HTTP/1.1
Host: localhost:8080
Authorization:
Content-Type: application/json
Content-Length: 120

{
"title":"Test Book 2",
"price":10,
"description":"Test description 2",
"author":"Test author 2"
}
...


####delete book

...
DELETE /api/book/2 HTTP/1.1
Host: localhost:8080
Authorization:
...

####get all books

...
GET /api/book HTTP/1.1
Host: localhost:8080
Authorization:
...


####get all books

...
GET /api/book HTTP/1.1
Host: localhost:8080

...


####save satış

...
POST /api/purchase-history HTTP/1.1
Host: localhost:8080

Content-Type: application/json
Content-Length: 56

{
"userId":3,
"bookId":1,
"price":10

}

...



####sget user satış

...
GET /api/purchase-history HTTP/1.1
Host: localhost:8080


}

...






