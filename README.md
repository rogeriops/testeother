Para roda a aplicação

-mvn spring-boot:run

-a aplicação roda na porta 9000

#Console do banco de dados
http://localhost:9000/h2-console

#Upload de arquivo - POST
-localhost:9000/uploadFile 

#Status do processamento do arquivo - GET
localhost:9000/status/products_teste_webdev_leroy.xlsx

#Lista de todos produtos - GET
localhost:9000/

#Remover um produto - DELETE
localhost:9000/product/1001

#Consultar unico produto - GET
localhost:9000/product/1001

#Atualizar um produto - POST
localhost:9000/product/1001
Exemplo de json:
 {
  "im": 1001,
  "name": "Furadeira X",
  "free_shipping": 0,
  "description": "Furadeira eficiente X",
  "price": 100
 }
