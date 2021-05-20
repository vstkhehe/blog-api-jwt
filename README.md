# blog-api-jwt
Blog de posts com autenticação JWT

API desenvolvida em Java 11.

SUBIR A APLICAÇÃO SPRINGBOOT E UTILIZAR PELO POSTMAN.
NECESSÁRIO TER O POSTGRE INSTALADO NA MÁQUINA.

1-Criar usuário.
2-Logar com o usuário e anotar o token gerado. Ir na aba "Authorization e selecionar o type para
  Bearer Token. Copiar e colar o token gerado no campo ao lado.
3-Configurar a aba "Headers" com Key = Content-Type, Value = application/json.

Obs: Para trocar de usuário, deve se reiniciar a aplicação e logar com o usuário diferente.

Criar um novo usuario:
POST - http://localhost:8080/api/auth/signup
JSON:
{
    "usuario":"(String)",
    "senha":"(String)"
}


Logar no sistema:
POST - http://localhost:8080/api/auth/signin
JSON:
{
    "usuario":"(String)",
    "senha":"(String)"
}

Consultar todos os post:
GET - http://localhost:8080/api/posts

Buscar por um post:
GET - http://localhost:8080/api/posts/(id do post)

Criar um novo post:
POST - http://localhost:8080/api/newpost
JSON:
    {
        "titulo": "(String)",
        "link": "(String)",
        "data": "dd-mm-yyyy",
        "texto": "(String)",
        "imagemNome": "(String)",
        "imagemTipo": "(String)",
        "imagemTamanho": "(String)"
     }

Adicionar um comentário:
PUT - http://localhost:8080/api/post/(id do post)
JSON:
     {
        "comentario":"(String)"
      }

Excluir um post 
DELETE - http://localhost:8080/api/post
JSON:
    {
    	"id":"(String)"
    }

Excluir um comentário:
DELETE - http://localhost:8080/api/comentario
JSON:
    {
    	"id"(id do comentario):"(String)"
    }

Consultar todos os albums:
GET - http://localhost:8080/api/albums

Buscar por um post:
GET - http://localhost:8080/api/album/(id do album)	
	
Criar um novo album:
POST - http://localhost:8080/api/newalbum
JSON:
    {
        "nomeAlbum": "(String)"
     }
	 
Adicionar uma foto:
PUT - http://localhost:8080/api/album/(id do album)
JSON:
     {
		"imagemNome": "(String)",
		"imagemTipo": "(String)",
		"imagemTamanho": "(String)"
	}

Excluir um album 
DELETE - http://localhost:8080/api/album
JSON:
    {
    	"id":"(String)"
    }

Excluir uma foto:
DELETE - http://localhost:8080/api/foto
JSON:
    {
    	"id"(id da foto):"(String)"
    }
	