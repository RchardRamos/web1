document.getElementById('login-form').addEventListener('submit', async function (e) {
  e.preventDefault();

  const username = document.getElementById('username').value;
  const password = document.getElementById('password').value;

  try {
    const respuesta = await fetch('http://localhost:8080/api/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ username, password })
    });

    if (respuesta.ok) {
      const data = await respuesta.json();
      localStorage.setItem('token', data.token);
      alert('Inicio de sesión exitoso');
      window.location.href = 'productos.html';
    } else {
      const mensaje = await respuesta.text();
      alert(`Error ${respuesta.status}: ${mensaje}`);
    }
  } catch (error) {
    console.error('Error en la solicitud:', error);
    alert('Error de conexión con el servidor');
  }
});
