# Before Running
- Have docker installed and running
  Create rabbit-mq container if not created before
  docker run -it --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management

  - Start the container
  - docker start rabbitmq
  
- DB Configuration

  - spring.datasource.url=jdbc:postgresql://localhost:5432/products?currentSchema=public&ssl=false
  - spring.datasource.username=postgres
  - spring.datasource.password=1234

- End Points
  - (POST) Add Product for Stock-> localhost:4445/product
    - {
  "productId":9,
  "productName": "Product 9",
  "quantity":10,
  "price": 140
}
  - (PUT) Update Product Price-> localhost:4445/product
    - {
  "productId":4,
  "newPrice":65
  }
  