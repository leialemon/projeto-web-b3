let uuid = document.getElementById("buuid").value;
console.log(uuid);
function handleConfirm() {
    let url = 'http://localhost:8080/api/v1/orders/confirm/'+uuid;
    console.log(url);
    fetch(url, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ action: 'confirmar' })
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
          document.getElementById('responseDiv').innerText = 'Ordem confirmada! \nResposta do servidor: ' + JSON.stringify(data, null, 2);
        } else {
          // Se for texto simples, exibe o texto direto
          document.getElementById('responseDiv').innerText = 'Ordem confirmada! \nResposta do servidor: ' + data;
        }
      })
      .catch((error) => {
        // Captura qualquer erro e mostra no HTML
        document.getElementById('responseDiv').innerText = `Erro: ${error.message}`;
      });
}

function handleCancel() {
    let url = 'http://localhost:8080/api/v1/orders/cancel/'+uuid;
    fetch(url, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ action: 'cancelar' })
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
          document.getElementById('responseDiv').innerText = 'Ordem cancelada! \nResposta do servidor: ' + JSON.stringify(data, null, 2);
        } else {
          // Se for texto simples, exibe o texto direto
          document.getElementById('responseDiv').innerText = 'Ordem cancelada! \nResposta do servidor: ' + data;
        }
      })
      .catch((error) => {
        // Captura qualquer erro e mostra no HTML
        document.getElementById('responseDiv').innerText = `Erro: ${error.message}`;
      });
}