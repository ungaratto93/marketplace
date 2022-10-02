Para executar esse projeto voce precisa possuir:
- JDK e JRE mais recente
- IDE Eclipse 

Siga esses passos:
 - Clone o projeto em sua maquina. 
 - Importe para o Eclipse. 
 - Execute a classe MarketplaceApplication
 - No seu prompt de comando envie essa requisicao
    curl --request POST \
  --url http://localhost:8080/products/buy \
  --header 'Content-Type: application/json' \
  --data '{
	"name": "teste",
	"code": "1",
	"price": 500.00,
	"payment": {
		"downPayment": 100.00,
		"installments": 24
	}
}'


