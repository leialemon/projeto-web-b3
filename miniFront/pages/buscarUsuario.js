
    const btn = document.getElementById('buscarUser');
    if (btn) {
      btn.addEventListener('click', function(event) {
        event.preventDefault();
        const cpf = document.getElementById('bcpf').value;
        console.log('CPF a ser buscado:', cpf);
        if (!cpf) {
          document.getElementById('responseDiv').innerText = 'Por favor, insira um CPF.';
          return;
        }
  
        let url = 'http://localhost:8080/api/v1/users/cpf/' + cpf;
        console.log('Fazendo requisição para URL:', url);
        
        fetch(url, {
          method: 'GET'
        })
        .then(response => {
          // Verifica se a resposta é JSON ou texto simples
          const contentType = response.headers.get("content-type");
          
          if (!response.ok) {
            // Se o status for de erro e for texto simples
            if (contentType && contentType.includes("text/plain")) {
              return response.text().then(errorMessage => {
                throw new Error(`Erro ${response.status}: ${errorMessage}`);
              });
            } else if (contentType && contentType.includes("application/json")) {
              return response.json().then(errorData => {
                throw new Error(`Erro ${response.status}: ${errorData.message || 'Erro desconhecido'}`);
              });
            } else {
              throw new Error(`Erro ${response.status}: Resposta inesperada`);
            }
          }
  
          // Se a resposta for bem-sucedida, verificar se é JSON ou texto simples
          if (contentType && contentType.includes("application/json")) {
            return response.json();  // Faz o parsing se for JSON
          } else {
            return response.text();  // Se for texto simples
          }
        })
        .then(data => {
          // Se a resposta for JSON, exibe a resposta formatada
          if (typeof data === 'object') {
            document.getElementById('responseDiv').innerText = 'Resposta do servidor: ' + JSON.stringify(data, null, 2);
          } else {
            // Se for texto simples, exibe o texto direto
            document.getElementById('responseDiv').innerText = 'Resposta do servidor: ' + data;
          }
        })
        .catch((error) => {
          // Captura qualquer erro e mostra no HTML
          document.getElementById('responseDiv').innerText = `Erro: ${error.message}`;
        });
      });
    } else {
      console.log('Botão de buscar não encontrado.');
    }
