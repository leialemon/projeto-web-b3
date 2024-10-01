
document.getElementById('criarOrdem').addEventListener('submit', function (event) {
    event.preventDefault();
    let tipo;
      if (document.getElementById('compra').checked){
        tipo = document.getElementById('compra').value;
      } else if (document.getElementById('venda').checked) {
        tipo = document.getElementById('venda').value;
      } 
    
    const formData = {
      stockTicker : document.getElementById('fticker').value,
      stockQuantity: document.getElementById('fqt').value,
      userCpf : document.getElementById('fcpf').value,
      type: tipo,
      userPassword : document.getElementById('fsenha').value,
    };
    fetch('http://localhost:8080/api/v1/orders', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(formData)
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
        document.getElementById('responseDiv').innerText = 'Ordem criada com sucesso! \nUse o uuid fornecido pelo servidor para confirmá-la ou cancelá=la. \nResposta do servidor: ' + JSON.stringify(data, null, 2);
      } else {
        // Se for texto simples, exibe o texto direto
        document.getElementById('responseDiv').innerText = 'Ordem criada com sucesso! \nUse o uuid fornecido pelo servidor para confirmá-la ou cancelá=la. \nResposta do servidor: ' + data;
      }
    })
    .catch((error) => {
      // Captura qualquer erro e mostra no HTML
      document.getElementById('responseDiv').innerText = `Erro: ${error.message}`;
    });
  });


 
  

  
          
