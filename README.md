# blog-api-jwt
Blog de posts com autenticação JWT


SUBIR A APLICAÇÃO SPRINGBOOT E UTILIZAR PELO POSTMAN.
NECESSÁRIO TER O POSTGRE INSTALADO NA MÁQUINA.

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

Criar um novo post:
POST - http://localhost:8080/api/newpost
JSON:
    {
        "titulo": "(String)",
        "link": "(String)",
        "data": "(dd-mm-yyyy)",
        "texto": "(String)",
        "imagemNome": "(String)",
        "imagemTipo": "(String)",
        "imagemTamanho": "(String)"
     }

Adicionar um comentário:
PUT - http://localhost:8080/api/post/(id do post)
JSON:
     {
        "comentario":"(String)",
        "autor":"(String)"
      }

Excluir um post 
DELETE - http://localhost:8080/api/post/
JSON:
    {
    	"id":"(String)"
    }

Excluir um comentário:
DELETE - http://localhost:8080/api/deletarComentario/
JSON:
    {
    	"id":"(String)" (do comentario)
    }
